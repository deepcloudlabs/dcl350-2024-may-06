package com.example.hr.domain.event;

import com.example.hr.domain.Employee;

public class EmployeeFiredEvent extends HrEvent {
	private final Employee employee;

	public EmployeeFiredEvent(Employee employee) {
		super(employee.getIdentity());
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

}
