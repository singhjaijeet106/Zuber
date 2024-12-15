package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.command.CustomerCommand;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.CustomerDTO;
import com.example.demo.util.CustomerUtil;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerUtil customerUtil;
	
	@Autowired
	CustomerServiceImpl(CustomerUtil customerUtil){
		this.customerUtil = customerUtil;
	}
	
	
	@Override
	public CustomerDTO createNewCustomer(CustomerCommand customerCommand){
		CustomerDTO customerDTO = null;
		List<String> errorList = customerUtil.validateCustomerCommandRequestObject(customerCommand);
		if(errorList==null || errorList.isEmpty()) {
			customerDTO = customerUtil.convertCustomerCommandtoCustomerDto(customerCommand);
		}else {
			throw new ValidationException("Error while validating customer request", errorList);
		}
		return customerDTO;
	}
	
	
	@Override
	public CustomerDTO getCustomerByUsername(String username) {
		CustomerDTO customerDTO = CustomerUtil.getRandomCustomerDTO(username);
		return customerDTO;
	}

}
