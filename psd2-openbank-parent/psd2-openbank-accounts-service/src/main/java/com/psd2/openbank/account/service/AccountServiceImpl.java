package com.psd2.openbank.account.service;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psd2.openbank.account.models.AccountEntity;
import com.psd2.openbank.account.models.AccountPermissions;
import com.psd2.openbank.account.repositories.AccountRepository;
import com.psd2.openbank.account.request.AccountRequest;
import com.psd2.openbank.account.response.AccountResponse;
import com.psd2.openbank.account.response.AccountStatus;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountResponse createAccount(AccountRequest request) {

		AccountEntity entity = AccountEntity.builder().expirationDateTime(request.getExpirationDateTime())
				.transactionFromDateTime(request.getTransactionFromDateTime())
				.transactionToDateTime(request.getTransactionToDateTime())
				.expirationDateTime(request.getExpirationDateTime())
				.permissions(Stream.of(request.getPermissions()).filter(Objects::nonNull)
						.map(permission -> AccountPermissions.valueOf(permission.getPermission()))
						.collect(Collectors.toSet()))
				.build();

		AccountEntity savedEntity = accountRepository.save(entity);
		return AccountResponse.builder().accountRequestId(savedEntity.getAccountRequestId() + "")
				.creationDateTime(savedEntity.getCreationDateTime())
				.expirationDateTime(savedEntity.getExpirationDateTime())
				.transactionFromDateTime(savedEntity.getTransactionFromDateTime())
				.transactionToDateTime(savedEntity.getTransactionToDateTime())
				.status(savedEntity.getStatus().stream().toArray(AccountStatus[]::new))// TODO: need to debug
				.permissions(savedEntity.getPermissions().stream()
						.toArray(com.psd2.openbank.account.response.AccountPermissions[]::new))// TODO: need to debug
				.build();
	}

}
