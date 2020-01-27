package tw.com.mbproject.yeol.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tw.com.mbproject.yeol.entity.Message;

@Repository
public interface MessageRepo extends MongoRepository<Message, String> {
    
    Page<Message> findByDeleteFlag(Boolean deleteFlag, Pageable pageable);
    
    Page<Message> findByDeleteFlagFalseAndCreateMsGreaterThanEqual(Long ms, Pageable pageable);

}
