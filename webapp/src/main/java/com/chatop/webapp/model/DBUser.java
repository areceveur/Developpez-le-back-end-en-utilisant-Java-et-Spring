package com.chatop.webapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "USERS")
public class DBUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String email;
  private String username;
  private String password;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
  private String role;

}


