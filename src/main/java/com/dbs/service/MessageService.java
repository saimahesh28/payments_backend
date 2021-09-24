package com.dbs.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.dbs.entity.Message;
import com.dbs.repository.MessageRepository;

public class MessageService {

	@Autowired
	MessageRepository messageRepository;

	public Optional<Message> getMessageById(String id) {
		
		return messageRepository.findById(id);
	}
	
	public List<Message> getAllMessages()
	{
		return messageRepository.findAll();
	}
}
