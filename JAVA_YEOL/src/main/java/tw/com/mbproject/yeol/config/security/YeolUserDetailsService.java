package tw.com.mbproject.yeol.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.entity.Member;
import tw.com.mbproject.yeol.repo.MemberRepo;

@Service
@RequiredArgsConstructor
public class YeolUserDetailsService implements ReactiveUserDetailsService {

    private final MemberRepo memberRepo;

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        return memberRepo.findByNameAndDeleteFlagFalse(username)
                .map(Member::toUserDetails)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User Not Found")));
    }

}
