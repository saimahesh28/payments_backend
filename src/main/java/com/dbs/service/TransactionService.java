package com.dbs.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.dbs.dao.ErrorMessage;
import com.dbs.dao.TansactionResponse;
import com.dbs.dao.TransactionRequest;
import com.dbs.entity.Customer;
import com.dbs.entity.Transaction;
import com.dbs.repository.CustomerRepository;
import com.dbs.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	CustomerRepository customerRepository;
	
	
	List<String> perm=Arrays.asList();
	public void generatePermutations(int n,String[] names, String word) {
	    int len=names.length;
		for (int i = 0; i < len; i++) {
	        String newWord = word + names[i];
	        if (n >= 0) {
	            generatePermutations(n- 1, names, newWord);
	       } else {
	            perm.add(newWord);
	        }
	    }
	}
	
	public ResponseEntity<Object> sendMoney(TransactionRequest transactionRequest)
	{
		Customer sender=customerRepository.findById(transactionRequest.getSenderAccountNumber()).get();
		
		
		
		if(transactionRequest.getAmount()<0) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorMessage("amount cant be negative"));
			
		}
		if(transactionRequest.getRecipientBic().isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorMessage("Bic cant be empty"));
		}
		if(sender.getCustomerName().toUpperCase().contains("HDFC BANK")&&transactionRequest.getTransferType().equals("BB")) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorMessage("sender should be bank"));
			
		}
		
		if(transactionRequest.getTransferType().equals("BB")){
			
			if(!transactionRequest.getRecipientName().toUpperCase().contains("HDFC BANK")||!transactionRequest.getSenderName().toUpperCase().contains("HDFC BANK"))
			{		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorMessage("recipient and sender should be bank"));
		}
		
		}
		else
		{
//			//check if recipient name is present in sdn list
			String s=new String("");
			File f;
			try {
				f = ResourceUtils.getFile("classpath:sdnlist.txt");
			
			FileReader fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			String[] names=transactionRequest.getRecipientName().split(" ");
			generatePermutations(names.length,names,s);
			String pattern="("+String.join("|", names)+")";
			Pattern p=Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
			while((s=br.readLine())!=null) {
				Matcher match=p.matcher(s);
				if(match.find()) {
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorMessage("name present in sdn list"));
		}
			}
			}
			 catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
			
		double total=1.25*(transactionRequest.getAmount());
		if(total>customerRepository.findById(transactionRequest.getSenderAccountNumber()).get().getClearBalance())
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("amount cant be more than balance");
		}
		
		sender.setClearBalance(sender.getClearBalance()-total);
		customerRepository.save(sender);
			Transaction transaction=new Transaction();
			transaction.setAmount(total);
			transaction.setDate(new Date());
			transaction.setRecipientName(transactionRequest.getRecipientName());

			transaction.setRecipientAccountNumber(transactionRequest.getRecipientAccountNumber());
			transaction.setSender(sender);
			transaction.setMessageCode(transactionRequest.getMessage());
			transaction.setIsSuccess(true);
	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("transaction successful");
	}
	
	
	}

