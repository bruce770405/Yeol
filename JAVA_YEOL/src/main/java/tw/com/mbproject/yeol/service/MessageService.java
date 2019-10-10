package tw.com.mbproject.yeol.service;

import java.util.List;

import tw.com.mbproject.yeol.dto.MessageDto;
import tw.com.mbproject.yeol.entity.Message;

public interface MessageService {

    List<MessageDto> getAllMessages();

    List<MessageDto> getPagedMessages(int page, int size);

    Message add(Message message);

}
