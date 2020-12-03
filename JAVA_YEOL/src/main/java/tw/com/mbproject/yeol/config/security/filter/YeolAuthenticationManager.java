package tw.com.mbproject.yeol.config.security.filter;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.util.JWTUtils;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component("YeolAuthenticationManager")
@RequiredArgsConstructor
public class YeolAuthenticationManager implements ReactiveAuthenticationManager {

    private final JWTUtils jwtUtils;

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        try {
            if (!jwtUtils.validateToken(authToken)) {
                log.info("token expired time validate fail");
                return Mono.empty();
            }
            Claims claims = jwtUtils.getAllClaimsFromToken(authToken);
            List<String> role = claims.get("role", List.class);
            List<GrantedAuthority> authorities = role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            return Mono.just(new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities));
        } catch (Exception e) {
            log.info(e.getMessage());
            return Mono.empty();
        }
    }
}
