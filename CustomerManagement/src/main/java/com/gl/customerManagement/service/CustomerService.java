package com.gl.customerManagement.service;

import java.util.List;

import com.gl.customerManagement.entity.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public Customer findById(int id);

	public void save(Customer theCustomer);

	public void deleteById(int id); 
		
	
}
