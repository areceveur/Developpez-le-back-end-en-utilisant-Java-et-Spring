package com.chatop.webapp.controller;

import com.chatop.webapp.controller.controllerDTO.TokenResponse;
import com.chatop.webapp.model.DBUser;
import com.chatop.webapp.services.JWTService;
import com.chatop.webapp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final UserService userService;
  private final JWTService jwtService;
  private final JwtDecoder jwtDecoder;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public AuthController(UserService userService, JWTService jwtService, JwtDecoder jwtDecoder, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userService = userService;
    this.jwtService = jwtService;
    this.jwtDecoder = jwtDecoder;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Operation(summary = "Registration of the user")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Registration done"),
    @ApiResponse(responseCode = "400", description = "Problem on the registration")
  })
  @PostMapping("/register")
  public ResponseEntity<TokenResponse> register(@RequestBody DBUser dbUser) {
    dbUser.setPassword(bCryptPasswordEncoder.encode(dbUser.getPassword()));
    dbUser.setCreated_at(LocalDateTime.now());
    DBUser savedUser =  userService.saveUser(dbUser);
    String token = jwtService.generateToken(new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword()));
    return ResponseEntity.ok(new TokenResponse(token));
  }

  @Operation(summary = "Login of the user")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "User logged"),
    @ApiResponse(responseCode = "404", description = "Problem with the login")
  })
  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@RequestBody DBUser login) {
    Optional<DBUser> user = userService.getUserByEmail(login.getEmail());
    if (user.isPresent() && bCryptPasswordEncoder.matches(login.getPassword(), user.get().getPassword())) {
      String token = jwtService.generateToken(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
      return ResponseEntity.ok(new TokenResponse(token));
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @Operation(summary = "Get the user")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Found the user"),
    @ApiResponse(responseCode = "404", description = "User not found")
  })
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
}
