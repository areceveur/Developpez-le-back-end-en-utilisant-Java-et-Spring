package com.chatop.webapp.services;

import com.chatop.webapp.model.DBMessage;
import com.chatop.webapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {
  @Autowired
  private MessageRepository messageRepository;

  public DBMessage saveMessage(DBMessage dbMessage) {
    return messageRepository.save(dbMessage);
  }

  public Optional<DBMessage> getUser(final int id) {
    return messageRepository.findById(id);
  }

}
