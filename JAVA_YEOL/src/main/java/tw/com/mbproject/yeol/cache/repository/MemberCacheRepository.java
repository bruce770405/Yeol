package tw.com.mbproject.yeol.cache.repository;

import org.springframework.data.repository.CrudRepository;
import tw.com.mbproject.yeol.cache.items.MemberCacheItem;

//@Repository
public interface MemberCacheRepository extends CrudRepository<MemberCacheItem, String> {
}
