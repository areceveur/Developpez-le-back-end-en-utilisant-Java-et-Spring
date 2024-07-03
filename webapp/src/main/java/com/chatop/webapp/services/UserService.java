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
  private static UserRepository userRepository;

  public static DBUser saveUser(DBUser register) {
    return userRepository.save(register);
  }

  public static Optional<DBUser> getUser(final Long id) {
    return userRepository.findById(id);
  }

  public static void deleteUser(final Long id) {
    userRepository.deleteById(id);
  }
}
