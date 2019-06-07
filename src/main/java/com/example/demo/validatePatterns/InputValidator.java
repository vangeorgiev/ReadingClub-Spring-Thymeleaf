package com.example.demo.validatePatterns;



import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.model.User;
import com.example.demo.model.ENUMS.Role;

public class InputValidator {
	public static void validateUser(User u) {
		checkString(u.getFirstName(), PatternValidator.name, "Invalid Name");
		checkString(u.getLastName(), PatternValidator.name, "Invalid last name");
		checkString(u.getEmail(), PatternValidator.email, "Invalid email");
		checkValidUsername(u.getUserName());
		checkValidPassword(u.getPassword());
		// checkValidRoles(u.getRole());
	};


	public static void checkString(String param, String validationPatterns, String invalidInputMsg) {
		if (!param.matches(validationPatterns)) {
			throw new IllegalArgumentException(invalidInputMsg);
		}
	}

	public static void checkValidUsername(String username) {
		if (!username.matches(PatternValidator.username)) {
			throw new IllegalArgumentException("Invalid username.");
		}
	}

	public static void checkValidPassword(String password) {
		if (!password.matches(PatternValidator.password)) {
			throw new IllegalArgumentException("Invalid password");
		}
	}

	public static String  checkValidGender(String role) {
		if (!(role.equals("MALE") || role.equals("FEMALE"))) {
			throw new IllegalArgumentException(String.format("Invalid gender"));
		}
		return role;
	}

	private static List<Role> checkValidRoles(List<Role> inputRoles) {
		Set<Role> roles = new HashSet<>(Arrays.asList(Role.values()));
		for (Role role : inputRoles) {
			if (!roles.contains(role)) {
				throw new IllegalArgumentException(String.format("Invalid role: %s", role));
			}
		}

		return inputRoles;
	}
}
