package tw.com.mbproject.yeol.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tw.com.mbproject.yeol.common.service.BizService;
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

    @Override
    public Message add(Message message) {
        message.setId(ObjectId.get().toHexString());
        messageRepo.save(message);
        return message;
    }


    @Override
    public List<MessageDto> getPagedMessages(int page, int size) {
        Page<Message> pageResult = messageRepo.findAll(
                PageRequest.of(page, size, Sort.by("createMs").descending()));

        return pageResult.getContent().stream().map(MessageDto::valueOf)
                .collect(Collectors.toList());

    }

}
