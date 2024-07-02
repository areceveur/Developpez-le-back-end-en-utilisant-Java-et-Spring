package com.chatop.webapp.controller;

import com.chatop.webapp.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping
public class LoginController {
  private final JWTService jwtService;

  @Autowired
  public LoginController(JWTService jwtService) {
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  public String getToken(Authentication authentication) {
    return jwtService.generateToken(authentication);
  }
}
