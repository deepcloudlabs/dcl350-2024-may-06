package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject
public record Money(double value, FiatCurrency currency) {

	public Money multiply(double factor) {
		return new Money(this.value * factor, currency);
	}

}
