package tw.com.mbproject.yeol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.CreateMessageRequest;
import tw.com.mbproject.yeol.controller.request.DeleteRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMessageRequest;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MessageDto;
import tw.com.mbproject.yeol.properties.GenericProperties;
import tw.com.mbproject.yeol.service.MessageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/api/messages")
public class MessageController {
    
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
        var pageDto = messageService.getPagedMessages(page, GenericProperties.PAGE_SIZE);
        return Mono.just(new YeolResponse<>(pageDto,ErrCode.SUCCESS));
    }
    
    /**
     * Add new message
     */
    @PostMapping(value="/add", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MessageDto>> createMessage(
            @Valid @RequestBody CreateMessageRequest request) {
        var messageDto = messageService.addMessage(request);
        return messageDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
    /**
     * Update message title and content
     */
    @PatchMapping(value="/update", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MessageDto>> createMessage(
            @Valid @RequestBody UpdateMessageRequest request) {
        var messageDto = messageService.updateMessageContent(request);
        return messageDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
    /**
     * Delete message
     */
    @DeleteMapping(value="/delete", produces=MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MessageDto>> deleteMessage(
            @Valid @RequestBody DeleteRequest request) {
        
        var messageDto = messageService.deleteMessage(request);
        return messageDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
}
