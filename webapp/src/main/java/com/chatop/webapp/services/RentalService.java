package com.chatop.webapp.services;

import com.chatop.webapp.model.DBRental;
import com.chatop.webapp.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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

  public DBRental updateRental(DBRental rental) {
    return rentalRepository.save(rental);
  }

  public String savePicture(MultipartFile picture) throws IOException {
    String directory = "/Users/anne-elodiereceveur/Documents/formation_openclassrooms/projet3/Developpez-le-back-end-en-utilisant-Java-et-Spring/src/assets/images";
    Path directoryPath = Paths.get(directory);

    String uniqueFileName = System.currentTimeMillis() + "_" + picture.getOriginalFilename();
    Path picturePath = directoryPath.resolve(uniqueFileName);

    picture.transferTo(picturePath.toFile());

    return  uniqueFileName;
  }
}
