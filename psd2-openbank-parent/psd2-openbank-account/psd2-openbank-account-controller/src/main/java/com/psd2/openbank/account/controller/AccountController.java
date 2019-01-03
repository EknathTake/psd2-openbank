package com.psd2.openbank.account.controller;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.psd2.openbank.account.controller.request.AccountRequest;
import com.psd2.openbank.account.controller.response.AccountResponse;
import com.psd2.openbank.account.exception.InvalidDatesForTransaction;
import com.psd2.openbank.account.service.AccountService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class AccountController {

	@Inject
	@Named("accountService")
	private AccountService accountService;

	/**
	 * To make a call to the Create an account request API, you will need to know
	 * the request information body, the account-specific access token, as granted
	 * by the account holder. This access token can be retrieved via the APIs found
	 * in the Authorization collection.
	 * 
	 * @return AccountResponse
	 * @throws InvalidDatesForTransaction
	 */
	@PostMapping(path = "/retailbanking/v2.0/account-requests", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Authenticated User Login", response = AccountRequest.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = AccountRequest.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public AccountResponse createAccount(@Valid @RequestBody AccountRequest request) throws InvalidDatesForTransaction {

		AccountResponse accountServiceResponse = accountService.createAccount(request);
		// log.info("createAccount: {}", accountServiceResponse);
		return accountServiceResponse;
	}
}
