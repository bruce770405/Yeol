package tw.com.mbproject.yeol.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.CreateMemberRequest;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
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
    public Mono<YeolResponse> createMessage(@RequestBody CreateMemberRequest request) {
        Optional<MemberDto> messageDto = memberService.addMember(request);
//        return messageDto.map(e -> Mono.just(ModifyMessageResponse.builder().message(e).build(ErrCode.SUCCESS))).orElse(Mono.just(ModifyMessageResponse.builder().build(ErrCode.FAIL)));
        return null;
    }
    
    

}
