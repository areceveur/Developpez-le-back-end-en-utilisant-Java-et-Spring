package com.chatop.webapp.services;

import com.chatop.webapp.model.DBRental;
import com.chatop.webapp.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
  @Autowired
  private RentalRepository rentalRepository;

  public Iterable<DBRental> getAllRentals() {
    return rentalRepository.findAll();
  }

  public Optional<DBRental> getRental(int id) {
    return rentalRepository.findById(id);
  }

  public DBRental saveRental(DBRental rental) {
    return rentalRepository.save(rental);
  }

  public void deleteRental(int id) {
    rentalRepository.deleteById(id);
  }
}
