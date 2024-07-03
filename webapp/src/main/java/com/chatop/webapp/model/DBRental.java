package com.chatop.webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class DBRental {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private int surface;
  private int price;
  private String picture;
  private String description;
  private int id_owner;
  private Date created_date;
  private Date update_date;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSurface() {
    return surface;
  }

  public void setSurface(int surface) {
    this.surface = surface;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId_owner() {
    return id_owner;
  }

  public void setId_owner(int id_owner) {
    this.id_owner = id_owner;
  }

  public Date getCreated_date() {
    return created_date;
  }

  public void setCreated_date(Date created_date) {
    this.created_date = created_date;
  }

  public Date getUpdate_date() {
    return update_date;
  }

  public void setUpdate_date(Date update_date) {
    this.update_date = update_date;
  }
}
