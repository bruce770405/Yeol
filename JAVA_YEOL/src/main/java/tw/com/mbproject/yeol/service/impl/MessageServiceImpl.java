package tw.com.mbproject.yeol.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.common.service.BizService;
import tw.com.mbproject.yeol.constant.ConstantNumber;
import tw.com.mbproject.yeol.controller.request.CreateMessageRequest;
import tw.com.mbproject.yeol.controller.request.DeleteRequest;
import tw.com.mbproject.yeol.controller.request.UpdateMessageRequest;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MessageDto;
import tw.com.mbproject.yeol.dto.PageDto;
import tw.com.mbproject.yeol.entity.Message;
import tw.com.mbproject.yeol.exception.YeolException;
import tw.com.mbproject.yeol.repo.MessageRepo;
import tw.com.mbproject.yeol.service.MessageService;
import tw.com.mbproject.yeol.util.YeolDateUtil;

import java.util.List;

@Service
public class MessageServiceImpl extends BizService implements MessageService {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public Mono<List<MessageDto>> getAllMessages() {
        return messageRepo.findAll().map(MessageDto::valueOf).collectList();
    }

    /**
     * Get yesterday's top view messages
     */
    @Override
    public Mono<List<MessageDto>> getTopViewsMessages(Integer recordNumber) {
        Long yesterdayMillis = YeolDateUtil.getYesterdayMillis();

        var pageable = PageRequest.of(0, recordNumber, Sort.by("view").descending());
        return messageRepo.findByDeleteFlagFalseAndCreateMsGreaterThanEqual(yesterdayMillis, pageable)
                .map(MessageDto::valueOf).collectList();
    }

    /**
     * Add new message
     */
    @Override
    public Mono<MessageDto> addMessage(CreateMessageRequest request) {
        return Mono.just(Message.builder()
                .id(ObjectId.get().toHexString())
                .title(request.getTitle())
                .content(request.getContent())
                .view(ConstantNumber.INIT_COUNT)
                .up(ConstantNumber.INIT_COUNT)
                .down(ConstantNumber.INIT_COUNT)
                .createMs(YeolDateUtil.getCurrentMillis())
                .updateMs(YeolDateUtil.getCurrentMillis())
                .deleteFlag(false)
                .build())
                .doOnNext(messageRepo::save)
                .map(MessageDto::valueOf);
    }


    @Override
    public Mono<PageDto<List<MessageDto>>> getPagedMessages(int page, int size) {
        if (page < 0 || size < 1) {
            throw new YeolException(ErrCode.INCORRECT_PAGE_FORMAT);
        }

        var pageable = PageRequest.of(page, size, Sort.by("createMs").descending());
        return messageRepo.findByDeleteFlag(false, pageable)
                .map(MessageDto::valueOf).collectList()
                .map(messageDtoList -> {
                    PageDto<List<MessageDto>> pageDto = PageDto.empty();
                    pageDto.setData(messageDtoList);
                    return pageDto;
                });
    }

    /**
     * Update message title and content
     */
    public Mono<MessageDto> updateMessageContent(UpdateMessageRequest request) {
        return messageRepo.findById(request.getId())
                .switchIfEmpty(Mono.error(new YeolException(ErrCode.MEMBER_NOT_FOUND)))
                .doOnNext(message -> {
                    message.setTitle(request.getTitle());
                    message.setContent(request.getContent());
                    message.setUpdateMs(YeolDateUtil.getCurrentMillis());
                    messageRepo.save(message);
                }).map(MessageDto::valueOf);
    }

    /**
     * Delete message
     */
    public Mono<MessageDto> deleteMessage(DeleteRequest request) {
        return messageRepo.findById(request.getId())
                .switchIfEmpty(Mono.error(new YeolException(ErrCode.MEMBER_NOT_FOUND)))
                .doOnNext(message -> {
                    message.setUpdateMs(YeolDateUtil.getCurrentMillis());
                    message.setDeleteFlag(true);
                    messageRepo.save(message);
                })
                .map(MessageDto::valueOf);
    }

}
