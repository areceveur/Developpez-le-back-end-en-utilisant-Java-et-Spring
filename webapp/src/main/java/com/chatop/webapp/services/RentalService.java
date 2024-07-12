package com.chatop.webapp.services;

import com.chatop.webapp.model.DBRental;
import com.chatop.webapp.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  public Iterable<DBRental> getAllRentals() {
    return rentalRepository.findAll();
  }

  public DBRental getRental(int id) {
    return rentalRepository.findById(id).orElse(null);
  }

  public DBRental saveRental(DBRental rental) {
    return rentalRepository.save(rental);
  }

  public void deleteRental(int id) {
    rentalRepository.deleteById(id);
  }

  public DBRental updateRental(DBRental rental) {
    return rentalRepository.save(rental);
  }

  public String savePicture(MultipartFile picture) throws IOException {
    String directory = "/Users/anne-elodiereceveur/Pictures";
    Files.createDirectories(Paths.get(directory));

    String uniqueFileName = System.currentTimeMillis() + "_" + picture.getOriginalFilename();
    String picturePath = Paths.get(directory, uniqueFileName).toString();

    File file = new File(picturePath);
    picture.transferTo(file);

    return picturePath;
  }
}
