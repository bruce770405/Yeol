package tw.com.mbproject.yeol.service;

import java.util.List;

import tw.com.mbproject.yeol.entity.Message;

public interface MessageService {
    
    public List<Message> getAllMessages();
    
    public Message add(Message message);

}
