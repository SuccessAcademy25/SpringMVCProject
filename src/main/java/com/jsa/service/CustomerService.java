package com.jsa.service;

import java.util.List;

import com.jsa.model.Customer;
import com.jsa.model.SearchCriteria;

public interface CustomerService {
	
	public boolean addCustomerDetails(Customer Cust);
	public List<Customer> searchCustomerDetails(SearchCriteria searchCriteria);
	int deleteCustomer(String custId);
	List<Customer> getAllCustomers();
	boolean updateCustomer(Customer customer);
	
	public boolean validateUser(String email, String phone);
}
