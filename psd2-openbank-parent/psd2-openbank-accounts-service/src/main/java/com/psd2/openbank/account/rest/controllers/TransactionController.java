package com.psd2.openbank.account.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psd2.openbank.account.service.TransactionService;
import com.psd2.openbank.transaction.controller.response.TransactionResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author eknath.take
 *
 */
@Slf4j
@RestController
@RequestMapping(path = "/api")
public class TransactionController {

	@Autowired
	// @Qualifier("accountTransactionService ")
	private TransactionService accountTransactionService;

	/**
	 * To make a call to the Specific Account Transactions API, you will need to
	 * account-specific access token, as granted by the account holder. This access
	 * token can be retrieved via the APIs found in the Authorization collection.
	 * 
	 * @param AccountId - A unique identifier used to identify the account resource.
	 * @return
	 */
	@GetMapping(path = "/retailbanking/v2.0/accounts/{AccountId}/transactions")
	public ResponseEntity<Object> specificAccountTransactions(@PathVariable(name = "AccountId") long accountId) {

		log.info("Entered Inside AccountTransactionController.specificAccountTransactions.");
		if (accountId == 0) {
			log.warn("Enpty accountId {}", accountId);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<TransactionResponse> specificAccountTransactions = accountTransactionService
				.getSpecificAccountTransactions(accountId);
		return new ResponseEntity<>(specificAccountTransactions, HttpStatus.OK);
	}

}
