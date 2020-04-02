package tw.com.mbproject.yeol.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.Serializable;

public abstract class CacheProvider<T extends Serializable> {

    /**
     * 標注於CacheConfig.cacheNames[0] 的值.
     */
    private String cacheConfigCacheName;

    /**
     * Cache 管理器.
     */
    private CacheManager manager;

    public CacheProvider(CacheManager manager) {
        this.manager = manager;
    }

    abstract void addOne(T object);

}
