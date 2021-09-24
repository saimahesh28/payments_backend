package com.dbs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    Integer	transactionId;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	Customer sender;
	
	
	
	@ManyToOne
	@JoinColumn(name="message_code")
	Message messageCode;
	
	String recipientName;
	String recipientAccountNumber;
    	@ManyToOne
	@JoinColumn(name="bank_bic")
	Bank recipientBankBic;
	
	String transferType;
	Double amount;
	@Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="CREATED_TIME")
    private Date date;
	Boolean isSuccess;
}
