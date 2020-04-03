package tw.com.mbproject.yeol.cache.items;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@EqualsAndHashCode(callSuper = true)
public class TokenCacheItem extends UsernamePasswordAuthenticationToken {

    private final String token;

    public TokenCacheItem(String token, Object principal, Object credentials) {
        super(principal, credentials);
        this.token = token;
    }

    public TokenCacheItem(String token, Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.token = token;
    }

}
