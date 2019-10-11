package tw.com.mbproject.yeol.service;

import java.util.Optional;

import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.dto.MemberDto;

public interface MemberService {
    
    Optional<MemberDto> addMember(CreateMemberRequest request);

}