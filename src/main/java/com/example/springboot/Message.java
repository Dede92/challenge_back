package com.example.springboot;

import java.util.Objects;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

class Message {

  private Long id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String content;
  private LocalDateTime deadline;
  private String[] tags;
  private String link;
  private String icon;
  

  Message() {}

  Message(Long id, String content, LocalDateTime deadline, String[] tags, String link, String icon) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  

    this.id = id;
    this.content = content;
    this.createdAt = LocalDateTime.now();  
    this.updatedAt = LocalDateTime.now();
    this.deadline = deadline;
    this.tags = tags;
    this.link = link;
    this.icon = icon;
  }

  public Long getId() {
    return this.id;
  }

  public String getContent() {
    return this.content;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }


  public String[] getTags() {
    return this.tags;
  }

  public LocalDateTime getDeadline() {
    return this.deadline;
  }

  public String getLink() {
    return this.link;
  }

  public String getIcon() {
    return this.icon;
  }

  public Long setId(Long id) {
    return this.id = id;
  }

  public String setContent(String content) {
    return this.content = content;
  }

  public LocalDateTime setUpdatedAt(LocalDateTime updatedAt) {
    return this.updatedAt = updatedAt;
  }

  public LocalDateTime setCreatedAt(LocalDateTime createdAt) {
    return this.createdAt = createdAt;
  }

  public String[] setTags(String[] tags) {
    return this.tags = tags;
  }

  public LocalDateTime setDeadline(LocalDateTime deadline) {
    return this.deadline = deadline;
  }

  public String setLink(String link) {
    return this.link = link;
  }

  public String setIcon(String icon) {
    return this.icon =   icon;
  }

  @Override
  public String toString() {
    return "Message{" + "id=" + this.id + ", content='" + this.content + '\'' + ", createdAt='" + this.createdAt + '\'' + '}';
  }


}