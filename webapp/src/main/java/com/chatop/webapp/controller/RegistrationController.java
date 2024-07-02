package com.chatop.webapp.controller;

import com.chatop.webapp.model.DBUser;
import com.chatop.webapp.repository.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RegistrationController {
  @Autowired
  private DBUserRepository dbUserRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public String registerUser(@RequestBody DBUser dbUser) {
    dbUser.setUsername(dbUser.getUsername());
    dbUser.setPassword(passwordEncoder.encode(dbUser.getPassword()));
    dbUser.setRole("USER");
    dbUserRepository.save(dbUser);
    return "User registered successfully";
  }
}
