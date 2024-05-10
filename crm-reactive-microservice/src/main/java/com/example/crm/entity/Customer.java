package com.example.crm.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "customers")
@Data
public class Customer {
	@Id
	private String identity;
	private FullName fullName;
	private List<Phone> phones;
	private List<Address> addresses;

}
