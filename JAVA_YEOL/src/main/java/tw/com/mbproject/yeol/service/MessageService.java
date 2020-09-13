package tw.com.mbproject.yeol.service;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.CreateMessageRequest;
import tw.com.mbproject.yeol.controller.request.DeleteRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMessageRequest;
import tw.com.mbproject.yeol.dto.MessageDto;
import tw.com.mbproject.yeol.dto.PageDto;

import java.util.List;

public interface MessageService {

    Mono<List<MessageDto>> getAllMessages();

    Mono<List<MessageDto>> getTopViewsMessages(Integer recordNumber);

    Mono<PageDto<List<MessageDto>>> getPagedMessages(int page, int size);

    Mono<MessageDto> addMessage(CreateMessageRequest request);

    Mono<MessageDto> updateMessageContent(UpdateMessageRequest request);

    Mono<MessageDto> deleteMessage(DeleteRequest request);

}
