package com.chatop.webapp.controller;

import com.chatop.webapp.model.DBRental;
import com.chatop.webapp.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
  private final RentalService rentalService;

  @Autowired
  public RentalController(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  // récupère tous les rentals
  @GetMapping("/all")
  public Iterable<DBRental> getAllRentals() {
    return rentalService.getAllRentals();
  }

  // récupère une location en particulier
  @GetMapping("/detail/{id}")
  public DBRental getRental(@PathVariable("id") final int id) {
    Optional<DBRental> dbRental = rentalService.getRental(id);
    return dbRental.orElse(null);
  }

  @PostMapping("/create")
  public DBRental createRental(@RequestBody final DBRental dbRental) {
    return rentalService.saveRental(dbRental);
  }

  // Update la location en particulier
  @PutMapping("/detail/{id}")
  public DBRental updateRental(@PathVariable("id") final int id, @RequestBody final DBRental dbRental) {
    Optional<DBRental> r = rentalService.getRental(id);
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

      int owner_id = dbRental.getOwner_id();
      if(owner_id != 0) {
        currentRental.setOwner_id(owner_id);
      }

      Date created_date = dbRental.getCreated_date();
      if(created_date != null) {
        currentRental.setCreated_date(created_date);
      }

      Date updated_date = dbRental.getUpdated_date();
      if(updated_date != null) {
        currentRental.setUpdated_date(updated_date);
      }
      rentalService.saveRental(currentRental);
      return currentRental;
    } else {
      return null;
    }
  }

  // Suppression du rental
  @DeleteMapping("/detail/{id}")
  public void deleteRental(@PathVariable("id") final int id) {
    rentalService.deleteRental(id);
  }
}
