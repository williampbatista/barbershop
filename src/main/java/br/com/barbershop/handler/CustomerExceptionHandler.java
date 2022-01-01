package br.com.barbershop.handler;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.barbershop.response.DefaultResponse;

@RestControllerAdvice
public class CustomerExceptionHandler {
	
	@ExceptionHandler
	public DefaultResponse handleNumberFormatException(NumberFormatException ex) {
		return new DefaultResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex);
	}

	@ExceptionHandler
	public DefaultResponse handleDateTimeParseException(DateTimeParseException ex) {
		return new DefaultResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex);
	}

	@ExceptionHandler
	public DefaultResponse handleIllegalArgumentException(IllegalArgumentException ex) {
		return new DefaultResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex);
	}

}
