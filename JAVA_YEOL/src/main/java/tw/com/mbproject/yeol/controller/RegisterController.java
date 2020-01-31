package tw.com.mbproject.yeol.controller;

import javax.validation.Valid;

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
    public Mono<YeolResponse<MemberDto>> createMember(
            @Valid @RequestBody CreateMemberRequest request) throws Exception {
        
        var memberDto = memberService.addMember(request);
        return memberDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }

}
