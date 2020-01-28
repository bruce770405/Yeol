package tw.com.mbproject.yeol.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import tw.com.mbproject.yeol.entity.Member;

public interface MemberRepo extends MongoRepository<Member, String> {
    
    List<Member> findByEmail(String email);

}
