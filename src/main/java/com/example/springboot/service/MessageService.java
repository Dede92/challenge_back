package com.example.springboot.service;

import java.util.List;
import java.util.Map;

import com.example.springboot.common.query.SearchRequest;
import com.example.springboot.model.Message;

public interface MessageService {
    
    Message findById(Long messageId);

    List<Message> getAllMessages();

    Message createMessage(Message message);

    Message updateMessage(Long messageId, Message messageDetails);

    Map<String, Boolean> deleteMessage(Long messageId);

    List<Message> searchMessage(SearchRequest request);
}
