package tw.com.mbproject.yeol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.AuthTokenBean;
import tw.com.mbproject.yeol.controller.request.LoginMemberRequest;

import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

    private final ReactiveAuthenticationManager authenticationManager;
    private final ServerSecurityContextRepository contextRepository;

    /**
     * 登入.
     */
    @PostMapping("/login")
    public Mono<String> login(@RequestBody LoginMemberRequest request, ServerWebExchange exchange) {
        // TODO 建立jwt
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        AuthTokenBean authenticationToken = new AuthTokenBean(token, request.getName(), request.getPassword());
        return authenticationManager.authenticate(authenticationToken)
                .doOnSuccess(auth -> contextRepository.save(exchange, new SecurityContextImpl(auth)))
                .then(Mono.just(token));
    }


}
