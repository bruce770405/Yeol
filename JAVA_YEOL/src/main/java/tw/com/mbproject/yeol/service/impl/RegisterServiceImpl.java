package tw.com.mbproject.yeol.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.config.security.YeolUserDetails;
import tw.com.mbproject.yeol.config.security.YeolUserDetailsService;
import tw.com.mbproject.yeol.constant.ConstantNumber;
import tw.com.mbproject.yeol.constant.Role;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.request.LoginMemberRequest;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.entity.Member;
import tw.com.mbproject.yeol.exception.YeolException;
import tw.com.mbproject.yeol.repo.MemberRepo;
import tw.com.mbproject.yeol.service.RegisterService;
import tw.com.mbproject.yeol.util.JWTUtils;
import tw.com.mbproject.yeol.util.YeolDateUtil;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final MemberRepo memberRepo;
    private final YeolUserDetailsService userService;
    private final JWTUtils jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<MemberDto> addMember(CreateMemberRequest request) throws YeolException {
        return memberRepo.findByNameOrEmailAndDeleteFlagFalse(request.getName(), request.getEmail())
                .collectList()
                .flatMap(members ->
                        CollectionUtils.isEmpty(members) ?
                                Mono.just(buildMemberEntity(request)).flatMap(memberRepo::save).map(MemberDto::valueOf) :
                                Mono.error(new YeolException(ErrCode.MEMBER_EXISTED))
                );
    }

    @Override
    public Mono<String> login(LoginMemberRequest request) {
        return userService.findByUsername(request.getName()).flatMap((userDetails) -> {
            if (passwordEncoder.encode(request.getPassword()).equals(userDetails.getPassword())) {
                return Mono.just(jwtUtil.generateToken(userDetails));
            } else {
                return Mono.empty();
            }
        }).defaultIfEmpty("");
    }

    private Member buildMemberEntity(CreateMemberRequest request) {
        return Member.builder()
                .id(ObjectId.get().toHexString())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .postNumber(ConstantNumber.INIT_COUNT)
                .deleteFlag(false)
                .roles(Stream.of(Role.USER.name()).collect(Collectors.toList()))
                .build();
    }

}
