package tw.com.mbproject.yeol.service;

import java.util.Optional;

import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.request.DeleteMemberRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMemberRequest;
import tw.com.mbproject.yeol.dto.MemberDto;

public interface MemberService {
    
    Optional<MemberDto> addMember(CreateMemberRequest request);
    Optional<MemberDto> updateMember(UpdateMemberRequest request);
    Optional<MemberDto> deleteMember(DeleteMemberRequest request);

}
