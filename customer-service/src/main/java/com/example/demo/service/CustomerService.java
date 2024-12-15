package com.example.demo.service;

import com.example.demo.command.CustomerCommand;
import com.example.demo.model.CustomerDTO;

public interface CustomerService {
	public CustomerDTO createNewCustomer(CustomerCommand customerCommand);
}
