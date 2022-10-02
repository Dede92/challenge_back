package com.example.springboot.service;

import java.util.List;
import java.util.Map;

import com.example.springboot.model.Message;

public interface MessageService {
    List<Message> searchMessages(String query);

    Message findById(Long messageId);

    List<Message> getAllMessages();

    Message createMessage(Message message);

    Message updateMessage(Long messageId, Message messageDetails);

    Map<String, Boolean> deleteMessage(Long messageId);
}
