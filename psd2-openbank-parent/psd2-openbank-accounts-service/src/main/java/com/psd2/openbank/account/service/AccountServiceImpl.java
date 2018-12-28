package com.psd2.openbank.account.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
				.transactionToDateTime(request.getTransactionToDateTime()).creationDateTime(new Date())
				.expirationDateTime(request.getExpirationDateTime())
				.permissions(mapPermissionRequestToModel(request.getPermissions()))
				.status(com.psd2.openbank.account.models.AccountStatus.valueOf("AWAITING_AUTHORISATION")).build();

		AccountEntity savedEntity = accountRepository.save(entity);

		return AccountResponse.builder().accountRequestId(savedEntity.getAccountRequestId() + "")
				.creationDateTime(savedEntity.getCreationDateTime())
				.expirationDateTime(savedEntity.getExpirationDateTime())
				.transactionFromDateTime(savedEntity.getTransactionFromDateTime())
				.transactionToDateTime(savedEntity.getTransactionToDateTime())
				.status(AccountStatus.AWAITING_AUTHORISATION)
				.permissions(mapAccountPermissionModelToResponse(savedEntity.getPermissions())).build();
	}

	/**
	 * request to model mapper
	 * 
	 * @param permissionsRequest
	 * @return
	 */
	private Set<AccountPermissions> mapPermissionRequestToModel(Set<String> permissionsRequest) {
		Set<AccountPermissions> permissionsModel = new HashSet<>();
		for (String string : permissionsRequest) {
			switch (string) {
			case "ReadAccountsBasic":
				permissionsModel.add(AccountPermissions.READ_ACCOUNT_BASIC);
				break;
			case "ReadAccountsDetail":
				permissionsModel.add(AccountPermissions.READ_ACCOUNT_DETAIL);
				break;
			case "ReadBalances":
				permissionsModel.add(AccountPermissions.READ_BALANCE);
				break;
			case "ReadBeneficiariesBasic":
				permissionsModel.add(AccountPermissions.READ_BENEFICIARIES_BASIC);
				break;
			case "ReadBeneficiariesDetail":
				permissionsModel.add(AccountPermissions.READ_BENEFICIARIES_DETAIL);
				break;
			case "ReadDirectDebits":
				permissionsModel.add(AccountPermissions.READ_DIRECT_DEBITS);
				break;
			case "ReadOffers":
				permissionsModel.add(AccountPermissions.READ_OFFERS);
				break;
			case "ReadPAN":
				permissionsModel.add(AccountPermissions.READ_PAN);
				break;
			case "ReadParty":
				permissionsModel.add(AccountPermissions.READ_PARTY);
				break;
			case "ReadPartyPSU":
				permissionsModel.add(AccountPermissions.READ_PARTY_PSU);
				break;
			case "ReadProducts":
				permissionsModel.add(AccountPermissions.READ_PRODUCTS);
				break;
			case "ReadScheduledPaymentsBasic":
				permissionsModel.add(AccountPermissions.READ_SCHEDULED_PAYMENTS_BASIC);
				break;
			case "ReadScheduledPaymentsDetail":
				permissionsModel.add(AccountPermissions.READ_SCHEDULED_PAYMENTS_DETAIL);
				break;
			case "ReadStandingOrdersBasic":
				permissionsModel.add(AccountPermissions.READ_STANDING_ORDERS_BASIC);
				break;
			case "ReadStandingOrdersDetail":
				permissionsModel.add(AccountPermissions.READ_STANDING_ORDERS_DETAIL);
				break;
			case "ReadStatementsBasic":
				permissionsModel.add(AccountPermissions.READ_STATEMENTS_BASIC);
				break;
			case "ReadStatementsDetail":
				permissionsModel.add(AccountPermissions.READ_STATEMENTS_DETAIL);
				break;
			case "ReadTransactionsBasic":
				permissionsModel.add(AccountPermissions.READ_TRANSACTIONS_BASIC);
				break;
			case "ReadTransactionsCredits":
				permissionsModel.add(AccountPermissions.READ_TRANSACTIONS_CREDITS);
				break;
			case "ReadTransactionsDebits":
				permissionsModel.add(AccountPermissions.READ_TRANSACTIONS_DEBITS);
				break;
			case "ReadTransactionsDetail":
				permissionsModel.add(AccountPermissions.READ_TRANSACTIONS_DETAILS);
				break;
			default:
				break;
			}

		}
		return permissionsModel;
	}

	/**
	 * model to response mapper
	 * 
	 * @param permissions
	 * @return
	 */
	private Set<com.psd2.openbank.account.response.AccountPermissions> mapAccountPermissionModelToResponse(
			Set<AccountPermissions> permissions) {
		Set<com.psd2.openbank.account.response.AccountPermissions> permissionsResponse = new HashSet<>();
		for (AccountPermissions accountPermissions : permissions) {
			switch (accountPermissions) {
			case READ_ACCOUNT_BASIC:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_ACCOUNT_BASIC);
				break;
			case READ_ACCOUNT_DETAIL:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_ACCOUNT_DETAIL);
				break;
			case READ_BALANCE:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_BALANCE);
				break;
			case READ_BENEFICIARIES_BASIC:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_BENEFICIARIES_BASIC);
				break;
			case READ_BENEFICIARIES_DETAIL:
				permissionsResponse
						.add(com.psd2.openbank.account.response.AccountPermissions.READ_BENEFICIARIES_DETAIL);
				break;
			case READ_DIRECT_DEBITS:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_DIRECT_DEBITS);
				break;
			case READ_OFFERS:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_OFFERS);
				break;
			case READ_PAN:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_PAN);
				break;
			case READ_PARTY:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_PARTY);
				break;
			case READ_PARTY_PSU:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_PARTY_PSU);
				break;
			case READ_PRODUCTS:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_PRODUCTS);
				break;
			case READ_SCHEDULED_PAYMENTS_BASIC:
				permissionsResponse
						.add(com.psd2.openbank.account.response.AccountPermissions.READ_SCHEDULED_PAYMENTS_BASIC);
				break;
			case READ_SCHEDULED_PAYMENTS_DETAIL:
				permissionsResponse
						.add(com.psd2.openbank.account.response.AccountPermissions.READ_SCHEDULED_PAYMENTS_DETAIL);
				break;
			case READ_STANDING_ORDERS_BASIC:
				permissionsResponse
						.add(com.psd2.openbank.account.response.AccountPermissions.READ_STANDING_ORDERS_BASIC);
				break;
			case READ_STANDING_ORDERS_DETAIL:
				permissionsResponse
						.add(com.psd2.openbank.account.response.AccountPermissions.READ_STANDING_ORDERS_DETAIL);
				break;
			case READ_STATEMENTS_BASIC:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_STATEMENTS_BASIC);
				break;
			case READ_STATEMENTS_DETAIL:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_STATEMENTS_DETAIL);
				break;
			case READ_TRANSACTIONS_BASIC:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_TRANSACTIONS_BASIC);
				break;
			case READ_TRANSACTIONS_CREDITS:
				permissionsResponse
						.add(com.psd2.openbank.account.response.AccountPermissions.READ_TRANSACTIONS_CREDITS);
				break;
			case READ_TRANSACTIONS_DEBITS:
				permissionsResponse.add(com.psd2.openbank.account.response.AccountPermissions.READ_TRANSACTIONS_DEBITS);
				break;
			case READ_TRANSACTIONS_DETAILS:
				permissionsResponse
						.add(com.psd2.openbank.account.response.AccountPermissions.READ_TRANSACTIONS_DETAILS);
				break;

			default:
				break;
			}

		}
		return permissionsResponse;
	}

}
