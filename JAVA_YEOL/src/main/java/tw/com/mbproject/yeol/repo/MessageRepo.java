package tw.com.mbproject.yeol.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tw.com.mbproject.yeol.entity.Message;

@Repository
public interface MessageRepo extends MongoRepository<Message, String> {

}
