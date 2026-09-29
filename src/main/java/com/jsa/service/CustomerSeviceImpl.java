package com.jsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsa.dao.CustomerDAOIMPL;
import com.jsa.model.Customer;
import com.jsa.model.SearchCriteria;

@Service
public class CustomerSeviceImpl implements CustomerService {

	@Autowired
	CustomerDAOIMPL Customerdao;

	@Override
	public boolean addCustomerDetails(Customer Cust) {
		System.out.println("************CustomerSeviceImpl-addCustomerDetails()****************");
		//data transformartion 
		return Customerdao.addCustomerDetails(Cust);
	}

	@Override
	public List<Customer> searchCustomerDetails(SearchCriteria searchCriteria) {
		System.out.println("--------- Customer Service Search ------------");
		System.out.println("Search Criteria: " + searchCriteria);
		List<Customer> custList = Customerdao.searchCustomerDetails(searchCriteria);
		System.out.println("Customer List Size: " + custList.size());
		return custList;
	}
	
	@Override
    @Transactional
    public int deleteCustomer(String custId) {
        return Customerdao.deleteCustomer(custId);
    }
	
	@Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return Customerdao.getAllCustomers();
    }
	
	@Override
	@Transactional
	public boolean updateCustomer(Customer customer) {
	    return Customerdao.updateCustomer(customer) > 0;
	}
	
	@Override
    public boolean validateUser(String email, String phone) {
        // Sanity check for empty inputs
        if (email == null || phone == null || email.trim().isEmpty() || phone.trim().isEmpty()) {
            return false;
        }
        
        // Delegate to DAO
        return Customerdao.validateUser(email.trim(), phone.trim());
    }

}
