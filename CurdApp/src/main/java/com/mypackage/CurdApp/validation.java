package com.mypackage.CurdApp;

public class validation {
	public static boolean nameValidate(String name) {
		boolean valid = false;
		if (name.matches("^[A-Z a-z]+")) {
			valid = true;

		}
		return valid;
	}

	public static boolean passwordValidate(String password) {
		boolean valid = false;
		if (password.length() < 12 && password.length() > 5) {
//			if (password.matches("[A-Za-z0-9\\S]")) {
			valid = true;
//			}
		}

		return valid;
	}

	public static boolean emailValidate(String email) {
		boolean valid = false;
		if (email.matches("^[a-z0-9\\.]+[@](gmail|agshealth).com+\\b")) {
			valid = true;
		}
		return valid;
	}

	public static boolean contactValidate(String contact) {
		boolean valid = false;
		if (contact.matches("^[9867][0-9]\\d{8}\\b")) {
			valid = true;
		}
		return valid;
	}
}
