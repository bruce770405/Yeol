package tw.com.mbproject.yeol.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tw.com.mbproject.yeol.entity.Member;

public interface MemberRepo extends MongoRepository<Member, String> {

}
