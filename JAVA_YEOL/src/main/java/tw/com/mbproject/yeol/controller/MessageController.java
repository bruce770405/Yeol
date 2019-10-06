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
    
    @GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GetAllMessagesResponse getAllMessages() {
        List<MessageDto> messages = messageService.getAllMessages();
        return new GetAllMessagesResponse.Builder().messages(messages).build(ErrCode.SUCCESS);
    }
    
    @GetMapping(value="/page/{page}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GetAllMessagesResponse getPagedMessages(@PathVariable("page") Integer page) {
        List<MessageDto> messages = messageService.getPagedMessages(page, PAGE_SIZE);
        return new GetAllMessagesResponse.Builder().messages(messages).build(ErrCode.SUCCESS);
    }
    
    @PostMapping(value="/add", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Message createMessage(@RequestBody Message message) {
        messageService.add(message);
        return message;
    }
    
}
