package tw.com.mbproject.yeol.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.config.security.SecurityContextRepository;
import tw.com.mbproject.yeol.config.security.filter.YeolAuthenticationManager;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebFluxSecurityConfig {

    private final YeolAuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    @Value("${yeol.security.enable:true}")
    private boolean securityEnabled;

    public WebFluxSecurityConfig(@Qualifier("YeolAuthenticationManager") YeolAuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        if (!this.securityEnabled) {
            return http.csrf().disable()
                    .formLogin().disable()
                    .httpBasic().disable().authorizeExchange().anyExchange().permitAll().and().build();
        }

        // 由於使用的是JWT，不需要csrf
        return http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
                .accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))).and()

                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()

                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                // Passing a white list endpoint, do not need to
                // authenticate
                .pathMatchers("/api/account/**").permitAll()
                // 除上面設定外，其他請求皆認證
                .anyExchange().authenticated().and().build();

    }


    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
