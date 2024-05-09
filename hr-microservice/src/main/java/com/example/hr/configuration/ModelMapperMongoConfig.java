package com.example.hr.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.BirthYear;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Configuration
@ConditionalOnProperty(name="persistenceStrategy", havingValue = "mongodb")
public class ModelMapperMongoConfig {
	private static final Converter<Employee,EmployeeDocument> EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER =
			context -> {
				var employee = context.getSource();
				return new EmployeeDocument(
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

	private static final Converter<EmployeeDocument,Employee> EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER =
			context -> {
				var document = context.getSource();
				return new Employee.Builder(TcKimlikNo.of(document.getKimlik()), new BirthYear(document.getBirthYear()))
						.fullName(document.getFirstName(), document.getLastName())
						.salary(document.getSalary(), document.getCurrency())
						.iban(document.getIban())
						.jobStyle(document.getJobStyle())
						.departments(document.getDepartments())
						.photo(document.getPhoto())
						.build();
			};
			
	public ModelMapperMongoConfig(ModelMapper modelMapper) {
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER, Employee.class, EmployeeDocument.class);
		modelMapper.addConverter(EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER, EmployeeDocument.class, Employee.class);
	}

}
