package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.command.CustomerCommand;
import com.example.demo.model.AddressDTO;
import com.example.demo.model.CustomerDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.util.CustomerUtil;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/v1/customer")
public class CustomerController {
	
	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> saveCustomerDto(@RequestBody CustomerCommand customerCommand){
		CustomerDTO customerDTO = customerService.createNewCustomer(customerCommand);
		return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
	}
	
	@GetMapping
	public ResponseEntity<CustomerDTO> getCustomerDtoByUsername(@RequestParam("username") String username){	
		CustomerDTO customerDTO = CustomerUtil.getRandomCustomerDTO(username);
		return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
	}
	
	
	@GetMapping("/list") 
	public ResponseEntity<List<CustomerDTO>> getAllCustomerDto(){	
		List<CustomerDTO> customerDTO = CustomerUtil.getListOfCustomerDto();
		return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
	}
	
	@PutMapping
	public ResponseEntity<CustomerDTO> updateCustomerDtoByUsername(@RequestParam("username") String username, @RequestBody CustomerDTO customerDTO){	
		customerDTO = CustomerUtil.getRandomCustomerDTO(username);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerDTO);
	}
	
	@PostMapping("/address")
	public ResponseEntity<AddressDTO> saveCustomerAddress(@RequestBody AddressDTO addressDTO, @RequestParam("username") String username) {
		addressDTO.setUsername(username);
		return ResponseEntity.status(HttpStatus.CREATED).body(addressDTO);
	}
	
	@GetMapping("/address")
	public ResponseEntity<AddressDTO> getCustomerAddressByUsernameAndAddressTag(@RequestParam("username") String username,@RequestParam("tag") String addressTag) {
		AddressDTO addressDTO = CustomerUtil.getRanAddressDTO(username);
		addressDTO.setTag(addressTag);
		return ResponseEntity.status(HttpStatus.OK).body(addressDTO);
	}
	
	@GetMapping("/address/list")
	public ResponseEntity<List<AddressDTO>> getListOfCustomerAddressByUsername(@RequestParam("username") String username) {
		List<AddressDTO> addressDTO = CustomerUtil.getListOfAddressByUsername(username);
		return ResponseEntity.status(HttpStatus.OK).body(addressDTO);
	}
	
	@PutMapping("/address")
	public ResponseEntity<AddressDTO> updateCustomerAddress(@RequestBody AddressDTO addressDTO, @RequestParam("username") String username) {
		addressDTO.setUsername(username);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(addressDTO);
	}
	
	@DeleteMapping("/address")
	public ResponseEntity<String> removeCustomerAddress(@RequestParam("addresstag") String addressTag, @RequestParam("username") String username) {
		return ResponseEntity.status(HttpStatus.OK).body("Removed successfully");
	}

}
