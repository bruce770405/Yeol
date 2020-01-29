package tw.com.mbproject.yeol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.DeleteMemberRequest;
import tw.com.mbproject.yeol.controller.request.QueryMemberRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMemberRequest;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.controller.validation.FormatRegex;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.properties.GenericProperties;
import tw.com.mbproject.yeol.service.MemberService;

@RestController(value = "/api/members")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    /** 取得全部會員(含已刪除) */
    @GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<List<MemberDto>>> getAllMembers() {
        var memberDtoList = memberService.getAllMembers();
        return Mono.just(new YeolResponse<>(memberDtoList, ErrCode.SUCCESS));
    }
    
    /** 取得分頁會員 */
    @GetMapping(value="/page/{page}", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<List<MemberDto>>> getPagedMembers(@PathVariable("page") Integer page) {
        var memberDtoList = memberService.getPagedMembers(page, GenericProperties.PAGE_SIZE);
        return Mono.just(new YeolResponse<>(memberDtoList, ErrCode.SUCCESS));
    }
    
    /** 取得會員 */
    @PostMapping(value="/one", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MemberDto>> getMember(
            @RequestBody QueryMemberRequest request) {
        if (FormatRegex.ID_FORMAT.isNotValid(request.getId())
                && FormatRegex.MEMBER_EMAIL_FORMAT.isNotValid(request.getEmail())) {
            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_FORMAT));
        }
        
        var memberDto = memberService.getMember(request);
        return memberDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
    /** 修改會員 */
    @PatchMapping(value="/update", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MemberDto>> updateMember (@RequestBody UpdateMemberRequest request) throws Exception {
        if (FormatRegex.MEMBER_EMAIL_FORMAT.isNotValid(request.getEmail())) {
            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_MEMBER_EMAIL_FORMAT));
        }
        
//        if (Regex.MEMBER_PASSWORD_FORMAT.isNotValid(request.getPassword())) {
//            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_MEMBER_PASSWORD_FORMAT));
//        }
        
        var memberDto = memberService.updateMember(request);
        return memberDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
    /** 刪除會員 */
    @DeleteMapping(value="/delete", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MemberDto>> deleteMember (@RequestBody DeleteMemberRequest request) throws Exception {
        if (FormatRegex.ID_FORMAT.isNotValid(request.getId())) {
            return Mono.just(new YeolResponse<>(ErrCode.INCORRECT_FORMAT));
        }
        
        var memberDto = memberService.deleteMember(request);
        return memberDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
}
