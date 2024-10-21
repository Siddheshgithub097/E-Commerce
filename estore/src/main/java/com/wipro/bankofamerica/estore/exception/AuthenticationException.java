package com.wipro.bankofamerica.estore.exception;

public class AuthenticationException extends RuntimeException
{
	private  String message ;

	public AuthenticationException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	
	
}
