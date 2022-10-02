package com.example.springboot;

import java.time.LocalDateTime;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "messages")
public class Message {

  private Long id;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private String content;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate deadline;

  private String[] tags;

  private String link;

  private String icon;

  Message() {
  }

  Message(String content, LocalDate deadline, String[] tags, String link, String icon) {
    this.content = content;
    this.deadline = deadline;
    this.tags = tags;
    this.link = link;
    this.icon = icon;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return this.id;
  }

  @Column(name = "content", nullable = false)
  public String getContent() {
    return this.content;
  }

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false, updatable = false)
  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  @Column(name = "tags", nullable = true)
  public String[] getTags() {
    return this.tags;
  }

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @Column(name = "deadline", nullable = true)
  public LocalDate getDeadline() {
    return this.deadline;
  }

  @Column(name = "link", nullable = true)
  public String getLink() {
    return this.link;
  }

  @Column(name = "icon", nullable = true)
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

  public LocalDate setDeadline(LocalDate deadline) {
    return this.deadline = deadline;
  }

  public String setLink(String link) {
    return this.link = link;
  }

  public String setIcon(String icon) {
    return this.icon = icon;
  }

  @Override
  public String toString() {
    return "Message{" + "id=" + this.id + ", content='" + this.content + '\'' + ", createdAt='" + this.createdAt + '\''
        + '}';
  }

}