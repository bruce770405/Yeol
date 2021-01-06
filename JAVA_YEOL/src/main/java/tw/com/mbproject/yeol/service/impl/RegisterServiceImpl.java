package tw.com.mbproject.yeol.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.config.security.YeolUserDetailsService;
import tw.com.mbproject.yeol.constant.ConstantNumber;
import tw.com.mbproject.yeol.constant.Role;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.request.LoginMemberRequest;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.LoginDto;
import tw.com.mbproject.yeol.dto.LoginKxyDto;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.entity.Member;
import tw.com.mbproject.yeol.exception.YeolException;
import tw.com.mbproject.yeol.repo.MemberRepo;
import tw.com.mbproject.yeol.service.RegisterService;
import tw.com.mbproject.yeol.util.JWTUtils;
import tw.com.mbproject.yeol.util.RsaUtils;

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
    public Mono<LoginKxyDto> getMemberPublicKxy() {
        return Mono.defer(() -> Mono.just(RsaUtils.kxyMap().get(RsaUtils.PUBLIC_KXY)))
                .map(publicKey -> LoginKxyDto.builder().publicKxy(publicKey).build());
    }

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
    public Mono<LoginDto> login(LoginMemberRequest request) throws Exception {
        return Mono.just(RsaUtils.decryptionByPrivateKxy(request.getPassword(),RsaUtils.kxyMap().get(RsaUtils.PRIVATE_KXY)))
                .zipWith(userService.findByUsername(request.getName()))
                .flatMap(tuple2 -> {
                    final String decodePaxxword = tuple2.getT1();
                    final UserDetails userDetails = tuple2.getT2();
                    if (passwordEncoder.encode(decodePaxxword).equals(userDetails.getPassword())) {
                        return Mono.just(LoginDto.builder().token(jwtUtil.generateToken(userDetails)).name(userDetails.getUsername()).build());
                    } else {
                        return Mono.empty();
                    }
                }).defaultIfEmpty(LoginDto.builder().build());
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
