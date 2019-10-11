package tw.com.mbproject.yeol.controller;

import java.util.List;
import java.util.Optional;

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
    @GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<YeolResponse<List<MessageDto>>> getAllMessages() {
        List<MessageDto> messageDtoList = messageService.getAllMessages();
        return Mono.just(new YeolResponse<>(messageDtoList,ErrCode.SUCCESS));
    }
    
    /**
     * Get paginate messages
     */
    @GetMapping(value="/page/{page}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<YeolResponse<List<MessageDto>>> getPagedMessages(@PathVariable("page") Integer page) {
        List<MessageDto> messageDtoList = messageService.getPagedMessages(page, pageSize);
        return Mono.just(new YeolResponse<>(messageDtoList,ErrCode.SUCCESS));
    }
    
    /**
     * Add new message
     */
    @PostMapping(value="/add", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<YeolResponse<MessageDto>> createMessage(@RequestBody CreateMessageRequest request) {
        Optional<MessageDto> messageDto = messageService.addMessage(request);
        return messageDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
    /**
     * Update message title and content
     */
    @PatchMapping(value="/update", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<YeolResponse<MessageDto>> createMessage(@RequestBody UpdateMessageRequest request) {
        Optional<MessageDto> messageDto = messageService.updateMessageContent(request);
        return messageDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
    /**
     * Delete message
     */
    @DeleteMapping(value="/delete", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<YeolResponse<MessageDto>> deleteMessage(@RequestBody DeleteMessageRequest request) {
        Optional<MessageDto> messageDto = messageService.deleteMessage(request);
        return messageDto.map(e -> Mono.just(new YeolResponse<>(e, ErrCode.SUCCESS)))
                .orElse(Mono.just(new YeolResponse<>()));
    }
    
}
