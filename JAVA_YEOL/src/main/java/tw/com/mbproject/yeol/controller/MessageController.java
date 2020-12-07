package tw.com.mbproject.yeol.controller;

import lombok.extern.log4j.Log4j2;
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
@RequestMapping(value = "/api/messages")
@Log4j2
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Get All messages of this system, for test only.
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<List<MessageDto>>> getAllMessages() {
        return messageService.getAllMessages().map(messageDtoList -> new YeolResponse<>(messageDtoList, ErrCode.SUCCESS));
    }

    /**
     * Get top view messages
     *
     * @param recordNumber fetch records number, max is 10;
     */
    @GetMapping(value = "/top-view/{recordNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<List<MessageDto>>> getTopViewMessages(@PathVariable(name = "recordNumber") Integer recordNumber) {
        if (recordNumber > 10) {
            recordNumber = 10;
        }
        return messageService.getTopViewsMessages(recordNumber).map(messageDtoList -> new YeolResponse<>(messageDtoList, ErrCode.SUCCESS));
    }

    /**
     * Get paginate messages
     */
    @GetMapping(value = "/page/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<List<MessageDto>>> getPagedMessages(@PathVariable("page") Integer page) {
        log.debug("getPagedMessages ...");
        return messageService.getPagedMessages(page, GenericProperties.PAGE_SIZE).map(pageDto -> new YeolResponse<>(pageDto, ErrCode.SUCCESS));
    }

    /**
     * Add new message
     */
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MessageDto>> createMessage(@Valid @RequestBody CreateMessageRequest request) {
        return messageService.addMessage(request).map(messageDto -> new YeolResponse<>(messageDto, ErrCode.SUCCESS));
    }

    /**
     * Update message title and content
     */
    @PatchMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MessageDto>> createMessage(@Valid @RequestBody UpdateMessageRequest request) {
        return messageService.updateMessageContent(request).map(messageDto -> new YeolResponse<>(messageDto, ErrCode.SUCCESS));
    }

    /**
     * Delete message
     */
    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<YeolResponse<MessageDto>> deleteMessage(@Valid @RequestBody DeleteRequest request) {
        return messageService.deleteMessage(request).map(messageDto -> new YeolResponse<>(messageDto, ErrCode.SUCCESS));
    }

}
