package com.psd2.openbank.account.response;

/**
 * Specifies the status of the account request resource.
 * 
 * @author eknath.take
 *
 */
public enum AccountStatus {

	AUTHORISED("Authorised"), AWAITING_AUTHORISATION("AwaitingAuthorisation"), REJECTED("Rejected"), REVOKED("Revoked");

	private String status;

	AccountStatus(String status) {
		this.status = status;
	}
}
