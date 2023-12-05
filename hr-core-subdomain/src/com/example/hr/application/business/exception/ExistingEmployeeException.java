package com.example.hr.application.business.exception;

import com.example.hr.domain.TcKimlikNo;

@SuppressWarnings("serial")
public class ExistingEmployeeException extends RuntimeException {
	private final TcKimlikNo identity;

	public ExistingEmployeeException(String message, TcKimlikNo identity) {
		super(message);
		this.identity = identity;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}
	
}
