package com.example.hr.dto.request;

import java.util.List;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record HireEmployeeRequest(
		@TcKimlikNo String identity, 
		@NotEmpty String firstName, 
		@NotEmpty String lastName, 
		@Iban String iban, 
		@Min(20_000) double salary,
		@NotNull FiatCurrency currency, 
		@NotNull List<Department> departments, 
		@NotNull JobStyle jobStyle, 
		@Min(1950) @Max(2008) int birthYear,
		@NotEmpty String photo) {

}
