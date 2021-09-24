package com.dbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.service.BankService;

@RestController
public class BankController {
	
	@Autowired
	BankService bankService;

	@GetMapping("/bank/{bic}")
		public ResponseEntity<Object> getBankByBic(@PathVariable String bic)
		{
			
			return ResponseEntity.status(HttpStatus.OK).body(bankService.getBankByBic(bic));
		}
	
}
