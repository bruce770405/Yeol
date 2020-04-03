package tw.com.mbproject.yeol.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 *
 */
public class AuthorizedInterceptor implements WebFilter {

    /**
     * log物件.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizedInterceptor.class);


    /**
     * TODO 目前只紀錄service前後完成的時間.
     *
     * @param serverWebExchange
     * @param webFilterChain
     * @return the Mono Object
     */
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        long startTime = System.currentTimeMillis();
        String path = serverWebExchange.getRequest().getURI().getPath();
        LOGGER.info("Serving '{}'", path);

        return webFilterChain.filter(serverWebExchange).doAfterTerminate(() -> {
                    serverWebExchange.getResponse().getHeaders().entrySet().forEach(e ->
                            LOGGER.info("Response header '{}': {}", e.getKey(), e.getValue()));

                    LOGGER.info("Served '{}' as {} in {} msec",
                            path,
                            serverWebExchange.getResponse().getStatusCode(),
                            System.currentTimeMillis() - startTime);
                }
        );
    }
}
