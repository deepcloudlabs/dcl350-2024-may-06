package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject // immutable -> 2. record (since Java SE 14)
public record FullName(String firstName, String lastName) {
	public FullName(String firstName, String lastName) {
		// validation
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public static FullName valueOf(String firstName, String lastName) {
		// validation
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
		return new FullName(firstName, lastName);
	}
}
