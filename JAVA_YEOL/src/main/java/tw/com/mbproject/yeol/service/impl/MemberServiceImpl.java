package tw.com.mbproject.yeol.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import tw.com.mbproject.yeol.common.service.BizService;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.service.MemberService;

@Service
public class MemberServiceImpl extends BizService implements MemberService {

    @Override
    public Optional<MemberDto> addMember(CreateMemberRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}
