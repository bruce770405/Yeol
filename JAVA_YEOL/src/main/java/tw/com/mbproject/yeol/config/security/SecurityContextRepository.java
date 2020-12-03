package tw.com.mbproject.yeol.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

    /**
     * token header.
     */
    @Value("${yeol.security.bearer:Bearer}")
    private String bearer;

    private final ReactiveAuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange swe) {
        ServerHttpRequest request = swe.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith(bearer)) {
            String authToken = authHeader.substring(bearer.length());
            Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
            return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
        } else {
            log.info("unauthorized");
            return Mono.empty();
        }
    }

}
