package tw.com.mbproject.yeol.service;

import java.util.List;

import tw.com.mbproject.yeol.dto.MessageDto;
import tw.com.mbproject.yeol.entity.Message;

public interface MessageService {
    
    public List<MessageDto> getAllMessages();
    
    public List<MessageDto> getPagedMessages(int page, int size);
    
    public Message add(Message message);

}
