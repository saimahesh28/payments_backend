package com.dbs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity

@Data

public class Bank {

	@Id
	String bankBIC;
	
	String bankName;

}
