package com.chatop.webapp.repository;

import com.chatop.webapp.model.DBUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<DBUser, Integer> {
  Optional<DBUser> findByEmail(String email);
}
