package com.tyss.layover.handler;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tyss.layover.exception.AccountNotApprovedException;
import com.tyss.layover.exception.IncorrectOtp;
import com.tyss.layover.exception.UserNotFoundException;
import com.tyss.layover.response.LayoverResponse;

@RestControllerAdvice
public class LayoverExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<LayoverResponse> internalServerError(RuntimeException exception, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(LayoverResponse.builder().isError(true).message(exception.getMessage()).build());
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<LayoverResponse> fileNotFoundException(FileNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(LayoverResponse.builder().isError(true).message(exception.getMessage()).build());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<LayoverResponse> notFoundException(UserNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(LayoverResponse.builder().isError(true).message(exception.getMessage()).build());
	}

	@ExceptionHandler(IncorrectOtp.class)
	public ResponseEntity<LayoverResponse> incorrectOtp(IncorrectOtp exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(LayoverResponse.builder().isError(true).message(exception.getMessage()).build());
	}

	@ExceptionHandler(AccountNotApprovedException.class)
	public ResponseEntity<LayoverResponse> accountNotApprovedException(AccountNotApprovedException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(LayoverResponse.builder().isError(true).message(exception.getMessage()).build());

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errorList = exception.getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		return new ResponseEntity<>(
				LayoverResponse.builder().isError(true).message("Validation Errors").data(errorList).build(),
				HttpStatus.BAD_REQUEST);
	}
}
