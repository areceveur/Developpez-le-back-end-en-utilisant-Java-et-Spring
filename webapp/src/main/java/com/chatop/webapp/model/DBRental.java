package com.chatop.webapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "RENTALS")
public class DBRental {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private int surface;
  private int price;
  private String picture;
  private String description;
  private int owner_id;
  private Date created_date;
  private Date updated_date;
}
