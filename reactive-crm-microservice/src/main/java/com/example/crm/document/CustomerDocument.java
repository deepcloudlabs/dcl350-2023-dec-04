package com.example.crm.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class CustomerDocument {
	@Id
	private String identity; 
	private String fullname; 
	@Indexed(unique=true)
	private String email; 
	private List<Phone> phones; 
	private List<Address> addresses; 
}
