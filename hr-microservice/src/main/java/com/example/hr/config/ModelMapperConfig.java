package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

@Configuration
public class ModelMapperConfig {
	private static final Converter<Employee,EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER =
    context -> {
    	var employee = context.getSource();
    	var response = new EmployeeResponse();
    	response.setIdentity(employee.getIdentity().getValue());
    	response.setFirstName(employee.getFullname().firstName());
    	response.setLastName(employee.getFullname().lastName());
    	response.setIban(employee.getIban().value());
    	response.setSalary(employee.getSalary().getValue());
    	response.setCurrency(employee.getSalary().getCurrency());
    	response.setJobStyle(employee.getJobStyle());
    	response.setDepartments(employee.getDepartments());
    	response.setBirthYear(employee.getBirthYear().value());
    	return response;
    };			
    private static final Converter<Employee,HireEmployeeResponse> EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER =
	context -> {
		var employee = context.getSource();
		var response = new HireEmployeeResponse();
		response.setIdentity(employee.getIdentity().getValue());
		response.setFirstName(employee.getFullname().firstName());
		response.setLastName(employee.getFullname().lastName());
		response.setIban(employee.getIban().value());
		response.setSalary(employee.getSalary().getValue());
		response.setCurrency(employee.getSalary().getCurrency());
		response.setJobStyle(employee.getJobStyle());
		response.setDepartments(employee.getDepartments());
		response.setBirthYear(employee.getBirthYear().value());
		return response;
	};			
	
	private static final Converter<HireEmployeeRequest,Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER =
	context -> {
		var request = context.getSource();
		return new Employee.Builder()
				.identity(request.getIdentity())
		        .fullname(request.getFirstName(),request.getLastName())
		        .iban(request.getIban())
		        .salary(request.getSalary(),request.getCurrency())
		        .departments(request.getDepartments())
		        .jobStyle(request.getJobStyle())
                .birthYear(request.getBirthYear())
                .photo(request.getPhoto())
		        .build();
	};			
			
	@Bean
	ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, HireEmployeeResponse.class);
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		return modelMapper;
	}
}
