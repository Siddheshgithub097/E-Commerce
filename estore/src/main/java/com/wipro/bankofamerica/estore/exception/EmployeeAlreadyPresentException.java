package com.wipro.bankofamerica.estore.exception;

public class EmployeeAlreadyPresentException  extends RuntimeException
{
	private String message;

	public EmployeeAlreadyPresentException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
}
