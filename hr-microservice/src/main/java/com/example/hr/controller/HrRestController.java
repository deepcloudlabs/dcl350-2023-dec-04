package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hexagonal.Adapter;
import com.example.hr.application.HrApplication;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlikNo;

@Adapter(port=HrApplication.class)
@RestController
@RequestScope
@RequestMapping("/employees")
@Validated
@CrossOrigin
public class HrRestController {
	private final HrService hrService;
	
	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}

	// GET /employees/11111111110
	@GetMapping("{identity}")
	public EmployeeResponse getEmployee(
			@PathVariable @TcKimlikNo String identity) {
		return hrService.findEmployeeById(identity);
	}
	
	// POST /employees
	@PostMapping
	public HireEmployeeResponse hireEmployee(
			@RequestBody @Validated HireEmployeeRequest request) {
		return hrService.hireEmployee(request);		
	}
	
	// DELETE /employees/11111111110
	@DeleteMapping("{identity}")
	public EmployeeResponse fireEmployee(
			@PathVariable @TcKimlikNo String identity) {
		return hrService.fireEmployee(identity);				
	}	
}
