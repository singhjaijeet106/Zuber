package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	private long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private long phone;
	private String countryCode;
}
