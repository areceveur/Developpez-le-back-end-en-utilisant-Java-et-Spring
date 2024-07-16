package com.chatop.webapp.controller;

import com.chatop.webapp.model.DBMessage;
import com.chatop.webapp.model.DBRental;
import com.chatop.webapp.model.DBUser;
import com.chatop.webapp.services.MessageService;
import com.nimbusds.oauth2.sdk.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/messages")
public class MessageController {

  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @Operation(summary = "Receive a message")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Message received"),
    @ApiResponse(responseCode = "400", description = "Message not received")
  })
  @PostMapping()
  public ResponseEntity<DBMessage> receiveMessage(@RequestBody Message dbMessage) {
    DBMessage message = new DBMessage();
    DBMessage savedMessage = messageService.saveMessage(message);
    return ResponseEntity.ok(savedMessage);
  }
}
