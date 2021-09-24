package com.dbs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.entity.Customer;
import com.dbs.repository.CustomerRepository;
@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public Optional<Customer> getCustomerById(String id) {
		
		return customerRepository.findById(id);
	}
	
}
