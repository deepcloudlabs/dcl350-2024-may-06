package com.example.hr.domain;

import java.util.List;
import java.util.Objects;

import com.example.ddd.Aggregate;
import com.example.ddd.DomainEntity;

// Ubiquitous Language: TcKimlikNo, FullName, Iban, Money,...  
// Entity Class: i) identity ii) persistent
@DomainEntity(id = "identity")
@Aggregate
public class Employee {
	private final TcKimlikNo identity;
	private FullName fullName;
	private Iban iban;
	private Money salary;
	private List<Department> departments;
	private Photo photo;
	private JobStyle jobStyle;
	private final BirthYear birthYear;

	public Employee(TcKimlikNo identity, FullName fullName, Iban iban, Money salary, List<Department> departments,
			Photo photo, JobStyle jobStyle, BirthYear birthYear) {
		this.identity = identity;
		this.fullName = fullName;
		this.iban = iban;
		this.salary = salary;
		this.departments = departments;
		this.photo = photo;
		this.jobStyle = jobStyle;
		this.birthYear = birthYear;
	}

	private Employee(Builder builder) {
		this.identity = builder.identity;
		this.fullName = builder.fullName;
		this.iban = builder.iban;
		this.salary = builder.salary;
		this.departments = builder.departments;
		this.photo = builder.photo;
		this.jobStyle = builder.jobStyle;
		this.birthYear = builder.birthYear;
	}

	public static class Builder {
		private final TcKimlikNo identity;
		private FullName fullName;
		private Iban iban;
		private Money salary;
		private List<Department> departments;
		private Photo photo;
		private JobStyle jobStyle;
		private final BirthYear birthYear;

		public Builder(TcKimlikNo identity, BirthYear birthYear) {
			this.identity = identity;
			this.birthYear = birthYear;
		}

		public Builder fullName(String firstName, String lastName) {
			this.fullName = FullName.valueOf(firstName, lastName);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.of(value);
			return this;
		}

		public Builder salary(double value) {
			this.salary = new Money(value, FiatCurrency.TL);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = new Money(value, currency);
			return this;
		}

		public Builder departments(Department... departments) {
			this.departments = List.of(departments);
			return this;
		}

		public Builder departments(List<Department> departments) {
			this.departments = List.copyOf(departments);
			return this;
		}

		public Builder departments(Department department) {
			this.departments = List.of(department);
			this.departments.add(department);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = new Photo(values);
			return this;
		}

		public Builder photo(String base64Values) {
			this.photo = Photo.of(base64Values);
			return this;
		}

		public Builder jobStyle(JobStyle value) {
			this.jobStyle = value;
			return this;
		}

		public Employee build() {
			// validation rule
			// business rule
			// constraint
			// invariants
			return new Employee(this);
		}

	}

	public Money getSalary() {
		return salary;
	}

	public FullName getFullName() {
		return fullName;
	}

	public Iban getIban() {
		return iban;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public Photo getPhoto() {
		return photo;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}


	public void increaseSalary(double rate) {
		// validation rule
		// business rule
		// constraint
		// invariants
		this.salary = this.salary.multiply(1 + rate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(identity, other.identity);
	}

	@Override
	public String toString() {
		return "Employee [identity=" + identity + ", fullName=" + fullName + ", iban=" + iban + ", salary=" + salary
				+ ", departments=" + departments + ", jobStyle=" + jobStyle + ", birthYear=" + birthYear + "]";
	}

}
