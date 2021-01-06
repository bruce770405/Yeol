package tw.com.mbproject.yeol.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 *
 */
@Log4j2
@Component
public class AuthorizedInterceptor implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        final long startTime = System.currentTimeMillis();
        final String path = serverWebExchange.getRequest().getURI().getPath();
        return webFilterChain.filter(serverWebExchange).doAfterTerminate(() -> {
                    serverWebExchange.getResponse().getHeaders().entrySet().forEach(e ->
                            log.info("Response header '{}': {}", e.getKey(), e.getValue()));
                    log.info("Served '{}' as {} in {} sec", path, serverWebExchange.getResponse().getStatusCode(),
                            System.currentTimeMillis() - startTime);
                }
        );
    }
}
