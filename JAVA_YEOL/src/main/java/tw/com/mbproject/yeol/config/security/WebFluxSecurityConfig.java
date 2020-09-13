package tw.com.mbproject.yeol.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.AuthTokenBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebFluxSecurityConfig {

    private final Map<String, SecurityContext> tokenCache = new ConcurrentHashMap<>();

//    @Autowired
//    private TokenCacheProvider cacheProvider;

    /**
     * token header.
     */
    @Value("${yeol.security.bearer:Bearer}")
    private String bearer;

    @Value("${yeol.security.enable:true}")
    private boolean securityEnabled;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        // Please refer to "https://awesomeopensource.com/project/raphaelDL/spring-webflux-security-jwt?categoryPage=6" for JWT example

        // 由於使用的是JWT，不需要csrf
        var spec = http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
//        if (securityEnabled) {

                .addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
                .authorizeExchange()
                // Passing a white list endpoint, do not need to
                // authenticate
                .pathMatchers("/register/**").permitAll()
                // 除上面設定外，其他請求皆認證
                .anyExchange().authenticated();

//        } else {
//            spec.pathMatchers("/**").permitAll();
//        }

        return spec.and().build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 驗證 spring webflux security filter.
     * <p>
     * TODO implement jwt.
     *
     * @return
     */
    @Bean
    AuthenticationWebFilter authenticationWebFilter() {
        var authenticationWebFilter = new AuthenticationWebFilter(reactiveAuthenticationManager());

        // 白名單
        NegatedServerWebExchangeMatcher negateWhiteList = new NegatedServerWebExchangeMatcher(ServerWebExchangeMatchers.pathMatchers("/register/**"));
        authenticationWebFilter.setRequiresAuthenticationMatcher(negateWhiteList);

        authenticationWebFilter.setServerAuthenticationConverter(serverAuthenticationConverter());
        authenticationWebFilter.setSecurityContextRepository(serverSecurityContextRepository());

        authenticationWebFilter.setAuthenticationFailureHandler((webFilterExchange, exception) -> Mono.error(new BadCredentialsException("auth fail")));
        return authenticationWebFilter;
    }


    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
        final ReactiveUserDetailsService detailsService = new YeolUserDetailsService();
        List<ReactiveAuthenticationManager> managers = new LinkedList<>();
        // 其他登入方式(ex: otp ...)可在此設定不throw exception或者Mono.error
        managers.add(authentication -> Mono.empty());
        // 必须放最後不然會優先使用帳號密碼驗證但是用帳號密碼不對時AuthenticationManager會call Mono.error,
        // 造成後面的AuthenticationManager不生效
        managers.add(new UserDetailsRepositoryReactiveAuthenticationManager(detailsService));
        return new DelegatingReactiveAuthenticationManager(managers);
    }

    @Bean
    public ServerAuthenticationConverter serverAuthenticationConverter() {
        final AnonymousAuthenticationToken anonymous = new AnonymousAuthenticationToken("key", "anonymous", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
        return exchange -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isEmpty(token)) {
                return Mono.just(anonymous);
            }
            if (!token.startsWith(bearer) || token.length() <= bearer.length() || !tokenCache.containsKey(token.substring(bearer.length()))) {
                return Mono.just(anonymous);
            }
            return Mono.just(tokenCache.get(token.substring(bearer.length())).getAuthentication());
        };
    }

    /**
     * security認證結束後運用的repository.
     *
     * @return
     */
    @Bean
    public ServerSecurityContextRepository serverSecurityContextRepository() {
        return new ServerSecurityContextRepository() {
            /**
             * 儲存方法.
             * @param exchange
             * @param context
             * @return
             */
            @Override
            public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
                if (context.getAuthentication() instanceof AuthTokenBean) {
                    var authentication = (AuthTokenBean) context.getAuthentication();
                    tokenCache.put(authentication.getToken(), context);
                }
                return Mono.empty();
            }

            /**
             * 讀取方法.
             * @param exchange
             * @return
             */
            @Override
            public Mono<SecurityContext> load(ServerWebExchange exchange) {
                ServerHttpRequest request = exchange.getRequest();
                String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

                if (StringUtils.isEmpty(authorization) || !tokenCache.containsKey(authorization)) {
                    return Mono.empty();
                }
                return Mono.just(tokenCache.get(authorization));
            }
        };
    }


}
