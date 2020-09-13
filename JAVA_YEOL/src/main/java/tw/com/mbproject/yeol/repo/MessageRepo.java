package tw.com.mbproject.yeol.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import tw.com.mbproject.yeol.entity.Message;

@Repository
public interface MessageRepo extends ReactiveMongoRepository<Message, String> {
    
    Flux<Message> findByDeleteFlag(Boolean deleteFlag, Pageable pageable);

    Flux<Message> findByDeleteFlagFalseAndCreateMsGreaterThanEqual(Long ms, Pageable pageable);

}
