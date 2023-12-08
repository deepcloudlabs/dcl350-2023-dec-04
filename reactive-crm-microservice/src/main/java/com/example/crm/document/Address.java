package com.example.crm.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	private AddressType type;
	private String line;
	private String city;
	private String country;
	private String zipCode;
}
