package com.jsa.model;

import java.time.LocalDate;

public class Customer {

	private int custId = 0;
	private String custName;
	private String custEmail;
	private String phone;
	private String custCity;
	private String custsalary;
	private String date;
	private String gender;
	private LocalDate recordscreateDate;

	public Customer() {
		super();

	}
	
	public Customer(int custId, String custName, String custEmail, String phone, String custCity, String custsalary,
			String date, String gender, LocalDate recordscreateDate) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custEmail = custEmail;
		this.phone = phone;
		this.custCity = custCity;
		this.custsalary = custsalary;
		this.date = date;
		this.gender = gender;
		this.recordscreateDate = recordscreateDate;
	}




	public LocalDate getRecordscreateDate() {
		return recordscreateDate;
	}

	public void setRecordscreateDate(LocalDate recordscreateDate) {
		this.recordscreateDate = recordscreateDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustCity() {
		return custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	public String getCustsalary() {
		return custsalary;
	}

	public void setCustsalary(String custsalary) {
		this.custsalary = custsalary;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Customer [custCity=" + custCity + ", custEmail=" + custEmail
				+ ", custId=" + custId + ", custName=" + custName
				+ ", custsalary=" + custsalary + ", date=" + date + ", gender="
				+ gender + ", phone=" + phone + "]";
	}

}
