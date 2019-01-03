package com.psd2.openbank.account.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Specifies the Open Banking account request types. This is a list of the data
 * clusters being consented by the PSU, and requested for authorisation with the
 * ASPSP.
 * 
 * @author eknath.take
 *
 */
@Entity
@Table(name = "T_ACCOUNT_PERMISSIONS")
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

	@Transient
	private String permission;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERMISSION_ID")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	private AccountEntity account;

	AccountPermissions(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

}
