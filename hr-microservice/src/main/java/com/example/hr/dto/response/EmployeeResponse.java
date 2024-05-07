package com.example.hr.dto.response;

import java.util.List;

import com.example.ddd.DataTransferObject;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

@DataTransferObject(Employee.class)
public record EmployeeResponse(String identity, String firstName, String lastName, String iban, double salary,
		FiatCurrency currency, List<Department> departments, JobStyle jobStyle, int birthYear) {
}
