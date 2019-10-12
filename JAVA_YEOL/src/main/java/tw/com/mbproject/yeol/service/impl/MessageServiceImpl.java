package tw.com.mbproject.yeol.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tw.com.mbproject.yeol.common.service.BizService;
import tw.com.mbproject.yeol.constant.ConstantNumber;
import tw.com.mbproject.yeol.controller.request.CreateMessageRequest;
import tw.com.mbproject.yeol.controller.request.DeleteMessageRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMessageRequest;
import tw.com.mbproject.yeol.dto.MessageDto;
import tw.com.mbproject.yeol.entity.Message;
import tw.com.mbproject.yeol.repo.MessageRepo;
import tw.com.mbproject.yeol.service.MessageService;

@Service
public class MessageServiceImpl extends BizService implements MessageService {
    
    @Autowired
    private MessageRepo messageRepo;

    @Override
    public List<MessageDto> getAllMessages() {
        return messageRepo.findAll().stream().map(MessageDto::valueOf).collect(Collectors.toList());

    }

    /**
     * Add new message
     */
    @Override
    public Optional<MessageDto> addMessage(CreateMessageRequest request) {
        Message message = Message.builder()
                .id(ObjectId.get().toHexString())
                .title(request.getTitle())
                .content(request.getContent())
                .view(ConstantNumber.INIT_COUNT)
                .up(ConstantNumber.INIT_COUNT)
                .down(ConstantNumber.INIT_COUNT)
                .createMs(System.currentTimeMillis())
                .updateMs(System.currentTimeMillis())
                .deleteFlag(Boolean.FALSE).build();
        
        message = messageRepo.save(message);
        
        return Optional.ofNullable(MessageDto.valueOf(message));
    }


    @Override
    public List<MessageDto> getPagedMessages(int page, int size) {
        if(page < 0 || size < 0) {
            return Collections.emptyList();
        }
        
        Page<Message> pageResult = messageRepo.findAll(
                PageRequest.of(page, size, Sort.by("createMs").descending()));

        return pageResult.getContent().stream().map(MessageDto::valueOf)
                .collect(Collectors.toList());

    }
    
    /**
     * Update message title and content
     */
    public Optional<MessageDto> updateMessageContent(UpdateMessageRequest request) {
        
        return messageRepo.findById(request.getId()).flatMap(e -> {
            e.setTitle(request.getTitle());
            e.setContent(request.getContent());
            e.setUpdateMs(System.currentTimeMillis());
            return Optional.ofNullable(messageRepo.save(e));
        }).map(MessageDto::valueOf);
        
    }
    
    /**
     * Delete message
     */
    public Optional<MessageDto> deleteMessage(DeleteMessageRequest request) {
        return messageRepo.findById(request.getId()).map(e -> {
            messageRepo.delete(e);
            return MessageDto.valueOf(e);
        });
    }

}
