package tw.com.mbproject.yeol.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class WebFluxSecurityConfig {
    
    @Value("${yeol.security.enable:true}")
    private boolean isSecurityEnabled;
    
    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {

        // Please refer to "https://awesomeopensource.com/project/raphaelDL/spring-webflux-security-jwt?categoryPage=6" for JWT example

        var spec = http.csrf().disable().formLogin().and()
                .authorizeExchange();
        if (isSecurityEnabled) {
            spec.pathMatchers("/api/**").authenticated()
                .pathMatchers("/register/**").permitAll();
        } else {
            spec.pathMatchers("/**").permitAll();
        }
        
        return spec.and().build();
        
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
