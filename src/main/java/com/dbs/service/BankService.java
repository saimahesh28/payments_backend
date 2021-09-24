package com.dbs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.entity.Bank;
import com.dbs.repository.BankRepository;

@Service
public class BankService {

	@Autowired
	BankRepository bankRepository;
	
	public Optional<Bank> getBankByBic(String bic) {
		return bankRepository.findById(bic);
	}
}
