package tw.com.mbproject.yeol.service;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.request.LoginMemberRequest;
import tw.com.mbproject.yeol.dto.LoginDto;
import tw.com.mbproject.yeol.dto.LoginKxyDto;
import tw.com.mbproject.yeol.dto.MemberDto;

public interface RegisterService {

    Mono<LoginKxyDto> getMemberPublicKxy();

    Mono<MemberDto> addMember(CreateMemberRequest request);

    Mono<LoginDto> login(LoginMemberRequest request) throws Exception;

}
