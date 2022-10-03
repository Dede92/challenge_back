package com.example.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.common.query.SearchRequest;
import com.example.springboot.exception.MessageNotFoundException;
import com.example.springboot.model.Message;
import com.example.springboot.service.MessageService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable(value = "id") Long messageId)
            throws MessageNotFoundException {
        return ResponseEntity.ok().body(messageService.findById(messageId));
    }

    @GetMapping("/messages")
    List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping("/messages")
    Message createMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @PutMapping("/messages/{id}")
    ResponseEntity<Message> updateMessage(@RequestBody Message messageDetails,
            @PathVariable(value = "id") Long messageId) throws MessageNotFoundException {
        return ResponseEntity.ok(messageService.updateMessage(messageId, messageDetails));
    }

    @DeleteMapping("/messages/{id}")
    public Map<String, Boolean> deleteMessage(@PathVariable(value = "id") Long messageId)
            throws MessageNotFoundException {
        return messageService.deleteMessage(messageId);
    }

    @PostMapping(value = "/messages/search")
    public List<Message> search(@RequestBody SearchRequest request) {
        return messageService.searchMessage(request);
    }

}