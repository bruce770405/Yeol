package tw.com.mbproject.yeol.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.constant.ConstantNumber;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.request.DeleteRequest;
import tw.com.mbproject.yeol.controller.request.QueryMemberRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMemberRequest;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.dto.PageDto;
import tw.com.mbproject.yeol.entity.Member;
import tw.com.mbproject.yeol.exception.YeolException;
import tw.com.mbproject.yeol.repo.MemberRepo;
import tw.com.mbproject.yeol.service.MemberService;
import tw.com.mbproject.yeol.util.YeolDateUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<List<MemberDto>> getAllMembers() {
        return memberRepo.findAll().map(MemberDto::valueOf).collectList();
    }

    @Override
    public Mono<PageDto<List<MemberDto>>> getPagedMembers(Integer page, Integer size) {
        if (page < 0 || size < 1) {
            throw new YeolException(ErrCode.INCORRECT_PAGE_FORMAT);
        }
        var pageable = PageRequest.of(page, size, Sort.by("createMs").descending());

        return memberRepo.findByDeleteFlagFalse(pageable).map(MemberDto::valueOf).collectList()
                .map(memberDtoList -> {
                    PageDto dto = PageDto.empty();
                    dto.setData(memberDtoList);
                    return dto;
                });
    }

    @Override
    public Mono<MemberDto> getMember(QueryMemberRequest request) {
        return memberRepo.findByIdOrEmailAndDeleteFlagFalse(request.getId(), request.getEmail())
                .switchIfEmpty(Mono.error(new YeolException(ErrCode.MEMBER_NOT_FOUND)))
                .map(MemberDto::valueOf);
    }

    @Override
    public Mono<MemberDto> addMember(CreateMemberRequest request) throws YeolException {

        if (isMemberExisted(request.getName(), request.getEmail())) {
            throw new YeolException(ErrCode.MEMBER_EXISTED);
        }

        return Mono.just(Member.builder()
                .id(ObjectId.get().toHexString())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .postNumber(ConstantNumber.INIT_COUNT)
                .createMs(YeolDateUtil.getCurrentMillis())
                .updateMs(YeolDateUtil.getCurrentMillis())
                .deleteFlag(false)
                .build())
                .doOnNext(memberRepo::save)
                .map(MemberDto::valueOf);
    }

    private boolean isMemberExisted(String name, String email) {
        return !memberRepo.findByNameOrEmailAndDeleteFlagFalse(name, email).collectList().block().isEmpty();
    }

    private boolean isEmailExisted(String email) {
        return !memberRepo.findByEmailAndDeleteFlagFalse(email).collectList().block().isEmpty();
    }

    @Override
    public Mono<MemberDto> updateMember(UpdateMemberRequest request) {

        if (isEmailExisted(request.getEmail())) {
            throw new YeolException(ErrCode.EMAIL_EXISTED);
        }

        return memberRepo.findById(request.getId())
                .switchIfEmpty(Mono.error(new YeolException(ErrCode.MEMBER_NOT_FOUND)))
                .doOnNext(member -> {
                    member.setEmail(request.getEmail());
//            member.setPassword(passwordEncoder.encode(request.getPassword()));
                    member.setUpdateMs(YeolDateUtil.getCurrentMillis());
                    member.setPostNumber(request.getPostNumber());
                    memberRepo.save(member);
                }).map(MemberDto::valueOf);
    }

    @Override
    public Mono<MemberDto> deleteMember(DeleteRequest request) {
        return memberRepo.findById(request.getId())
                .switchIfEmpty(Mono.error(new YeolException(ErrCode.MEMBER_NOT_FOUND)))
                .doOnNext(member -> {
                    member.setUpdateMs(YeolDateUtil.getCurrentMillis());
                    member.setDeleteFlag(true);
                    memberRepo.save(member);
                })
                .map(MemberDto::valueOf);
    }

}
