package tw.com.mbproject.yeol.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import tw.com.mbproject.yeol.cache.items.TokenCacheItem;
import tw.com.mbproject.yeol.config.YeolCacheConfig;

@Slf4j
@Component
@CacheConfig(cacheNames = "TokenCacheManager", cacheManager = YeolCacheConfig.CAFFEINE_CACHE_MANAGER)
public class TokenCacheProvider extends CacheProvider<TokenCacheItem> {

    @Autowired
    public TokenCacheProvider(CacheManager manager) {
        super(manager);
    }

    @Override
    @CachePut(key = "#userInfo.id")
    public void addOne(TokenCacheItem object) {
        log.info("create");
    }

}
