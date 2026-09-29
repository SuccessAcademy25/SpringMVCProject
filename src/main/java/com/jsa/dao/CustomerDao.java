package com.jsa.dao;

import java.util.List;

import com.jsa.model.Customer;
import com.jsa.model.SearchCriteria;

public interface CustomerDao {
	public boolean addCustomerDetails(Customer Cust);
	public List<Customer> searchCustomerDetails( SearchCriteria searchCriteria);
	int deleteCustomer(String custId);
	List<Customer> getAllCustomers();
	int updateCustomer(Customer customer);
	public boolean validateUser(String email, String phone);
}
