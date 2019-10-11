package tw.com.mbproject.yeol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.CreateMessageRequest;
import tw.com.mbproject.yeol.controller.response.CreateMessageResponse;
import tw.com.mbproject.yeol.controller.response.GetAllMessagesResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MessageDto;
import tw.com.mbproject.yeol.entity.Message;
import tw.com.mbproject.yeol.service.MessageService;

@RestController
@RequestMapping(value="/api/messages")
public class MessageController {
    
    private static final int PAGE_SIZE = 20;
    
    @Autowired
    private MessageService messageService;
    
    @GetMapping("/test") 
    public Mono<String> test() {
        return Mono.just(new String("hello world"));
    }
    
    @GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<GetAllMessagesResponse> getAllMessages() {
        List<MessageDto> messageDto = messageService.getAllMessages();
        return Mono.just(new GetAllMessagesResponse.Builder().messages(messageDto).build(ErrCode.SUCCESS));
    }
    
    @GetMapping(value="/page/{page}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<GetAllMessagesResponse> getPagedMessages(@PathVariable("page") Integer page) {
        List<MessageDto> messageDtoList = messageService.getPagedMessages(page, PAGE_SIZE);
        return Mono.just(new GetAllMessagesResponse.Builder().messages(messageDtoList).build(ErrCode.SUCCESS));
    }
    
    @PostMapping(value="/add", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<CreateMessageResponse> createMessage(@RequestBody CreateMessageRequest request) {
        MessageDto messageDto = messageService.add(request);
        return Mono.just(new CreateMessageResponse.Builder().message(messageDto).build(ErrCode.SUCCESS));
    }
    
}
