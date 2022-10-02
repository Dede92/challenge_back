package com.example.springboot;

class MessageNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

    MessageNotFoundException(Long id) {
      super("Could not find message " + id);
    }
  }