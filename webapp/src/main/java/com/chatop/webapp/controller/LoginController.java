package com.chatop.webapp.controller;

import com.chatop.webapp.services.JWTService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
public class LoginController {
  private JWTService jwtService;

  public LoginController(JWTService jwtService) {
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  public String getToken(Authentication authentication) {
    String token = jwtService.generateToken(authentication);
    return token;
  }
}
