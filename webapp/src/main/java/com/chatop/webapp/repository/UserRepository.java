package com.chatop.webapp.repository;

import com.chatop.webapp.model.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DBUser, Integer> {
  Optional<DBUser> findByEmail(String email);
}
