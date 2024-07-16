package com.chatop.webapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "MESSAGES")
public class DBMessage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int rental_id;
  private int user_id;
  private String message;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
}
