package com.example.springboot.model;

import java.time.LocalDateTime;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "message")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false, updatable = false)
  private LocalDateTime updatedAt;

  @Column(name = "content", nullable = false)
  private String content;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @Column(name = "deadline", nullable = true)
  private LocalDate deadline;

  @Column(name = "tags", nullable = true)
  private String[] tags;

  @Column(name = "link", nullable = true)
  private String link;

  @OneToOne(mappedBy = "message", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  @JsonIgnore
  private MessageIcon messageIcon;

  Message() {
  }

  Message(String content, LocalDate deadline, String[] tags, String link) {
    this.content = content;
    this.deadline = deadline;
    this.tags = tags;
    this.link = link;
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

  public LocalDate getDeadline() {
    return this.deadline;
  }

  public String getLink() {
    return this.link;
  }

  public MessageIcon getMessageIcon() {
    return this.messageIcon;
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

  public LocalDate setDeadline(LocalDate deadline) {
    return this.deadline = deadline;
  }

  public String setLink(String link) {
    return this.link = link;
  }

  public MessageIcon setMessageIcon(MessageIcon messageIcon) {
    return this.messageIcon = messageIcon;
  }

  @Override
  public String toString() {
    return "Message{" + "id=" + this.id + ", content='" + this.content + '\'' + ", createdAt='" + this.createdAt + '\''
        + '}';
  }

}