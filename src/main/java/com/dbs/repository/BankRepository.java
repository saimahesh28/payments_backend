package com.dbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, String> {

}
