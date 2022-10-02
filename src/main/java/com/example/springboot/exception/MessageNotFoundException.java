package com.example.springboot.exception;

public class MessageNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

    public MessageNotFoundException(Long id) {
      super("Could not find message " + id);
    }
  }