package com.psd2.openbank.account.controller.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString(exclude = { "serialVersionUID" })
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 630576943287478800L;

	/**
	 * Unique identification as assigned to identify the account request resource.
	 */
	@Min(value = 1, message = "Min size for accountRequestId should be 1.")
	@Max(value = 128, message = "Max size for accountRequestId should be 128.")
	private String accountRequestId;

	/**
	 * Date and time at which the resource was created. All dates in the JSON
	 * payloads are represented in ISO 8601 date-time format. All date-time fields
	 * in responses must include the timezone. An example is below:
	 * 2017-04-05T10:43:07+00:00
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creationDateTime;

	/**
	 * Specifies the status of the account request resource.
	 */
	private AccountStatus status;

	/**
	 * Permissions
	 */
	private Set<AccountPermissions> permissions;

	/**
	 * Specified date and time the permissions will expire. If this is not
	 * populated, the permissions will be open ended. All dates in the JSON payloads
	 * are represented in ISO 8601 date-time format. All date-time fields in
	 * responses must include the timezone. An example is below:
	 * 2017-04-05T10:43:07+00:00
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expirationDateTime;

	/**
	 * Specified start date and time for the transaction query period. If this is
	 * not populated, the start date will be open ended, and data will be returned
	 * from the earliest available transaction. All dates in the JSON payloads are
	 * represented in ISO 8601 date-time format. All date-time fields in responses
	 * must include the timezone. An example is below: 2017-04-05T10:43:07+00:00
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date transactionFromDateTime;

	/**
	 * Specified end date and time for the transaction query period. If this is not
	 * populated, the end date will be open ended, and data will be returned to the
	 * latest available transaction. All dates in the JSON payloads are represented
	 * in ISO 8601 date-time format. All date-time fields in responses must include
	 * the timezone. An example is below: 2017-04-05T10:43:07+00:00
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date transactionToDateTime;

	public String getAccountRequestId() {
		return accountRequestId;
	}

	public void setAccountRequestId(String accountRequestId) {
		this.accountRequestId = accountRequestId;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public Set<AccountPermissions> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<AccountPermissions> permissions) {
		this.permissions = permissions;
	}

	public Date getExpirationDateTime() {
		return expirationDateTime;
	}

	public void setExpirationDateTime(Date expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}

	public Date getTransactionFromDateTime() {
		return transactionFromDateTime;
	}

	public void setTransactionFromDateTime(Date transactionFromDateTime) {
		this.transactionFromDateTime = transactionFromDateTime;
	}

	public Date getTransactionToDateTime() {
		return transactionToDateTime;
	}

	public void setTransactionToDateTime(Date transactionToDateTime) {
		this.transactionToDateTime = transactionToDateTime;
	}

}
