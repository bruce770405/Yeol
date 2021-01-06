package tw.com.mbproject.yeol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.request.LoginMemberRequest;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.service.RegisterService;

import javax.validation.Valid;
import java.util.Objects;

/**
 * login api controller.
 */
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    /**
     * 獲取前端登入用publicKxy
     */
    @PostMapping("/publicKxy/get")
    public Mono<ResponseEntity<?>> rsaPublicKxy() {
        return registerService.getMemberPublicKxy()
                .map(publicKxy -> ResponseEntity.ok(new YeolResponse<>(publicKxy, ErrCode.SUCCESS)));
    }

    /**
     * 登入.
     */
//    @PostMapping("/google/login")
//    public Mono<ResponseEntity<?>> loginByGoogleAccount(@RequestBody LoginMemberRequest request) {
//        return userService.findByUsername(request.getName()).map((userDetails) -> {
//            if (passwordEncoder.encode(request.getPassword()).equals(userDetails.getPassword())) {
//                return ResponseEntity.ok(new YeolResponse<>(jwtUtil.generateToken(userDetails), ErrCode.SUCCESS));
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
//    }

    /**
     * 登入.
     */
    @PostMapping("/username/login")
    public Mono<ResponseEntity<?>> loginByUsername(@Valid @RequestBody LoginMemberRequest request) throws Exception {
        return registerService.login(request)
                .map(member -> Objects.isNull(member) ?
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() :
                        ResponseEntity.ok(new YeolResponse<>(member, ErrCode.SUCCESS))
                );
    }

    /**
     * 註冊.
     */
    @PostMapping("/register")
    public Mono<ResponseEntity<?>> register(@Valid @RequestBody CreateMemberRequest request) {
        return registerService.addMember(request).map(ResponseEntity::ok);
    }
}
