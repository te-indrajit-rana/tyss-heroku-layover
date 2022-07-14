package com.tyss.layover.exception;

public class IncorrectOtp extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectOtp(String message){
		super(message);
	}
}
