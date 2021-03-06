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
import tw.com.mbproject.yeol.util.YeolStrUtil;

/**
 * login api controller.
 */
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

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
    public Mono<ResponseEntity<?>> loginByUsername(@RequestBody LoginMemberRequest request) {
        return registerService.login(request)
                .map(s -> YeolStrUtil.isBlank(s) ?
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() :
                        ResponseEntity.ok(new YeolResponse<>(s, ErrCode.SUCCESS))
                );
    }

    /**
     * 註冊.
     */
    @PostMapping("/register")
    public Mono<ResponseEntity<?>> register(@RequestBody CreateMemberRequest request) {
        return registerService.addMember(request).map(ResponseEntity::ok);
    }
}
