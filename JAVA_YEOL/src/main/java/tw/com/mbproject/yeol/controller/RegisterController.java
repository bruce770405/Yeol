package tw.com.mbproject.yeol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.controller.validation.FormatRegex;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.service.MemberService;

@RestController
@RequestMapping(value="/register")
public class RegisterController {
    
    @Autowired
    private MemberService memberService;
    
    /**
     * 新增會員
     */
    @PostMapping(value="/join", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MemberDto>> createMember(@RequestBody CreateMemberRequest request) throws Exception {
        
        if (FormatRegex.MEMBER_NAME_FORMAT.isNotValid(request.getName())) {
            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_MEMBER_NAME_FORMAT));
        }
        
        if (FormatRegex.MEMBER_EMAIL_FORMAT.isNotValid(request.getEmail())) {
            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_MEMBER_EMAIL_FORMAT));
        }
        
        if (FormatRegex.MEMBER_PASSWORD_FORMAT.isNotValid(request.getPassword())) {
            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_MEMBER_PASSWORD_FORMAT));
        }
        
        var memberDto = memberService.addMember(request);
        return memberDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }

}
