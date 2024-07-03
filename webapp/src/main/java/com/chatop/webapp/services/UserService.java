package com.chatop.webapp.services;

import com.chatop.webapp.model.DBUser;
import com.chatop.webapp.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public DBUser saveUser(DBUser dbUser) {
    return userRepository.save(dbUser);
  }

  public Optional<DBUser> getUser(final int id) {
    return userRepository.findById(id);
  }

  public void deleteUser(final int id) {
    userRepository.deleteById(id);
  }
}
