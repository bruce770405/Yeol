package tw.com.mbproject.yeol.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import tw.com.mbproject.yeol.entity.Member;

public interface MemberRepo extends MongoRepository<Member, String> {
    
    @Query(value = "{ $or: [ { 'name': ?0 }, { 'email': ?1 } ] }")
    List<Member> findExistedMembers(String name, String email);

}
