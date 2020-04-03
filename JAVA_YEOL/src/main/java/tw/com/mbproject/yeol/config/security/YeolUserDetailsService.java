package tw.com.mbproject.yeol.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.cache.MemberCacheProvider;
import tw.com.mbproject.yeol.cache.items.MemberCacheItem;
import tw.com.mbproject.yeol.entity.Member;
import tw.com.mbproject.yeol.repo.MemberRepo;

@Service
public class YeolUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    private MemberRepo memberRepo;

    private MemberCacheProvider cacheProvider;

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        var example = Example.of(Member.builder().name(username).build());
        var item = cacheProvider.getMemberCache(username);
        return memberRepo.findOne(example)
                .map(e -> Mono.just(e.toUserDetails()))
                .orElse(Mono.error(new UsernameNotFoundException("User Not Found")));
    }

}
