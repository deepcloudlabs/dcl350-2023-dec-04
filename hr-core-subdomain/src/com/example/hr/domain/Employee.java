package com.example.hr.domain;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import com.example.ddd.Entity;

// DDD
// Problem Domain    ------------> Solution Domain
// [core] Sub-domain -- design --> Bounded-Context
// Model: Ubiquitous Language  --> Employee, TcKimlikNo,FullName,Money,... 
// Entity Class: i)Identity ii) Persist iii) Mutable
@Entity(identity = "identity", aggregate=true)
public class Employee {
    private final TcKimlikNo identity;
    private FullName fullname;
    private Money salary;
    private Iban iban;
    private Department[] departments;
    private JobStyle jobStyle;
    private final BirthYear birthYear;
    private Photo photo;
    
	public FullName getFullname() {
		return fullname;
	}

	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}

	public Money getSalary() {
		return salary;
	}

	public void setSalary(Money salary) {
		this.salary = salary;
	}

	public Iban getIban() {
		return iban;
	}

	public void setIban(Iban iban) {
		this.iban = iban;
	}

	public Department[] getDepartments() {
		return departments;
	}

	public void setDepartments(Department[] departments) {
		this.departments = departments;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public void setJobStyle(JobStyle jobStyle) {
		this.jobStyle = jobStyle;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	private Employee(Builder builder) {
		this.identity = builder.identity;
		this.fullname = builder.fullname;
		this.salary = builder.salary;
		this.iban = builder.iban;
		this.departments = builder.departments;
		this.jobStyle = builder.jobStyle;
		this.birthYear = builder.birthYear;
		this.photo = builder.photo;
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
		return "Employee [identity=" + identity + ", fullname=" + fullname + ", salary=" + salary + ", iban=" + iban
				+ ", departments=" + Arrays.toString(departments) + ", jobStyle=" + jobStyle + ", birthYear="
				+ birthYear + "]";
	}

	public void increaseSalary(double rate) {
		var newSalary = this.salary.multiply(1+rate/100.0);
		// Validation Rule
		// Business Rule
		// Invariants
		// Constraint
		// Policy
		this.salary = newSalary;
	} 
	
	public static class Builder {
	    private TcKimlikNo identity;
	    private FullName fullname;
	    private Money salary;
	    private Iban iban;
	    private Department[] departments;
	    private JobStyle jobStyle;
	    private BirthYear birthYear;
	    private Photo photo;
	    
	    public Builder identity(String value) {
	    	this.identity = TcKimlikNo.valueOf(value);
	    	return this;
	    }
	    public Builder fullname(String firstName,String lastName) {
	    	this.fullname = FullName.of(firstName, lastName);
	    	return this;
	    }
	    public Builder salary(double value,FiatCurrency currency) {
	    	this.salary = Money.of(value, currency);
	    	return this;
	    }
	    public Builder salary(double value) {
	    	return salary(value,FiatCurrency.TL);
	    }
	    public Builder iban(String value) {
	    	this.iban = Iban.valueOf(value);
	    	return this;
	    }
	    public Builder departments(Department... values) {
	    	this.departments = values;
	    	return this;
	    }
	    public Builder departments(String... values) {
	    	this.departments = Arrays.stream(values).map(Department::valueOf).toList().toArray(new Department[0]);
	    	return this;
	    }
	    public Builder departments(List<Department> values) {
	    	this.departments = values.toArray(new Department[0]);
	    	return this;
	    }
	    public Builder jobStyle(JobStyle value) {
	    	this.jobStyle = value;
	    	return this;
	    }
	    public Builder jobStyle(String value) {
	    	this.jobStyle = JobStyle.valueOf(value);
	    	return this;
	    }
	    public Builder birthYear(int value) {
	    	this.birthYear = new BirthYear(value);
	    	return this;
	    }	    
	    public Builder photo(byte[] values) {
	    	this.photo = Photo.of(values);
	    	return this;
	    }	    
	    public Builder photo(String values) {
	    	this.photo = Photo.of(Base64.getDecoder().decode(values));
	    	return this;
	    }
	    public Employee build() {
			// Validation Rule
			// Business Rule
			// Invariants
			// Constraint
			// Policy
	    	return new Employee(this);
	    }
	    
	}
    
}
