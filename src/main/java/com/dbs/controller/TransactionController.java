package com.dbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.dao.TransactionRequest;
import com.dbs.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	@PostMapping("sendmoney")
	ResponseEntity<Object> processTransaction(@RequestBody TransactionRequest transactionRequest){
		return transactionService.sendMoney(transactionRequest);
	}
	

}
