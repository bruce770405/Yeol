package tw.com.mbproject.yeol.service.impl;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import tw.com.mbproject.yeol.common.service.BizService;
import tw.com.mbproject.yeol.constant.ConstantNumber;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.entity.Member;
import tw.com.mbproject.yeol.exception.YeolException;
import tw.com.mbproject.yeol.repo.MemberRepo;
import tw.com.mbproject.yeol.service.MemberService;

@Service
public class MemberServiceImpl extends BizService implements MemberService {
    
    @Autowired
    private MemberRepo memberRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<MemberDto> addMember(CreateMemberRequest request) throws YeolException {
        
        if (isMemberExisted(request.getName(), request.getEmail())) {
            throw new YeolException(ErrCode.MEMBER_EXISTED);
        }
        
        var member = Member.builder()
                .id(ObjectId.get().toHexString())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .postNumber(ConstantNumber.INIT_COUNT).build();
        
        member = memberRepo.save(member);
        return Optional.ofNullable(MemberDto.valueOf(member));
    }
    
    public boolean isMemberExisted(String name, String email) {
        var memberList = memberRepo.findExistedMembers(name, email);
        if (CollectionUtils.isEmpty(memberList)) {
            return false;
        } else {
            return true;
        }
    }

}
