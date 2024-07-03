package com.chatop.webapp.controller;

import com.chatop.webapp.model.DBRental;
import com.chatop.webapp.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class RentalController {
  @Autowired
  private RentalService rentalService;

  // récupère tous les rentals
  @GetMapping("/allRentals")
  public Iterable<DBRental> getAllRentals() {
    return rentalService.getAllRentals();
  }

  // récupère une location en particulier
  @GetMapping("/oneRental/{id}")
  public DBRental getRental(@PathVariable("id") final Long id) {
    Optional<DBRental> dbRental = RentalService.getRental(id);
    return dbRental.orElse(null);
  }

  @PostMapping("/createRental")
  public DBRental createRental(@RequestBody final DBRental dbRental) {
    return RentalService.saveRental(dbRental);
  }

  @PostMapping("/oneRental/{id}")
  public DBRental updateRental(@PathVariable("id") final Long id, @RequestBody final DBRental dbRental) {
    Optional<DBRental> r = RentalService.getRental(id);
    if(r.isPresent()) {
      DBRental currentRental = r.get();

      String name = dbRental.getName();
      if(name != null) {
        currentRental.setName(name);
      }

      int surface = dbRental.getSurface();
      if(surface != 0) {
        currentRental.setSurface(surface);
      }

      int price = dbRental.getPrice();
      if(price != 0) {
        currentRental.setPrice(price);
      }

      String picture = dbRental.getPicture();
      if(picture != null) {
        currentRental.setPicture(picture);
      }

      String description = dbRental.getDescription();
      if(description != null) {
        currentRental.setDescription(description);
      }

      int id_owner = dbRental.getId_owner();
      if(id_owner != 0) {
        currentRental.setId_owner(id_owner);
      }

      Date created_date = dbRental.getCreated_date();
      if(created_date != null) {
        currentRental.setCreated_date(created_date);
      }

      Date updated_date = dbRental.getUpdate_date();
      if(updated_date != null) {
        currentRental.setUpdate_date(updated_date);
      }
      RentalService.saveRental(currentRental);
      return currentRental;
    } else {
      return null;
    }
  }

  // Suppression du rental
  @DeleteMapping("/oneRental/{id}")
  public void deleteRental(@PathVariable("id") final Long id) {
    RentalService.deleteRental(id);
  }
}
