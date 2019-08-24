package tw.com.mbproject.yeol.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.mbproject.yeol.entity.Message;
import tw.com.mbproject.yeol.repo.MessageRepo;
import tw.com.mbproject.yeol.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepo messageRepo;

    @Override
    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    @Override
    public Message add(Message message) {
        message.setId(ObjectId.get().toHexString());
        messageRepo.save(message);
        return message;
    }

}
