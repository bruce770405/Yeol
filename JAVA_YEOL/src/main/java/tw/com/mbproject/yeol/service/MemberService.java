package tw.com.mbproject.yeol.service;

import java.util.List;
import java.util.Optional;

import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.request.DeleteMemberRequest;
import tw.com.mbproject.yeol.controller.request.QueryMemberRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMemberRequest;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.dto.PageDto;

public interface MemberService {
    
    List<MemberDto> getAllMembers();
    PageDto<List<MemberDto>> getPagedMembers(Integer page, Integer pageSize);
    Optional<MemberDto> getMember(QueryMemberRequest reqeust);
    Optional<MemberDto> addMember(CreateMemberRequest request);
    Optional<MemberDto> updateMember(UpdateMemberRequest request);
    Optional<MemberDto> deleteMember(DeleteMemberRequest request);

}
