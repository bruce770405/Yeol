package tw.com.mbproject.yeol.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.CreateMessageRequest;
import tw.com.mbproject.yeol.controller.request.DeleteMessageRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMessageRequest;
import tw.com.mbproject.yeol.controller.response.GetMessagesResponse;
import tw.com.mbproject.yeol.controller.response.ModifyMessageResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MessageDto;
import tw.com.mbproject.yeol.service.MessageService;

@RestController
@RequestMapping(value="/api/messages")
public class MessageController {
    
    private static final int PAGE_SIZE = 20;
    
    @Autowired
    private MessageService messageService;
    
    /**
     * Just for test
     */
    @GetMapping("/test") 
    public Mono<String> test() {
        return Mono.just(new String("hello world"));
    }
    
    /**
     * Get All messages of this system, for test only.
     */
    @GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<GetMessagesResponse> getAllMessages() {
        List<MessageDto> messageDto = messageService.getAllMessages();
        return Mono.just(new GetMessagesResponse.Builder().messages(messageDto).build(ErrCode.SUCCESS));
    }
    
    /**
     * Get paginate messages
     */
    @GetMapping(value="/page/{page}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<GetMessagesResponse> getPagedMessages(@PathVariable("page") Integer page) {
        List<MessageDto> messageDtoList = messageService.getPagedMessages(page, PAGE_SIZE);
        return Mono.just(new GetMessagesResponse.Builder().messages(messageDtoList).build(ErrCode.SUCCESS));
    }
    
    /**
     * Add new message
     */
    @PostMapping(value="/add", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<ModifyMessageResponse> createMessage(@RequestBody CreateMessageRequest request) {
        Optional<MessageDto> messageDto = messageService.addMessage(request);
        return messageDto.map(e -> Mono.just(ModifyMessageResponse.builder().message(e).build(ErrCode.SUCCESS))).orElse(Mono.just(ModifyMessageResponse.builder().build(ErrCode.FAIL)));
    }
    
    /**
     * Update message title and content
     */
    @PatchMapping(value="/update", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<ModifyMessageResponse> createMessage(@RequestBody UpdateMessageRequest request) {
        Optional<MessageDto> messageDto = messageService.updateMessageContent(request);
        return messageDto.map(e -> Mono.just(ModifyMessageResponse.builder().message(e).build(ErrCode.SUCCESS))).orElse(Mono.just(ModifyMessageResponse.builder().build(ErrCode.FAIL)));
    }
    
    /**
     * Delete message
     */
    @DeleteMapping(value="/delete", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<ModifyMessageResponse> deleteMessage(@RequestBody DeleteMessageRequest request) {
        Optional<MessageDto> messageDto = messageService.deleteMessage(request);
        return messageDto.map(e -> Mono.just(ModifyMessageResponse.builder().message(e).build(ErrCode.SUCCESS))).orElse(Mono.just(ModifyMessageResponse.builder().build(ErrCode.FAIL)));
    }
    
}
