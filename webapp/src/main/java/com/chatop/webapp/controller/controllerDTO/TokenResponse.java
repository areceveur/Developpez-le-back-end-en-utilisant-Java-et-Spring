package com.chatop.webapp.controller.controllerDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenResponse {
  private String token;

  public TokenResponse(String token) {
    this.token = token;
  }

}
