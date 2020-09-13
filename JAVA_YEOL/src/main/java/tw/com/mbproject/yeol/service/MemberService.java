package tw.com.mbproject.yeol.service;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.request.DeleteRequest;
import tw.com.mbproject.yeol.controller.request.QueryMemberRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMemberRequest;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.dto.PageDto;

import java.util.List;

public interface MemberService {

    Mono<List<MemberDto>> getAllMembers();

    Mono<PageDto<List<MemberDto>>> getPagedMembers(Integer page, Integer pageSize);

    Mono<MemberDto> getMember(QueryMemberRequest reqeust);

    Mono<MemberDto> addMember(CreateMemberRequest request);

    Mono<MemberDto> updateMember(UpdateMemberRequest request);

    Mono<MemberDto> deleteMember(DeleteRequest request);

}
