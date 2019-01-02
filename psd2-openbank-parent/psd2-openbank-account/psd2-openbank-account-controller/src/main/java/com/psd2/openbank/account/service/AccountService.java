package com.psd2.openbank.account.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.psd2.openbank.account.controller.request.AccountRequest;
import com.psd2.openbank.account.controller.response.AccountResponse;
import com.psd2.openbank.account.exception.InvalidDatesForTransaction;

public interface AccountService {

	AccountResponse createAccount(@RequestBody AccountRequest request) throws InvalidDatesForTransaction;
}
