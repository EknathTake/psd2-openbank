package com.psd2.openbank.account.models;

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
 * Specifies the status of the account request resource.
 * 
 * @author eknath.take
 *
 */
@Entity
@Table(name = "T_ACCOUNT_STATUS")
public enum AccountStatus {

	AUTHORISED("Authorised"), AWAITING_AUTHORISATION("AwaitingAuthorisation"), REJECTED("Rejected"), REVOKED("Revoked");

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATUS_ID")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNT_ID")
	private AccountEntity account;

	@Transient
	private String status;

	AccountStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
