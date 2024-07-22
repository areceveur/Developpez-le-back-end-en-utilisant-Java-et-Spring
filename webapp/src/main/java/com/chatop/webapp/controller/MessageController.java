package com.chatop.webapp.controller;

import com.chatop.webapp.model.DBMessage;
import com.chatop.webapp.services.MessageService;
import com.chatop.webapp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/messages")
public class MessageController {

  private final MessageService messageService;
  private final UserService userService;

  @Autowired
  public MessageController(MessageService messageService, UserService userService) {
    this.messageService = messageService;
    this.userService = userService;
  }

  @Operation(summary = "Receive a message")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Message received")
  })
  @PostMapping()
  public ResponseEntity<DBMessage> receiveMessage(@RequestBody DBMessage dbMessage) {

    String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    int userId = userService.findUserIdByEmail(currentUserEmail);

    dbMessage.setUser_id(userId);
    dbMessage.setRental_id(dbMessage.getRental_id());
    dbMessage.setCreated_at(LocalDateTime.now());
    dbMessage.setUpdated_at(LocalDateTime.now());

    DBMessage savedMessage = messageService.saveMessage(dbMessage);
    return ResponseEntity.ok(savedMessage);
  }
}
