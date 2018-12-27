package com.psd2.openbank.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.psd2.openbank.account.controller.response.TransactionResponse;

@Service("accountTransactionService")
public class AccountTransactionServiceImpl implements AccountTransactionService {

	@Override
	public List<TransactionResponse> getSpecificAccountTransactions(long accountId) {
		return new ArrayList<>();
	}

}
