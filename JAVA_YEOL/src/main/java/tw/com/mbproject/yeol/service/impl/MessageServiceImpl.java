package tw.com.mbproject.yeol.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import tw.com.mbproject.yeol.common.service.BizService;
import tw.com.mbproject.yeol.constant.ConstantNumber;
import tw.com.mbproject.yeol.controller.request.CreateMessageRequest;
import tw.com.mbproject.yeol.controller.request.DeleteMessageRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMessageRequest;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MessageDto;
import tw.com.mbproject.yeol.dto.PageDto;
import tw.com.mbproject.yeol.entity.Message;
import tw.com.mbproject.yeol.exception.YeolException;
import tw.com.mbproject.yeol.repo.MessageRepo;
import tw.com.mbproject.yeol.service.MessageService;
import tw.com.mbproject.yeol.util.YeolDateUtil;

@Service
public class MessageServiceImpl extends BizService implements MessageService {
    
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<MessageDto> getAllMessages() {
        return messageRepo.findAll().stream().map(MessageDto::valueOf).collect(Collectors.toList());

    }
    
    /**
     * Get yesterday's top view messages
     */
    public List<MessageDto> getTopViewsMessages(Integer recordNumber) {
        Long yeseterdayMillis = YeolDateUtil.getYesterdayMillis();
        
        var pageable = PageRequest.of(0, recordNumber, Sort.by("view").descending());
        var pageResult = messageRepo.findByDeleteFlagFalseAndCreateMsGreaterThanEqual(yeseterdayMillis, pageable);
        
        return pageResult.getContent().stream()
                .map(MessageDto::valueOf)
                .collect(Collectors.toList());
        
    }

    /**
     * Add new message
     */
    @Override
    public Optional<MessageDto> addMessage(CreateMessageRequest request) {
        var message = Message.builder()
                .id(ObjectId.get().toHexString())
                .title(request.getTitle())
                .content(request.getContent())
                .view(ConstantNumber.INIT_COUNT)
                .up(ConstantNumber.INIT_COUNT)
                .down(ConstantNumber.INIT_COUNT)
                .createMs(YeolDateUtil.getCurrentMillis())
                .updateMs(YeolDateUtil.getCurrentMillis())
                .deleteFlag(false).build();
        
        message = messageRepo.save(message);
        
        return Optional.ofNullable(MessageDto.valueOf(message));
    }


    @Override
    public PageDto<List<MessageDto>> getPagedMessages(int page, int size) {
        if(page < 0 || size < 1) {
            throw new YeolException(ErrCode.INCORRECT_PAGE_FORMAT);
        }
        
        var pageable = PageRequest.of(page, size, Sort.by("createMs").descending());
        var pageResult = messageRepo.findByDeleteFlag(false, pageable);

        List<MessageDto> messageDtoList = pageResult.getContent().stream()
                .map(MessageDto::valueOf)
                .collect(Collectors.toList());
        
        PageDto<List<MessageDto>> pageDto = new PageDto<>(pageResult);
        pageDto.setData(messageDtoList);
        return pageDto;

    }
    
    /**
     * Update message title and content
     */
    public Optional<MessageDto> updateMessageContent(UpdateMessageRequest request) {
        
        return messageRepo.findById(request.getId()).map(e -> {
            e.setTitle(request.getTitle());
            e.setContent(request.getContent());
            e.setUpdateMs(YeolDateUtil.getCurrentMillis());
            return MessageDto.valueOf(messageRepo.save(e));
        });
        
    }
    
    /**
     * Delete message
     */
    public Optional<MessageDto> deleteMessage(DeleteMessageRequest request) {
        return messageRepo.findById(request.getId()).map(e -> {
            e.setUpdateMs(YeolDateUtil.getCurrentMillis());
            e.setDeleteFlag(true);
            return MessageDto.valueOf(messageRepo.save(e));
        });
    }

}
