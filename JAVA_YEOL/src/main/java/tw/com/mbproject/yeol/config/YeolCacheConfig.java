package tw.com.mbproject.yeol.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * cache 機制.
 * <p>
 * 如果未定義類型CacheManager或CacheResolver命名的bean cacheResolver（@see CachingConfigurer），
 * 在Spring Boot中透過@EnableCaching註解自動配置合適的CacheManager，
 * Spring Boot根据下面的顺序去scan CacheProvider：
 * <span>
 * (1) Generic
 * (2) JCache (JSR-107) (EhCache 3, Hazelcast, Infinispan, etc)
 * (3) EhCache 2.x
 * Hazelcast
 * Infinispan
 * Couchbase
 * Redis
 * Caffeine
 * Guava (deprecated)
 * Simple
 * </span>
 * 也可以強制CacheProvider透過spring.cache.type 属性使用。
 * 如果需要在某些環境（ex:test）中完全禁用cache，使用此属性。
 * </p>
 */
@Configuration
@Slf4j
public class YeolCacheConfig {

    public static final String CAFFEINE_CACHE_MANAGER = "caffeineCacheManager";


    /**
     * 配置缓存管理器
     *
     * @return 缓存管理器
     */
    @Bean(CAFFEINE_CACHE_MANAGER)
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterAccess(60, TimeUnit.SECONDS)
                .initialCapacity(100)
                .maximumSize(1000));
        return cacheManager;
    }
}
