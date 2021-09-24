package com.dbs.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.entity.Customer;
import com.dbs.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@GetMapping("customer/{acc}")
	public ResponseEntity<Object> getCustomerDetails(@PathVariable("acc") String id ) {
		Optional<Customer> c=customerService.getCustomerById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(c);
		
	}
}
