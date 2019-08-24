package tw.com.mbproject.yeol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.mbproject.yeol.entity.Message;
import tw.com.mbproject.yeol.service.MessageService;

@RestController
@RequestMapping(value="/api/messages")
public class MessageController {
    
    @Autowired
    private MessageService messageService;
    
    /**
     * 查詢全部
     */
    @GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Message> getAllCustomers() {
        return messageService.getAllMessages();
    }
    
    /**
     * 新增一筆資料
     */
    @PostMapping(value="/add", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Message createCustomer(@RequestBody Message message) {
        messageService.add(message);
        return message;
    }

}
