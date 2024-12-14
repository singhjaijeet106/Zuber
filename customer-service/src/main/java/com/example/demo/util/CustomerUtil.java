package com.example.demo.util;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.demo.command.CustomerCommand;
import com.example.demo.model.AddressDTO;
import com.example.demo.model.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerUtil {

	
	public boolean validateCustomerCommandRequestObject(CustomerCommand customerCommand) {
		
	}
	
	
	
	
	
	private boolean validatePhoneNumber(long phone) {
		boolean result=true;
		
		return result;
	}
	
	public static List<CustomerDTO> getListOfCustomerDto() {
		List<CustomerDTO> list = new ArrayList<>();
		list.add(getRandomCustomerDTO("adasdf"));
		list.add(getRandomCustomerDTO("wertert"));
		list.add(getRandomCustomerDTO("cbvcbn"));
		list.add(getRandomCustomerDTO("uyiuy"));
		list.add(getRandomCustomerDTO("lkajsdlf"));
		return list;
	}

	public static List<AddressDTO> getListOfAddressByUsername(String username) {
		List<AddressDTO> list = new ArrayList<>();
		list.add(getRanAddressDTO(username));
		list.add(getRanAddressDTO(username));
		list.add(getRanAddressDTO(username));
		list.add(getRanAddressDTO(username));
		return list;
	}

	public static CustomerDTO getRandomCustomerDTO(String username) {
		Random rnd = new Random();
		long id = rnd.nextLong();
		long phone = rnd.nextLong();
		return CustomerDTO.builder().id(id).phone(phone).firstName(getSaltString(10)).lastName(getSaltString(10))
				.email(getSaltString(10)).userName(username).build();
	}

	public static AddressDTO getRanAddressDTO(String username) {
		Random rnd = new Random();
		long id = rnd.nextLong();
		return AddressDTO.builder().id(id).tag(getSaltString(10)).username(username).street(getSaltString(15))
				.city(getSaltString(8)).state(getSaltString(12)).country(getSaltString(11)).pincode(getSaltString(6))
				.build();
	}

	public static String getSaltString(int length) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYabcdefghijklmnopqrstuvwxyz";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < length) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
}
