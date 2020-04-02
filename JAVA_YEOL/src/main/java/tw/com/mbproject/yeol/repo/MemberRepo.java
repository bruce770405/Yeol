package tw.com.mbproject.yeol.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import tw.com.mbproject.yeol.entity.Member;

public interface MemberRepo extends MongoRepository<Member, String> {

    Page<Member> findByDeleteFlagFalse(Pageable pageable);
    Optional<Member> findByIdOrEmailAndDeleteFlagFalse(String id, String email);

    List<Member> findByNameOrEmailAndDeleteFlagFalse(String name, String email);
    List<Member> findByEmailAndDeleteFlagFalse(String email);

}
