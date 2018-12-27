package com.psd2.openbank.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.psd2.openbank.transaction.controller.response.TransactionResponse;

@Service("accountTransactionService")
public class TransactionServiceImpl implements TransactionService {

	@Override
	public List<TransactionResponse> getSpecificAccountTransactions(long accountId) {
		return new ArrayList<>();
	}

}
