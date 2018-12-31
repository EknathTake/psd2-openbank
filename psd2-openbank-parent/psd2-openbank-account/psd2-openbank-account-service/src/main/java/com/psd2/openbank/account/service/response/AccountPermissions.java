package com.psd2.openbank.account.service.response;

/**
 * Specifies the Open Banking account request types. This is a list of the data
 * clusters being consented by the PSU, and requested for authorisation with the
 * ASPSP.
 * 
 * @author eknath.take
 *
 */
public enum AccountPermissions {
	READ_ACCOUNT_BASIC("ReadAccountsBasic"), READ_ACCOUNT_DETAIL("ReadAccountsDetail"), READ_BALANCE("ReadBalances"),
	READ_BENEFICIARIES_BASIC("ReadBeneficiariesBasic"), READ_BENEFICIARIES_DETAIL("ReadBeneficiariesDetail"),
	READ_DIRECT_DEBITS("ReadDirectDebits"), READ_OFFERS("ReadOffers"), READ_PAN("ReadPAN"), READ_PARTY("ReadParty"),
	READ_PARTY_PSU("ReadPartyPSU"), READ_PRODUCTS("ReadProducts"),
	READ_SCHEDULED_PAYMENTS_BASIC("ReadScheduledPaymentsBasic"),
	READ_SCHEDULED_PAYMENTS_DETAIL("ReadScheduledPaymentsDetail"),
	READ_STANDING_ORDERS_BASIC("ReadStandingOrdersBasic"), READ_STANDING_ORDERS_DETAIL("ReadStandingOrdersDetail"),
	READ_STATEMENTS_BASIC("ReadStatementsBasic"), READ_STATEMENTS_DETAIL("ReadStatementsDetail"),
	READ_TRANSACTIONS_BASIC("ReadTransactionsBasic"), READ_TRANSACTIONS_CREDITS("ReadTransactionsCredits"),
	READ_TRANSACTIONS_DEBITS("ReadTransactionsDebits"), READ_TRANSACTIONS_DETAILS("ReadTransactionsDetail");

	private String permission;

	AccountPermissions(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

}
