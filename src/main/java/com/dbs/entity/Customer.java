package com.dbs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	String accountNumber;

	
	String customerName;
	
	double clearBalance;
	
	Boolean overDraft;
	
	
	
	
	
	
}
