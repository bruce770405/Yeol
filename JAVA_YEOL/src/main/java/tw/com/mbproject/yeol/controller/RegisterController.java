package tw.com.mbproject.yeol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.config.security.YeolUserDetailsService;
import tw.com.mbproject.yeol.controller.request.LoginMemberRequest;
import tw.com.mbproject.yeol.controller.response.AuthTokenResponse;
import tw.com.mbproject.yeol.util.JWTUtils;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

    @Autowired
    private YeolUserDetailsService userService;

    @Autowired
    private JWTUtils jwtUtil;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    /**
     * 登入.
     */
    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody LoginMemberRequest request) {
        return userService.findByUsername(request.getName()).map((userDetails) -> {
            if (passwordEncoder.encode(request.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok(new AuthTokenResponse(jwtUtil.generateToken(userDetails)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }


}
