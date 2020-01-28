package tw.com.mbproject.yeol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MessageDto;
import tw.com.mbproject.yeol.service.MessageService;

@RestController
@RequestMapping(value="/api/messages")
public class MessageController {
    
    private static int pageSize;
    
    @Autowired
    private MessageService messageService;
    
    @Value("${yeol.message.page.size}")
    public void setPageSize(int pageSize) {
        MessageController.pageSize = pageSize;
    }
    
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
    @GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<List<MessageDto>>> getAllMessages() {
        var messageDtoList = messageService.getAllMessages();
        return Mono.just(new YeolResponse<>(messageDtoList,ErrCode.SUCCESS));
    }
    
    /**
     * Get top view messages
     * @param recordNumber fetch records number, max is 10;
     * @return
     */
    @GetMapping(value="/top-view/{recordNumber}", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<List<MessageDto>>> getTopViewMessages(
            @PathVariable(name = "recordNumber") Integer recordNumber) {
        if (recordNumber > 10) {
            recordNumber = 10;
        }
        
        var messageDtoList = messageService.getTopViewsMessages(recordNumber);
        return Mono.just(new YeolResponse<>(messageDtoList,ErrCode.SUCCESS));
    }
    
    /**
     * Get paginate messages
     */
    @GetMapping(value="/page/{page}", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<List<MessageDto>>> getPagedMessages(@PathVariable("page") Integer page) {
        var pageDto = messageService.getPagedMessages(page, pageSize);
        return Mono.just(new YeolResponse<>(pageDto,ErrCode.SUCCESS));
    }
    
    /**
     * Add new message
     */
    @PostMapping(value="/add", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MessageDto>> createMessage(@RequestBody CreateMessageRequest request) {
        var messageDto = messageService.addMessage(request);
        return messageDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
    /**
     * Update message title and content
     */
    @PatchMapping(value="/update", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MessageDto>> createMessage(@RequestBody UpdateMessageRequest request) {
        var messageDto = messageService.updateMessageContent(request);
        return messageDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
    /**
     * Delete message
     */
    @DeleteMapping(value="/delete", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MessageDto>> deleteMessage(@RequestBody DeleteMessageRequest request) {
        var messageDto = messageService.deleteMessage(request);
        return messageDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
}
