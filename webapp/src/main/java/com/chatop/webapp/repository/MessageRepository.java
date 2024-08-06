package com.chatop.webapp.repository;

import com.chatop.webapp.model.DBMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<DBMessage, Integer> {
}
