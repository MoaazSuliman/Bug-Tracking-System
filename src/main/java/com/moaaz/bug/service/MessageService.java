package com.moaaz.bug.service;

import com.moaaz.bug.model.Message;
import com.moaaz.bug.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepo;

    public void saveMessage(Message message) {
        messageRepo.save(message);
    }

//    public List<Message> getAllMessageForPersonThatHeSentIt(int senderId) {
//        return messageRepo.findBySenderId(developer_id);
//    }

}
