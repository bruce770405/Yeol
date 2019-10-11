package tw.com.mbproject.yeol.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tw.com.mbproject.yeol.entity.Message;

public interface MemberRepo extends MongoRepository<Message, String> {

}
