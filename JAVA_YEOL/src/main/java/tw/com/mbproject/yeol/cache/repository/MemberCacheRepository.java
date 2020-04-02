package tw.com.mbproject.yeol.cache.repository;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tw.com.mbproject.yeol.cache.items.MemberCacheItem;

//@Repository
public interface MemberCacheRepository extends CrudRepository<MemberCacheItem, String> {
}
