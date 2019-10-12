package tw.com.mbproject.yeol.controller;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.common.response.BaseResponse;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.service.MemberService;

@RestController
public class MemberController {
    
    
    @Autowired
    private MemberService memberService;
    
    
    /**
     * Add new member
     */
    @PostMapping(value="/join", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<YeolResponse<MemberDto>> createMember(@RequestBody CreateMemberRequest request) {
        
        String nameRegex = "^\\w{3,12}$";
        if (!Pattern.compile(nameRegex).matcher(request.getName()).find()) {
            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_MEMBER_NAME_FORMAT));
        }
        
        String emailRegex = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
        if (!Pattern.compile(emailRegex).matcher(request.getEmail()).find()) {
            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_MEMBER_EMAIL_FORMAT));
        }
        
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        if (!Pattern.compile(passwordRegex).matcher(request.getPassword()).find()) {
            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_MEMBER_PASSWORD_FORMAT));
        }
        
        
        Optional<MemberDto> memberDto = memberService.addMember(request);
        return memberDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
    

}
