package com.chatop.webapp.repository;

import com.chatop.webapp.model.DBRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<DBRental, Integer> {

}
