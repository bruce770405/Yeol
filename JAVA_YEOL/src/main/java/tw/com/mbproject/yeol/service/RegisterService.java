package tw.com.mbproject.yeol.service;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.request.LoginMemberRequest;
import tw.com.mbproject.yeol.dto.MemberDto;

public interface RegisterService {

    Mono<MemberDto> addMember(CreateMemberRequest request);

    Mono<String> login(LoginMemberRequest request);

}
