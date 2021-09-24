package com.dbs.dao;

import com.dbs.entity.Message;

import lombok.Data;

@Data
public class TransactionRequest {

    String senderName;
	String senderAccountNumber;
	String recipientBic;
	String recipientName;
	String recipientAccountNumber;
	Message message;
	String transferType;
	double amount;
	
}
