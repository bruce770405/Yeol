package tw.com.mbproject.yeol.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tw.com.mbproject.yeol.entity.Message;

public interface MessageRepo extends MongoRepository<Message, String> {

}
