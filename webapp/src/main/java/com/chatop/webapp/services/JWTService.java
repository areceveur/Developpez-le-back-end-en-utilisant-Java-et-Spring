package com.chatop.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JWTService {
  private final JwtEncoder jwtEncoder;
  private final JwtDecoder jwtDecoder;

  private final String jwtKey = "q2RtgQCxZ4E7Iz++7/eYm3ew5nyAHFPD72jekQXKuqS0Lj+zoHMdcdhwwlxn2BHJ";

  @Autowired
  public JWTService(JwtEncoder jwtEncoder, @Qualifier("jwtDecoder") JwtDecoder jwtDecoder) {
    this.jwtEncoder = jwtEncoder;
    this.jwtDecoder = jwtDecoder;
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

  public String extractUsername(String token) {
    Jwt decodedJwt = jwtDecoder.decode(token);
    return decodedJwt.getSubject();
  }
}
