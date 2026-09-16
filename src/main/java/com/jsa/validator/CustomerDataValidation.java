package com.jsa.validator;

import com.jsa.model.Customer;

public class CustomerDataValidation {

	boolean valid = false;

	public static boolean validateRequired(String value) {

		boolean valid = false;
		if (value != null && value.length() > 0) {
			valid = true;
		}
		return valid;

	}

	public static boolean minLength(String value, int len) {
		boolean valid = false;
		if (value != null && value.length() >= len) {
			valid = true;
		}
		return valid;
	}

	public static boolean maxLength(String value, int len) {
		boolean valid = false;
		if (value != null && value.length() <= len) {
			valid = true;
		}
		return valid;
	}

	public static boolean validateLong(String value) {
		boolean valid = false;
		if (value != null) {
			try {
				long l = Long.parseLong(value);
				valid = true;
			} catch (Exception e) {
			}
		}
		return valid;
	}

	public static boolean validateName(String value) {
		boolean valid = false;
		if (value != null) {
			valid = value.trim().matches("^[A-Z][a-z]*");
		}
		return valid;
	}

	public static boolean validateFirstCharactersAsUpper(String value) {
		return Character.isUpperCase(value.charAt(0));
	}

	public static boolean validateEmail(String email) {
		boolean valid = false;
		String pattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
		if (email != null) {
			valid = email.matches(pattern);
		}
		return valid;
	}

	public static boolean validateRequired(Customer cust1) {
		// TODO Auto-generated method stub
		return true;
	}
}
