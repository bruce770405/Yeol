package tw.com.mbproject.yeol.interceptor;


import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class AuthenInterceptor implements WebFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
//        serverWebExchange.getResponse()
//                .getHeaders().add("web-filter", "web-filter-test");
//        return webFilterChain.filter(serverWebExchange);
        return null;
    }
}
