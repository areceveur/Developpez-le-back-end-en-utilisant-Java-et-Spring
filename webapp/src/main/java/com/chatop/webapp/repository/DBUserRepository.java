package com.chatop.webapp.repository;

import com.chatop.webapp.model.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
  DBUser findByUsername(String username);
}
