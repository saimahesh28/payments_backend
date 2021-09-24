package com.dbs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.entity.Customer;
import com.dbs.entity.Message;
import com.dbs.service.CustomerService;
import com.dbs.service.MessageService;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;
	@GetMapping("messagecodes")
	public ResponseEntity<Object> getMessageCodes() {
		List<Message> m=messageService.getAllMessages();
		
		return ResponseEntity.status(HttpStatus.OK).body(m);
		
	}
}
