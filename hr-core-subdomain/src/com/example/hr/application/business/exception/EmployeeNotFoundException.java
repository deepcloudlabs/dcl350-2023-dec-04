package com.example.hr.application.business.exception;

import com.example.hr.domain.TcKimlikNo;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {
	private final TcKimlikNo identity;

	public EmployeeNotFoundException(String message, TcKimlikNo identity) {
		super(message);
		this.identity = identity;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}
	
}
