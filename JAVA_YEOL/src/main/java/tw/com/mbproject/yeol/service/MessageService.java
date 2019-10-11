package tw.com.mbproject.yeol.service;

import java.util.List;

import tw.com.mbproject.yeol.controller.request.CreateMessageRequest;
import tw.com.mbproject.yeol.dto.MessageDto;

public interface MessageService {

    List<MessageDto> getAllMessages();

    List<MessageDto> getPagedMessages(int page, int size);

    MessageDto add(CreateMessageRequest request);

}
