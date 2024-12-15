package com.example.demo.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerCommand {
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private long phone;
	private String countryCode;	
	
}