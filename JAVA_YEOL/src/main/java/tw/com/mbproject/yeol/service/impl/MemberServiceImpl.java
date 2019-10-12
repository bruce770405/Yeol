package tw.com.mbproject.yeol.service.impl;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tw.com.mbproject.yeol.common.service.BizService;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.entity.Member;
import tw.com.mbproject.yeol.repo.MemberRepo;
import tw.com.mbproject.yeol.service.MemberService;

@Service
public class MemberServiceImpl extends BizService implements MemberService {
    
    private final static int INIT_COUNT = 0;
    
    @Autowired
    private MemberRepo memberRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<MemberDto> addMember(CreateMemberRequest request) {
        Member member = Member.builder()
                .id(ObjectId.get().toHexString())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .postNumber(INIT_COUNT).build();
        
        member = memberRepo.save(member);
        return Optional.ofNullable(MemberDto.valueOf(member));
    }

}
