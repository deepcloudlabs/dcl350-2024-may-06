package com.example.hr.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.BirthYear;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.entity.EmployeeEntity;

@Configuration
public class ModelMapperConfig {
	private static final Converter<Employee,EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER =
			context -> {
				var employee = context.getSource();
				return new EmployeeResponse(
						employee.getIdentity().getValue(),
						employee.getFullName().firstName(),
						employee.getFullName().lastName(),
						employee.getIban().getValue(),
						employee.getSalary().value(),
						employee.getSalary().currency(),
						employee.getDepartments(),
						employee.getJobStyle(),
						employee.getBirthYear().value()
					);
			};
			
	private static final Converter<Employee,HireEmployeeResponse> EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER =
			context -> {
				var employee = context.getSource();
				return new HireEmployeeResponse(
						employee.getIdentity().getValue(),
						employee.getFullName().firstName(),
						employee.getFullName().lastName(),
						employee.getIban().getValue(),
						employee.getSalary().value(),
						employee.getSalary().currency(),
						employee.getDepartments(),
						employee.getJobStyle(),
						employee.getBirthYear().value(),
						employee.getPhoto().toBase64()
					);
			};	
			
	private static final Converter<HireEmployeeRequest,Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER =
			context -> {
				var request = context.getSource();
				return new Employee.Builder(TcKimlikNo.of(request.identity()), new BirthYear(request.birthYear()))
						.fullName(request.firstName(), request.lastName())
						.salary(request.salary(), request.currency())
						.iban(request.iban())
						.jobStyle(request.jobStyle())
						.departments(request.departments())
						.photo(request.photo())
						.build();
			};
			
	private static final Converter<EmployeeEntity,Employee> EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER =
			context -> {
				var entity = context.getSource();
				return new Employee.Builder(TcKimlikNo.of(entity.getKimlik()), new BirthYear(entity.getBirthYear()))
						.fullName(entity.getFirstName(), entity.getLastName())
						.salary(entity.getSalary(), entity.getCurrency())
						.iban(entity.getIban())
						.jobStyle(entity.getJobStyle())
						.departments(entity.getDepartments())
						.photo(entity.getPhoto())
						.build();
			};

			
	private static final Converter<Employee,EmployeeEntity> EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER =
			context -> {
				var employee = context.getSource();
				return new EmployeeEntity(
						employee.getIdentity().getValue(),
						employee.getFullName().firstName(),
						employee.getFullName().lastName(),
						employee.getIban().getValue(),
						employee.getSalary().value(),
						employee.getSalary().currency(),
						employee.getDepartments(),
						employee.getJobStyle(),
						employee.getBirthYear().value(),
						employee.getPhoto().values()
					);
			};
			
	@Bean
	ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, HireEmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
		return modelMapper;
	}

}
