package com.example.hr.application.business;

import java.util.Optional;

import com.example.ddd.Application;
import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.domain.event.EmployeeFiredEvent;
import com.example.hr.domain.event.EmployeeHiredEvent;
import com.example.hr.domain.event.HrEvent;
import com.example.hr.infra.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

@Application(HrApplication.class)
public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	private final EventPublisher<HrEvent> publisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher<HrEvent> publisher) {
		this.employeeRepository = employeeRepository;
		this.publisher = publisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var kimlik = employee.getIdentity();
		if (employeeRepository.exists(kimlik))
			throw new IllegalArgumentException("Employee[%s] already exists.".formatted(kimlik.getValue()));	
		var persistedEmployee = employeeRepository.persistEmployee(employee);
		var event = new EmployeeHiredEvent(kimlik);
		publisher.publishEvent(event);
		return persistedEmployee;
	}

	@Override
	public Employee fireEmployee(TcKimlikNo kimlik) {
		var employee = employeeRepository.findEmployeeByIdentity(kimlik)
				                         .orElseThrow(() -> new IllegalArgumentException("Employee[%s] does not exist.".formatted(kimlik.getValue())));
		employeeRepository.remove(employee);
		var event = new EmployeeFiredEvent(employee);
		publisher.publishEvent(event);		
		return employee;
	}

	@Override
	public Optional<Employee> getEmployee(TcKimlikNo kimlik) {
		return employeeRepository.findEmployeeByIdentity(kimlik);
	}

}
