package com.example.hr.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDocument {
	@Id
	@TcKimlikNo
	private String kimlik;
	@NotBlank
	@Field(name="fname")
	private String firstName; 
	@NotBlank
	@Field(name="lname")
	private String lastName; 
	@Iban
	@Indexed(unique = true)
	private String iban; 
	@Min(20_000) private double salary;
	@NotNull private FiatCurrency currency;
	@NotNull private List<Department> departments;
	@NotNull private JobStyle jobStyle; 
	@Field(name="byear")
	@Min(1950) @Max(2008)
	private int birthYear;
	@NotEmpty private String photo;
}
