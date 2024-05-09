package com.example.hr.entity;

import java.util.List;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
	@Id
	@TcKimlikNo
	@Column(name="id")
	private String kimlik;
	@NotBlank
	@Column(name="fname")
	private String firstName; 
	@NotBlank
	@Column(name="lname")
	private String lastName; 
	@Iban
	private String iban; 
	@Min(20_000) private double salary;
	@Enumerated(EnumType.ORDINAL)
	@NotNull private FiatCurrency currency;
	@ElementCollection
	@NotNull private List<Department> departments;
	@Enumerated(EnumType.STRING)
	@NotNull private JobStyle jobStyle; 
	@Column(name="byear")
	@Min(1950) @Max(2008)
	private int birthYear;
	@Lob
	@Column(columnDefinition = "longblob")
	@NotNull private byte[] photo;
	//@Version
	//private int versiyon;
}
