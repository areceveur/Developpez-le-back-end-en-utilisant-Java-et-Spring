package com.chatop.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JWTService {
  private final JwtEncoder jwtEncoder;

  @Autowired
  public JWTService(JwtEncoder jwtEncoder, @Qualifier("jwtDecoder") JwtDecoder jwtDecoder) {
    this.jwtEncoder = jwtEncoder;
  }

  public String generateToken(Authentication authentication) {
    Instant now = Instant.now();
    String email = authentication.getName();

    JwtClaimsSet claims = JwtClaimsSet.builder()
      .issuer("self")
      .issuedAt(now)
      .expiresAt(now.plus(1, ChronoUnit.DAYS))
      .subject(email)
      .build();
    JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
    return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
  }
}
