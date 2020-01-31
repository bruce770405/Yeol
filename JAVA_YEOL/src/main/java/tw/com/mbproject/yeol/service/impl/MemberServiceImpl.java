package tw.com.mbproject.yeol.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import tw.com.mbproject.yeol.common.service.BizService;
import tw.com.mbproject.yeol.constant.ConstantNumber;
import tw.com.mbproject.yeol.controller.request.*;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.dto.PageDto;
import tw.com.mbproject.yeol.entity.Member;
import tw.com.mbproject.yeol.exception.YeolException;
import tw.com.mbproject.yeol.repo.MemberRepo;
import tw.com.mbproject.yeol.service.MemberService;
import tw.com.mbproject.yeol.util.YeolDateUtil;

@Service
public class MemberServiceImpl extends BizService implements MemberService {
    
    @Autowired
    private MemberRepo memberRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public List<MemberDto> getAllMembers() {
        return memberRepo.findAll().stream()
                .map(MemberDto::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public PageDto<List<MemberDto>> getPagedMembers(Integer page, Integer size) {
        if(page < 0 || size < 1) {
            throw new YeolException(ErrCode.INCORRECT_PAGE_FORMAT);
        }
        var pageable = PageRequest.of(page, size, Sort.by("createMs").descending());
        var pageResult = memberRepo.findByDeleteFlagFalse(pageable);

        List<MemberDto> memberDtoList = pageResult.getContent().stream()
                .map(MemberDto::valueOf)
                .collect(Collectors.toList());
        
        PageDto<List<MemberDto>> pageDto = new PageDto<>(pageResult);
        pageDto.setData(memberDtoList);
        return pageDto;
    }

    @Override
    public Optional<MemberDto> getMember(QueryMemberRequest request) {
        return memberRepo.findByIdOrEmailAndDeleteFlagFalse(
                request.getId(), request.getEmail())
                .map(MemberDto::valueOf);
    }

    @Override
    public Optional<MemberDto> addMember(CreateMemberRequest request) throws YeolException {
        
        if (isMemberExisted(request.getName(), request.getEmail())) {
            throw new YeolException(ErrCode.MEMBER_EXISTED);
        }

        var member = Member.builder()
                .id(ObjectId.get().toHexString())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .postNumber(ConstantNumber.INIT_COUNT)
                .createMs(YeolDateUtil.getCurrentMillis())
                .updateMs(YeolDateUtil.getCurrentMillis())
                .deleteFlag(false)
                .build();

        member = memberRepo.save(member);
        return Optional.ofNullable(MemberDto.valueOf(member));
    }
    
    private boolean isMemberExisted(String name, String email) {
        var memberList = memberRepo.findByNameOrEmailAndDeleteFlagFalse(name, email);
        return !CollectionUtils.isEmpty(memberList);
    }
    
    private boolean isEmailExisted(String email) {
        var memberList = memberRepo.findByEmailAndDeleteFlagFalse(email);
        return !CollectionUtils.isEmpty(memberList);
    }
    
    public Optional<MemberDto> updateMember(UpdateMemberRequest request){
        
        if (isEmailExisted(request.getEmail())) {
            throw new YeolException(ErrCode.EMAIL_EXISTED);
        }
        
        return memberRepo.findById(request.getId()).map(e -> {
            e.setEmail(request.getEmail());
//            e.setPassword(passwordEncoder.encode(request.getPassword()));
            e.setUpdateMs(YeolDateUtil.getCurrentMillis());
            e.setPostNumber(request.getPostNumber());
            return MemberDto.valueOf(memberRepo.save(e));
        });
    }

    @Override
    public Optional<MemberDto> deleteMember(DeleteRequest request) {
        return memberRepo.findById(request.getId())
        .map(e -> {
            e.setUpdateMs(YeolDateUtil.getCurrentMillis());
            e.setDeleteFlag(true);
            return MemberDto.valueOf(memberRepo.save(e));
        });
    }

}
