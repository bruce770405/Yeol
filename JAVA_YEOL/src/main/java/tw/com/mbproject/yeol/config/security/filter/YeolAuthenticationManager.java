package tw.com.mbproject.yeol.config.security.filter;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.util.JWTUtils;
import tw.com.mbproject.yeol.util.SpringUtils;

import java.util.ArrayList;
import java.util.List;

@Component("YeolAuthenticationManager")
public class YeolAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        JWTUtils jwtUtils = SpringUtils.getBean(JWTUtils.class.getSimpleName(), JWTUtils.class);
        String authToken = authentication.getCredentials().toString();
        try {
            if (!jwtUtils.validateToken(authToken)) {
                return Mono.empty();
            }
            Claims claims = jwtUtils.getAllClaimsFromToken(authToken);
            List<String> rolesMap = claims.get("role", List.class);
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (String rolemap : rolesMap) {
                authorities.add(new SimpleGrantedAuthority(rolemap));
            }
            return Mono.just(new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities));
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}
