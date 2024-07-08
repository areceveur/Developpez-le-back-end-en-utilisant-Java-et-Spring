package com.chatop.webapp.controller;

import com.chatop.webapp.controller.controllerDTO.TokenResponse;
import com.chatop.webapp.model.DBUser;
import com.chatop.webapp.services.JWTService;
import com.chatop.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final UserService userService;
  private final JWTService jwtService;
  private final JwtDecoder jwtDecoder;

  @Autowired
  public AuthController(UserService userService, JWTService jwtService, JwtDecoder jwtDecoder) {
    this.userService = userService;
    this.jwtService = jwtService;
    this.jwtDecoder = jwtDecoder;
  }

  @PostMapping("/register")
  public ResponseEntity<TokenResponse> register(@RequestBody DBUser dbUser) {
    DBUser savedUser =  userService.saveUser(dbUser);
    System.out.println("Received user data: " + dbUser.getEmail() + ", " + dbUser.getUsername() + ", " + dbUser.getPassword());
    String token = jwtService.generateToken(new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword()));
    return ResponseEntity.ok(new TokenResponse(token));
  }

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@RequestBody DBUser login) {
    Optional<DBUser> user = userService.getUserByEmail(login.getEmail());
    if (user.isPresent() && user.get().getPassword().equals(login.getPassword())) {
      String token = jwtService.generateToken(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
      return ResponseEntity.ok(new TokenResponse(token));
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @GetMapping("/me")
  public ResponseEntity<DBUser> me(@RequestHeader("Authorization") String authorizationHeader) {
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring(7);
      Jwt jwt = jwtDecoder.decode(token);
      String username = jwt.getSubject();
      Optional<DBUser> user = userService.getUserByEmail(username);
      return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @PutMapping("/me/{id}")
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

  @DeleteMapping("/me/{id}")
  public void deleteUser(@PathVariable("id") int id) {
    userService.deleteUser(id);
  }
}
