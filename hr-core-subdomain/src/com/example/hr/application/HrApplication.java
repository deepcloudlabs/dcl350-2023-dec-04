package com.example.hr.application;

import java.util.Optional;

import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Port(PortType.DRIVING)
public interface HrApplication {
	Optional<Employee> getEmployee(TcKimlikNo identity);
	Employee hireEmployee(Employee employee);
	Employee fireEmployee(TcKimlikNo identity);	
}
