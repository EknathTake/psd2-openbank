package com.psd2.openbank.web;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.psd2.openbank.security.jwt.Psd2OpenBankInvalidJwtAuthenticationException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

	/*@ExceptionHandler(value = { VehicleNotFoundException.class })
	public ResponseEntity vehicleNotFound(VehicleNotFoundException ex, WebRequest request) {
		// log.debug("handling VehicleNotFoundException...");
		return notFound().build();
	}
*/
	@ExceptionHandler(value = { Psd2OpenBankInvalidJwtAuthenticationException.class })
	public ResponseEntity invalidJwtAuthentication(Psd2OpenBankInvalidJwtAuthenticationException ex, WebRequest request) {
		// log.debug("handling InvalidJwtAuthenticationException...");
		return status(UNAUTHORIZED).build();
	}
}
