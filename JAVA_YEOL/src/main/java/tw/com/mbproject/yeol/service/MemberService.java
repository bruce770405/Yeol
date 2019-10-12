package tw.com.mbproject.yeol.service;

import java.util.Optional;

import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.exception.YeolException;

public interface MemberService {
    
    Optional<MemberDto> addMember(CreateMemberRequest request) throws YeolException;

}
