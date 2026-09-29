package com.jsa.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jsa.model.Customer;
import com.jsa.model.SearchCriteria;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Repository
public class CustomerDAOIMPL implements CustomerDao {

	private final InternalResourceViewResolver viewResolver;

	@Autowired
	JdbcTemplate jdbcTemp;

	String INSERT_SQL = "INSERT INTO customer  VALUES (?,?, ?, ?, ?, ?, ?, ?,?);";

	CustomerDAOIMPL(InternalResourceViewResolver viewResolver) {
		this.viewResolver = viewResolver;
	}

	@Override
	public boolean addCustomerDetails(Customer cust) {
		System.err.println("************CustomerDAOIMPL-addCustomerDetails()****************");
		jdbcTemp.update(INSERT_SQL, cust.getCustId(), cust.getCustName(), cust.getCustEmail(), cust.getPhone(),
				cust.getCustCity(), cust.getCustsalary(), cust.getDate(), cust.getGender(), LocalDate.now());
		System.out.println("insert is done");
		return true;
	}

	@Override
	public List<Customer> searchCustomerDetails(SearchCriteria searchCriteria) {
		System.out.println("--------- Customer DAO Search ------------");
		//StringBuilder sql = new StringBuilder("SELECT cust_id, cust_name, cust_email, phone, "
			//	+ "cust_city, cust_salary, dob, gender, records_create_date " + "FROM customer WHERE 1=1");
		
		StringBuilder sql = new StringBuilder(" SELECT * FROM customer WHERE 1=1 ");
		List<Object> params = new ArrayList<>();
		// Search by ID
		if (searchCriteria.getId() > 0) {
			sql.append(" AND cust_id = ?");
			params.add(searchCriteria.getId());
		}
		// Search by Email
		if (searchCriteria.getEmail() != null && !searchCriteria.getEmail().trim().isEmpty()) {
			sql.append(" AND cust_email = ?");
			params.add(searchCriteria.getEmail().trim());
		}
		// Search by Phone
		if (searchCriteria.getPhone() != null && !searchCriteria.getPhone().trim().isEmpty()) {
			sql.append(" AND phone = ?");
			params.add(searchCriteria.getPhone().trim());
		}
		System.out.println("SQL >>> " + sql);
		System.out.println("PARAMS >>> " + params);
		return jdbcTemp.query(sql.toString(), new CustomerRowMapper(), params.toArray());
	}
	
	@Override
    public int deleteCustomer(String custId) {
        String sql = "DELETE FROM customer WHERE cust_id = ?";
        return jdbcTemp.update(sql, custId);
    }
	
	@Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        return jdbcTemp.query(sql, new CustomerRowMapper());
    }
	
	@Override
	public int updateCustomer(Customer customer) {
	    String sql = "UPDATE customer SET cust_name = ?, cust_email = ?, phone = ?, " +
	                 "cust_city = ?, cust_salary = ?, gender = ?, dob = ? WHERE cust_id = ?";
	    return jdbcTemp.update(
	        sql,
	        customer.getCustName(),
	        customer.getCustEmail(),
	        customer.getPhone(),
	        customer.getCustCity(),
	        customer.getCustsalary(),
	        customer.getGender(),
	        customer.getDate(),
	        customer.getCustId()
	    );
	}
	
	
	@Override
    public boolean validateUser(String email, String phone) {
		System.out.println("*************validateUser*************"+email+" >>>>>>"+phone);
        String sql = "SELECT COUNT(*) FROM customer WHERE cust_email = ? AND phone = ?";
        
        try {
            Integer count = jdbcTemp.queryForObject(sql, Integer.class, email, phone);
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
	

