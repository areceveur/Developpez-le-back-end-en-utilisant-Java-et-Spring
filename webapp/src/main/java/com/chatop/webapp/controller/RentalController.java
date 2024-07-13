package com.chatop.webapp.controller;

import com.chatop.webapp.model.DBRental;
import com.chatop.webapp.services.RentalService;
import com.chatop.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/rentals")
public class RentalController {

  private final RentalService rentalService;
  private final UserService userService;

  @Autowired
  public RentalController(RentalService rentalService, UserService userService) {
    this.rentalService = rentalService;
    this.userService = userService;
  }

  // récupère tous les rentals
  @GetMapping
  public ResponseEntity<Iterable<DBRental>> getAllRentals() {
    Iterable<DBRental> rentals = rentalService.getAllRentals();
    return ResponseEntity.ok(rentals);
  }

  // récupère une location en particulier
  @GetMapping("/detail/{id}")
  public DBRental getRental(@PathVariable("id") final int id) {
    return rentalService.getRental(id);
  }

  @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DBRental> createRental(
    @RequestParam("name") String name,
    @RequestParam("surface") int surface,
    @RequestParam("price") double price,
    @RequestParam("description") String description,
    @RequestParam("picture") MultipartFile picture) throws IOException {

    String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    int ownerId = userService.findUserIdByEmail(currentUserEmail);


    DBRental rental = new DBRental();
    rental.setName(name);
    rental.setSurface(surface);
    rental.setPrice(price);
    rental.setDescription(description);
    rental.setOwner_id(ownerId);

    if(!picture.isEmpty()) {
      String picturePath = rentalService.savePicture(picture);
      rental.setPicture(picturePath);
    }

    DBRental createdRental = rentalService.saveRental(rental);
    return ResponseEntity.ok(createdRental);
  }

  // Update la location en particulier
  @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DBRental> updateRental(
    @PathVariable("id") final int id,
    @RequestParam("name") String name,
    @RequestParam("surface") int surface,
    @RequestParam("price") double price,
    @RequestParam("description") String description,
    @RequestParam(value = "picture", required = false) MultipartFile picture) throws IOException {

    DBRental rental = rentalService.getRental(id);
    rental.setName(name);
    rental.setSurface(surface);
    rental.setPrice(price);
    rental.setDescription(description);

    if (picture != null && !picture.isEmpty()) {
      String picturePath = rentalService.savePicture(picture);
      rental.setPicture(picturePath);
    }

    DBRental updatedRental = rentalService.updateRental(rental);
    return ResponseEntity.ok(updatedRental);
  }

  // Suppression du rental
  @DeleteMapping("/detail/{id}")
  public void deleteRental(@PathVariable("id") final int id) {
    rentalService.deleteRental(id);
  }
}
