package com.tyss.layover.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.layover.constant.LayoverConstant;
import com.tyss.layover.response.LayoverResponse;
import com.tyss.layover.service.LayoverAuthenticationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/layover/auth")
@SecurityRequirement(name = "layover-api")
public class LayoverAuthenticationController {

	private final LayoverAuthenticationService authenticationServiceImpl;

	@PostMapping("/get-otp/{email}")
	public ResponseEntity<LayoverResponse> authenticate(@PathVariable("email") String email) {
		String authenticate = authenticationServiceImpl.authenticate(email);
		return ResponseEntity.ok().body(LayoverResponse.builder().isError(false).message(authenticate).build());
	}

	@GetMapping("/validate-otp/{otp}")
	public ResponseEntity<LayoverResponse> validateOtp(@PathVariable("otp")Integer otp) {
		Map<String, Object> validateOtp = authenticationServiceImpl.validateOtp(otp);
		return ResponseEntity.ok().body(LayoverResponse.builder().isError(false)
				.message(LayoverConstant.ACCESS_TOKEN_AND_REFRESH_TOKEN_GENRATED_SUCCESSFULLY).data(validateOtp).build());

	}

}
