package com.chatop.webapp.services;

import com.chatop.webapp.model.DBRental;
import com.chatop.webapp.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalService {
  @Autowired
  private static RentalRepository rentalRepository;

  public Iterable<DBRental> getAllRentals() {
    return rentalRepository.findAll();
  }

  public static Optional<DBRental> getRental(final Long id) {
    return rentalRepository.findById(id);
  }

  public static DBRental saveRental(final DBRental rental) {
    return rentalRepository.save(rental);
  }

  public static void deleteRental(final Long id) {
    rentalRepository.deleteById(id);
  }
}
