package tw.com.mbproject.yeol.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.entity.Member;

@Repository
public interface MemberRepo extends ReactiveMongoRepository<Member, String> {
    Flux<Member> findByDeleteFlagFalse(Pageable pageable);

    Mono<Member> findByIdOrEmailAndDeleteFlagFalse(String id, String email);

    Flux<Member> findByNameOrEmailAndDeleteFlagFalse(String name, String email);

    Flux<Member> findByEmailAndDeleteFlagFalse(String email);
}
