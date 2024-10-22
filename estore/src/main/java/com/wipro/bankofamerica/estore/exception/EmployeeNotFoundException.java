package com.wipro.bankofamerica.estore.exception;

public class EmployeeNotFoundException  extends RuntimeException
{

	private String message ;

	
	public EmployeeNotFoundException(String message) {
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}

	
	
}
