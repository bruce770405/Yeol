package tw.com.mbproject.yeol.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import tw.com.mbproject.yeol.cache.items.MemberCacheItem;
import tw.com.mbproject.yeol.config.YeolCacheConfig;
import tw.com.mbproject.yeol.repo.MemberRepo;

/**
 *
 */
@Slf4j
@Component
@CacheConfig(cacheNames = "MemberCacheManager", cacheManager = YeolCacheConfig.CAFFEINE_CACHE_MANAGER)
public class MemberCacheProvider extends CacheProvider<MemberCacheItem> {

    @Autowired
    private MemberRepo memberRepo;


    @Autowired
    public MemberCacheProvider(CacheManager manager) {
        super(manager);
    }

    /**
     * 取得使用者.
     *
     * @param username 使用者登入編入
     */
    @Cacheable(sync = false, unless = "#result == null", condition = "#p0 != null && '' != #p0")
    public MemberCacheItem getMemberCache(String username) {
        return null;
    }


    @CachePut(key = "#member.id")
    public void addOne(MemberCacheItem member) {
        log.info("create");
    }


}
