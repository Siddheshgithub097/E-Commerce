package com.wipro.bankofamerica.estore.exception;

public class IllegalArgumentException extends RuntimeException
{
	private String message;



	public IllegalArgumentException(String message) {
		super();
		this.message = message;
	}



	@Override
	public String getMessage() {
		return message;
	}



}
