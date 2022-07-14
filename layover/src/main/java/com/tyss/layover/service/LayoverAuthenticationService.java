package com.tyss.layover.service;

import java.util.Map;

public interface LayoverAuthenticationService  {
	
	String authenticate(String email);
	Map<String, Object> validateOtp(Integer otp);

}
