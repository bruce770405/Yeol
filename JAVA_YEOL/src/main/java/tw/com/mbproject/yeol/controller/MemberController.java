package tw.com.mbproject.yeol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.DeleteMemberRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMemberRequest;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.controller.validation.Regex;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.service.MemberService;
import tw.com.mbproject.yeol.util.YeolNumUtil;

@RestController(value = "/api/members")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    @PatchMapping(value="/update", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MemberDto>> updateMember (@RequestBody UpdateMemberRequest request) throws Exception {
        if (Regex.MEMBER_EMAIL_FORMAT.isNotValid(request.getEmail())) {
            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_MEMBER_EMAIL_FORMAT));
        }
        
//        if (Regex.MEMBER_PASSWORD_FORMAT.isNotValid(request.getPassword())) {
//            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_MEMBER_PASSWORD_FORMAT));
//        }
        
        var memberDto = memberService.updateMember(request);
        return memberDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
    @DeleteMapping(value="/delete", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MemberDto>> deleteMember (@RequestBody DeleteMemberRequest request) throws Exception {
        if (Regex.ID_FORMAT.isNotValid(request.getId())) {
            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_FORMAT));
        }
        
        var memberDto = memberService.deleteMember(request);
        return memberDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
}
