package com.psd2.openbank.account.controller;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.psd2.openbank.account.controller.request.AccountRequest;
import com.psd2.openbank.account.controller.response.AccountResponse;
import com.psd2.openbank.account.controller.response.AccountStatus;
import com.psd2.openbank.account.exception.InvalidDatesForTransaction;
import com.psd2.openbank.account.service.AccountService;
import com.psd2.openbank.account.service.response.AccountPermissions;

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
	public AccountResponse createAccount(@Valid @RequestBody AccountRequest request) throws InvalidDatesForTransaction {

		com.psd2.openbank.account.service.request.AccountRequest serviceAccountRequest = com.psd2.openbank.account.service.request.AccountRequest
				.builder().expirationDateTime(request.getExpirationDateTime())
				.transactionFromDateTime(request.getTransactionFromDateTime())
				.transactionToDateTime(request.getTransactionToDateTime()).permissions(request.getPermissions())
				.build();
		com.psd2.openbank.account.service.response.AccountResponse accountServiceResponse = accountService
				.createAccount(serviceAccountRequest);
		log.info("createAccount: {}", accountServiceResponse);
		return AccountResponse.builder().accountRequestId(accountServiceResponse.getAccountRequestId())
				.creationDateTime(accountServiceResponse.getCreationDateTime())
				.expirationDateTime(accountServiceResponse.getExpirationDateTime())
				.permissions(accountPermissionServiceToControllerMapper(accountServiceResponse.getPermissions()))
				.status(accountStatusServiceToControllerMapper(accountServiceResponse.getStatus()))
				.transactionFromDateTime(accountServiceResponse.getTransactionFromDateTime())
				.transactionToDateTime(accountServiceResponse.getTransactionToDateTime()).build();
	}

	private AccountStatus accountStatusServiceToControllerMapper(
			com.psd2.openbank.account.service.response.AccountStatus statusService) {
		AccountStatus status = null;
		switch (statusService) {
		case AUTHORISED:
			status = AccountStatus.AUTHORISED;
			break;
		case AWAITING_AUTHORISATION:
			status = AccountStatus.AWAITING_AUTHORISATION;
			break;
		case REJECTED:
			status = AccountStatus.REJECTED;
			break;
		case REVOKED:
			status = AccountStatus.REVOKED;
			break;
		default:
			break;
		}
		return status;
	}

	private Set<com.psd2.openbank.account.controller.response.AccountPermissions> accountPermissionServiceToControllerMapper(
			Set<AccountPermissions> permissionsService) {
		Set<com.psd2.openbank.account.controller.response.AccountPermissions> permissionsModel = new HashSet<>();
		if (!CollectionUtils.isEmpty(permissionsService)) {
			for (AccountPermissions accountPermissions : permissionsService) {
				if (accountPermissions != null) {
					switch (accountPermissions) {
					case READ_ACCOUNT_BASIC:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_ACCOUNT_BASIC);
						break;
					case READ_ACCOUNT_DETAIL:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_ACCOUNT_DETAIL);
						break;
					case READ_BALANCE:
						permissionsModel
								.add(com.psd2.openbank.account.controller.response.AccountPermissions.READ_BALANCE);
						break;
					case READ_BENEFICIARIES_BASIC:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_BENEFICIARIES_BASIC);
						break;
					case READ_BENEFICIARIES_DETAIL:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_BENEFICIARIES_DETAIL);
						break;
					case READ_DIRECT_DEBITS:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_DIRECT_DEBITS);
						break;
					case READ_OFFERS:
						permissionsModel
								.add(com.psd2.openbank.account.controller.response.AccountPermissions.READ_OFFERS);
						break;
					case READ_PAN:
						permissionsModel.add(com.psd2.openbank.account.controller.response.AccountPermissions.READ_PAN);
						break;
					case READ_PARTY:
						permissionsModel
								.add(com.psd2.openbank.account.controller.response.AccountPermissions.READ_PARTY);
						break;
					case READ_PARTY_PSU:
						permissionsModel
								.add(com.psd2.openbank.account.controller.response.AccountPermissions.READ_PARTY_PSU);
						break;
					case READ_PRODUCTS:
						permissionsModel
								.add(com.psd2.openbank.account.controller.response.AccountPermissions.READ_PRODUCTS);
						break;
					case READ_SCHEDULED_PAYMENTS_BASIC:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_SCHEDULED_PAYMENTS_BASIC);
						break;
					case READ_SCHEDULED_PAYMENTS_DETAIL:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_SCHEDULED_PAYMENTS_DETAIL);
						break;
					case READ_STANDING_ORDERS_BASIC:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_STANDING_ORDERS_BASIC);
						break;
					case READ_STANDING_ORDERS_DETAIL:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_STANDING_ORDERS_DETAIL);
						break;
					case READ_STATEMENTS_BASIC:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_STATEMENTS_BASIC);
						break;
					case READ_STATEMENTS_DETAIL:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_STATEMENTS_DETAIL);
						break;
					case READ_TRANSACTIONS_BASIC:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_TRANSACTIONS_BASIC);
						break;
					case READ_TRANSACTIONS_CREDITS:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_TRANSACTIONS_CREDITS);
						break;
					case READ_TRANSACTIONS_DEBITS:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_TRANSACTIONS_DEBITS);
						break;
					case READ_TRANSACTIONS_DETAILS:
						permissionsModel.add(
								com.psd2.openbank.account.controller.response.AccountPermissions.READ_TRANSACTIONS_DETAILS);
						break;
					default:
						break;
					}
				}
			}
		}
		return permissionsModel;
	}

}
