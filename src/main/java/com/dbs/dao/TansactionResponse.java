package com.dbs.dao;

import java.util.Date;

import lombok.Data;


@Data
public class TansactionResponse {

	boolean isSuccessful;
	
	String clearBalance;
	String senderAccountNumber;
	String recipientAccountNumber;
	String messageCode;
	Date date;
}
