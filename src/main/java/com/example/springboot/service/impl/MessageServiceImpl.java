package com.example.springboot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.springboot.exception.MessageNotFoundException;
import com.example.springboot.model.Message;
import com.example.springboot.repository.MessageRepository;
import com.example.springboot.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> searchMessages(String query) {
        List<Message> messages = messageRepository.searchMessage(query);
        return messages;
    }

    @Override
    public Message findById(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException(messageId));
        return message;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Long messageId, Message messageDetails) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException(messageId));

        message.setContent(messageDetails.getContent());
        message.setDeadline(messageDetails.getDeadline());
        message.setTags(messageDetails.getTags());
        message.setLink(messageDetails.getLink());
        message.setIcon(messageDetails.getIcon());

        final Message updatedMessage = messageRepository.save(message);
        return updatedMessage;
    }

    @Override
    public Map<String, Boolean> deleteMessage(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException(messageId));

        messageRepository.delete(message);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
