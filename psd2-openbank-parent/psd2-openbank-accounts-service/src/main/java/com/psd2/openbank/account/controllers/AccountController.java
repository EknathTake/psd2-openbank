package com.psd2.openbank.account.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.psd2.openbank.account.request.AccountRequest;
import com.psd2.openbank.account.response.AccountResponse;
import com.psd2.openbank.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class AccountController {

	@Autowired
	private AccountService accountService;

	/**
	 * To make a call to the Create an account request API, you will need to know
	 * the request information body, the account-specific access token, as granted
	 * by the account holder. This access token can be retrieved via the APIs found
	 * in the Authorization collection.
	 * 
	 * @return AccountResponse
	 */
	@PostMapping(path = "/retailbanking/v2.0/account-requests")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AccountResponse createAccount(@RequestBody AccountRequest request) {

		return accountService.createAccount(request);
	}

}
