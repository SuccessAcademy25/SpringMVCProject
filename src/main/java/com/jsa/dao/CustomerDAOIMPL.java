package com.jsa.dao;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jsa.model.Customer;

@Repository
public class CustomerDAOIMPL implements CustomerDao{
	
	
	@Autowired
	JdbcTemplate  jdbcTemp;
	
	String INSERT_SQL = "INSERT INTO customer  VALUES (?,?, ?, ?, ?, ?, ?, ?,?);";

	@Override
	public boolean addCustomerDetails(Customer cust) {
		System.err.println( "************CustomerDAOIMPL-addCustomerDetails()****************");
			jdbcTemp.update(INSERT_SQL, cust.getCustId(), cust.getCustName(), cust.getCustEmail(), cust.getPhone(), cust.getCustCity(),cust.getCustsalary(),cust.getDate(),cust.getGender(),LocalDate.now());
			System.out.println("insert is done");
			return true;
	}

}
