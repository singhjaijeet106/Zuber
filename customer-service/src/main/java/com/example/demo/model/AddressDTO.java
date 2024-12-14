package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDTO {
	private long id;
	private String tag;
	private String username;
	private String street;
	private String city;
	private String state;
	private String pincode;
	private String country;
}
