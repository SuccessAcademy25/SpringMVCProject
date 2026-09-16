 package com.jsa.model;

public class Serachcriteria {

	private int id;
	private String email;
	private long phone;

	public Serachcriteria() {
		super();

	}

	public Serachcriteria(int id, String email, long phone) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Serachcriteria [email=" + email + ", id=" + id + ", phone="
				+ phone + "]";
	}

}
