package com.example.springboot;

import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MessageController {

	private final AtomicLong counter = new AtomicLong();
    private static Map<Long, Message> hashOfMessages = new HashMap<Long, Message>();

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/message/{id}")
	public Message one(@PathVariable Long id) {
        if(!hashOfMessages.containsKey(id)) {
            throw new MessageNotFoundException(id);
        } 
        return hashOfMessages.get(id);
	}

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/messages")
    List<Message> all() {
        return new ArrayList<Message>(hashOfMessages.values());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/messages")
    Message newMessage(@RequestBody Message paramMessage) {
        Message newMessage = new Message(counter.incrementAndGet(), paramMessage.getContent(), paramMessage.getDeadline(), paramMessage.getTags(), paramMessage.getLink(), paramMessage.getIcon());
        hashOfMessages.put(counter.get(), newMessage);
        return hashOfMessages.get(counter.get());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/messages/{id}")
    Message replaceMessage(@RequestBody Message paramMessage, @PathVariable Long id) {
        if(!hashOfMessages.containsKey(id)) {
            throw new MessageNotFoundException(id);
        } 

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  

        Message message = hashOfMessages.get(id);
        message.setContent(paramMessage.getContent());
        message.setDeadline(paramMessage.getDeadline());
        message.setTags(paramMessage.getTags());
        message.setLink(paramMessage.getLink());
        message.setIcon(paramMessage.getIcon());
        message.setUpdatedAt(LocalDateTime.now());

        hashOfMessages.put(id, message);
        return hashOfMessages.get(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/messages/{id}")
    void deleteMessage(@PathVariable Long id) {
        if(!hashOfMessages.containsKey(id)) {
            throw new MessageNotFoundException(id);
        } 

        hashOfMessages.remove(id);
    }

}