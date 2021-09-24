package com.dbs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Message {

	@Id
	String messageCode;
	String messageInstruction;
	
}
