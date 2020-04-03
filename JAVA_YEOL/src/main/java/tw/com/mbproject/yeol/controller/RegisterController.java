package tw.com.mbproject.yeol.controller;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.cache.items.TokenCacheItem;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.request.LoginMemberRequest;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.service.MemberService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/register")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

    private final MemberService memberService;
    private final ReactiveAuthenticationManager authenticationManager;
    private final ServerSecurityContextRepository contextRepository;

    /**
     * 新增會員
     */
    @PostMapping(value = "/join", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MemberDto>> createMember(
            @Valid @RequestBody CreateMemberRequest request) throws Exception {

        var memberDto = memberService.addMember(request);
        return memberDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }

    /**
     * 登入.
     *
     * @param request
     * @param exchange
     * @return
     */
    @PostMapping("/login")
    public Mono login(@RequestBody LoginMemberRequest request, ServerWebExchange exchange) {
        // TODO 建立jwt
        final String token = UUID.randomUUID().toString().replaceAll("-", "");
        TokenCacheItem authenticationToken = new TokenCacheItem(token, request.getName(), request.getPassword());
        return authenticationManager.authenticate(authenticationToken)
                .doOnSuccess(auth -> contextRepository.save(exchange, new SecurityContextImpl(authenticationToken)))
                .then(Mono.just(token));
    }


}
