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
import tw.com.mbproject.yeol.controller.request.DeleteMemberRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMemberRequest;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.entity.Member;
import tw.com.mbproject.yeol.exception.YeolException;
import tw.com.mbproject.yeol.repo.MemberRepo;
import tw.com.mbproject.yeol.service.MemberService;
import tw.com.mbproject.yeol.util.YeolDateUtil;

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
                .postNumber(ConstantNumber.INIT_COUNT)
                .createMs(YeolDateUtil.getCurrentMillis())
                .updateMs(YeolDateUtil.getCurrentMillis())
                .deleteFlag(false)
                .build();
        
        member = memberRepo.save(member);
        return Optional.ofNullable(MemberDto.valueOf(member));
    }
    
    public boolean isMemberExisted(String name, String email) {
        var memberList = memberRepo.findByNameOrEmailAndDeleteFlagFalse(name, email);
        if (CollectionUtils.isEmpty(memberList)) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean isEmailExisted(String email) {
        var memberList = memberRepo.findByEmailAndDeleteFlagFalse(email);
        if (CollectionUtils.isEmpty(memberList)) {
            return false;
        } else {
            return true;
        }
    }
    
    public Optional<MemberDto> updateMember(UpdateMemberRequest request){
        
        if (isEmailExisted(request.getEmail())) {
            throw new YeolException(ErrCode.EMAIL_EXISTED);
        }
        
        return memberRepo.findById(request.getId()).map(e -> {
            e.setEmail(request.getEmail());
//            e.setPassword(passwordEncoder.encode(request.getPassword()));
            e.setUpdateMs(YeolDateUtil.getCurrentMillis());
            e.setPostNumber(request.getPostNumber());
            return MemberDto.valueOf(memberRepo.save(e));
        });
    }

    @Override
    public Optional<MemberDto> deleteMember(DeleteMemberRequest request) {
        return memberRepo.findById(request.getId())
        .map(e -> {
            e.setUpdateMs(YeolDateUtil.getCurrentMillis());
            e.setDeleteFlag(true);
            return MemberDto.valueOf(memberRepo.save(e));
        });
    }

}
