package com.example.demo.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.command.CustomerCommand;
import com.example.demo.model.AddressDTO;
import com.example.demo.model.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerUtil {

	private static final Logger log = LoggerFactory.getLogger(CustomerUtil.class);
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zAA-Z]{2,7}$";
	private final Random random = new Random();
	
	
	public List<String> validateCustomerCommandRequestObject(CustomerCommand customerCommand) {
		List<String> errorList = new ArrayList<>();

		String error = validateName(customerCommand.getFirstName());
		if (error != null) {
			errorList.add(error.replace("{}", "First Name"));
		}
		error = validateName(customerCommand.getLastName());
		if (error != null) {
			errorList.add(error.replace("{}", "Last Name"));
		}
		error = validateName(customerCommand.getUserName());
		if (error != null) {
			errorList.add(error.replace("{}", "Username"));
		}
		error = validatePhoneNumber(customerCommand.getPhone());
		if (error != null) {
			errorList.add(error.replace("{}", "Phone"));
		}
		error = validateEmailAddress(customerCommand.getEmail());
		if (error != null) {
			errorList.add(error.replace("{}", "Eamil"));
		}
		return errorList;
	}

	public CustomerDTO convertCustomerCommandtoCustomerDto(CustomerCommand command) {
		return CustomerDTO.builder()
				.id(random.nextLong(946831266482331l))
				.firstName(command.getFirstName())
				.lastName(command.getLastName())
				.email(command.getEmail())
				.userName(command.getUserName())
				.phone(command.getPhone()).build();
	}

	private String validateName(String name) {
		String result = null;
		if (name == null) {
			result = "{} field cann't be empty";
		} else {
			if (name.trim().length() < 3) {
				result = "{} field should be greater than 3 character";
			}
		}
		return result;
	}

	private String validatePhoneNumber(long phone) {
		String result = null;
		try {
			String phoneStr = String.valueOf(phone);
			if (phoneStr.length() != 10) {
				result = "{} should be of 10 digit";
			}
		} catch (Exception e) {
			result = "Error while validating phone number " + phone;
			log.error("Error while validating phone number {} :  {}", phone, e.getMessage());
		}
		return result;
	}

	private String validateEmailAddress(String email) {
		String result = null;
		if(email==null) {
			result = "{} field cann't be empty";
		}else {
			Pattern pattern = Pattern.compile(EMAIL_REGEX);
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches()) {
				result = "Invalid email address";
			}
		}
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
