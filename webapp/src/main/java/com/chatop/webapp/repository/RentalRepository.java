package com.chatop.webapp.repository;

import com.chatop.webapp.model.DBRental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<DBRental, Integer> {

}
