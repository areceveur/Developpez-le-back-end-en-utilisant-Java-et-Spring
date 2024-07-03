package com.chatop.webapp.controller;

import com.chatop.webapp.model.DBUser;
import com.chatop.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

  @Autowired
  private UserService userService;

  // Enregistrement d'un premier utilisateur
  @PostMapping("/register")
  public DBUser register(@RequestBody DBUser dbUser) {
    return userService.saveUser(dbUser);
  }

  // Login de l'utilisateur
  @GetMapping("/login")
  public DBUser login(@RequestBody DBUser login) {
    return login;
  }

  @PutMapping("/user/{id}")
  public DBUser updateUser(@PathVariable("id") final int id, @RequestBody DBUser dbUser) {
    Optional<DBUser> u = userService.getUser(id);
    if (u.isPresent()) {
      DBUser currentUser = u.get();

      String email = dbUser.getEmail();
      if(email != null) {
        currentUser.setEmail(email);
      }

      String username = dbUser.getUsername();
      if(username != null) {
        currentUser.setUsername(username);
      }

      String password = dbUser.getPassword();
      if(password != null) {
        currentUser.setPassword(password);
      }

      Date created_at = dbUser.getCreated_at();
      if(created_at != null) {
        currentUser.setCreated_at(created_at);
      }

      Date updated_at = dbUser.getUpdated_at();
      if(updated_at != null) {
        currentUser.setUpdated_at(updated_at);
      }

      String role = dbUser.getRole();
      if(role != null) {
        currentUser.setRole(role);
      }
      userService.saveUser(currentUser);
      return currentUser;
    } else {
      return null;
    }
  }

  @DeleteMapping("/user/{id}")
  public void deleteUser(@PathVariable("id") int id) {
    userService.deleteUser(id);
  }
}
