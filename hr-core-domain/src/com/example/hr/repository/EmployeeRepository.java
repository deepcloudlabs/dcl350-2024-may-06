package com.example.hr.repository;

import java.util.Optional;

import com.example.ddd.DomainRepository;
import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@DomainRepository(Employee.class)
@Port(PortType.DRIVEN_PORT)
public interface EmployeeRepository {

	boolean exists(TcKimlikNo kimlik);

	Employee persistEmployee(Employee employee);

	Optional<Employee> findEmployeeByIdentity(TcKimlikNo kimlik);

	void remove(Employee employee);

}
