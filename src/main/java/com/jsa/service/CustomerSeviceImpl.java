package com.jsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsa.dao.CustomerDAOIMPL;
import com.jsa.model.Customer;


@Service
public class CustomerSeviceImpl implements CustomerService{
	
	@Autowired
	CustomerDAOIMPL  Customerdao;

	@Override
	public boolean addCustomerDetails(Customer Cust) {
		System.err.println( "************CustomerSeviceImpl-addCustomerDetails()****************");
		return Customerdao.addCustomerDetails(Cust);
	}

}
