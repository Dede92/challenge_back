package com.example.springboot.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.exception.MessageNotFoundException;
import com.example.springboot.model.Message;
import com.example.springboot.repository.MessageRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable(value = "id") Long messageId)
            throws MessageNotFoundException {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException(messageId));
        return ResponseEntity.ok().body(message);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/messages")
    List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/messages")
    Message createMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/messages/{id}")
    ResponseEntity<Message> updateMessage(@RequestBody Message messageDetails,
            @PathVariable(value = "id") Long messageId) throws MessageNotFoundException {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException(messageId));

        message.setContent(messageDetails.getContent());
        message.setDeadline(messageDetails.getDeadline());
        message.setTags(messageDetails.getTags());
        message.setLink(messageDetails.getLink());
        message.setIcon(messageDetails.getIcon());

        final Message updatedMessage = messageRepository.save(message);
        return ResponseEntity.ok(updatedMessage);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/messages/{id}")
    public Map<String, Boolean> deleteMessage(@PathVariable(value = "id") Long messageId)
            throws MessageNotFoundException {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException(messageId));

        messageRepository.delete(message);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}