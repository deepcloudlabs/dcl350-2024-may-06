package com.example.hr.dto.response;

import com.example.ddd.DataTransferObject;
import com.example.hr.domain.Employee;

@DataTransferObject(Employee.class)
public record PhotoResponse(String photo) {
	
}
