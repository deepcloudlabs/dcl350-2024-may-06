package com.example.hr.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.BirthYear;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;

@Configuration
@ConditionalOnProperty(name="persistenceStrategy", havingValue = "jpa")
public class ModelMapperJpaConfig {
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
	
	
	public ModelMapperJpaConfig(ModelMapper modelMapper) {
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
	}

}
