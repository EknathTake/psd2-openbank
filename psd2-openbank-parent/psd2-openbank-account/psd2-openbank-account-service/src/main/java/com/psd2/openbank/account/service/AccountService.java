package com.psd2.openbank.account.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.psd2.openbank.account.exception.InvalidDatesForTransaction;
import com.psd2.openbank.account.service.request.AccountRequest;
import com.psd2.openbank.account.service.response.AccountResponse;

public interface AccountService {

	AccountResponse createAccount(@RequestBody AccountRequest request) throws InvalidDatesForTransaction;
}
