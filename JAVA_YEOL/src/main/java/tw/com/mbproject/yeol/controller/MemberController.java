package tw.com.mbproject.yeol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.DeleteRequest;
import tw.com.mbproject.yeol.controller.request.QueryMemberRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMemberRequest;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.controller.validation.FormatRegex;
import tw.com.mbproject.yeol.dto.MemberDto;
import tw.com.mbproject.yeol.exception.YeolException;
import tw.com.mbproject.yeol.properties.GenericProperties;
import tw.com.mbproject.yeol.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 取得全部會員(含已刪除)
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<List<MemberDto>>> getAllMembers() {
        return memberService.getAllMembers().map(memberDto -> new YeolResponse<>(memberDto, ErrCode.SUCCESS));
    }

    /**
     * 取得分頁會員
     */
    @GetMapping(value = "/page/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<List<MemberDto>>> getPagedMembers(@PathVariable("page") Integer page) {
        return memberService.getPagedMembers(page, GenericProperties.PAGE_SIZE).map(pageData -> new YeolResponse<>(pageData, ErrCode.SUCCESS));
    }

    /**
     * 取得會員
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MemberDto>> getMember(@PathVariable("id") String id, @Param("email") String email) {

        if (FormatRegex.ID_FORMAT.isNotValid(id) && FormatRegex.EMAIL_FORMAT.isNotValid(email)) {
            throw new YeolException(ErrCode.INCORRECT_FORMAT);
        }

        var request = QueryMemberRequest.builder().id(id).email(email).build();
        return memberService.getMember(request).map(e -> new YeolResponse<>(e, ErrCode.SUCCESS));
    }

    /**
     * 修改會員
     */
    @PatchMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MemberDto>> updateMember(@Valid @RequestBody UpdateMemberRequest request) {
        return memberService.updateMember(request).map(e -> new YeolResponse<>(e, ErrCode.SUCCESS));
    }

    /**
     * 刪除會員
     */
    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MemberDto>> deleteMember(@Valid @RequestBody DeleteRequest request) {
        return memberService.deleteMember(request).map(e -> new YeolResponse<>(e, ErrCode.SUCCESS));
    }

}
