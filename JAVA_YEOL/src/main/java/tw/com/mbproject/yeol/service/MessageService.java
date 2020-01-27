package tw.com.mbproject.yeol.service;

import java.util.List;
import java.util.Optional;

import tw.com.mbproject.yeol.controller.request.CreateMessageRequest;
import tw.com.mbproject.yeol.controller.request.DeleteMessageRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMessageRequest;
import tw.com.mbproject.yeol.dto.MessageDto;
import tw.com.mbproject.yeol.dto.PageDto;

public interface MessageService {

    List<MessageDto> getAllMessages();
    
    List<MessageDto> getTopViewsMessages(Integer recordNumber);

    PageDto<List<MessageDto>> getPagedMessages(int page, int size);

    Optional<MessageDto> addMessage(CreateMessageRequest request);
    
    Optional<MessageDto> updateMessageContent(UpdateMessageRequest request);
    
    Optional<MessageDto> deleteMessage(DeleteMessageRequest request);

}
