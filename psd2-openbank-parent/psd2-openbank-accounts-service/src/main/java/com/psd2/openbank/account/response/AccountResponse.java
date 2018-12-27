package com.psd2.openbank.account.response;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString(exclude = { "serialVersionUID" })
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
	private Date creationDateTime;

	/**
	 * Specifies the status of the account request resource.
	 */
	private AccountStatus[] status;

	/**
	 * Permissions
	 */
	private AccountPermissions[] permissions;

	/**
	 * Specified date and time the permissions will expire. If this is not
	 * populated, the permissions will be open ended. All dates in the JSON payloads
	 * are represented in ISO 8601 date-time format. All date-time fields in
	 * responses must include the timezone. An example is below:
	 * 2017-04-05T10:43:07+00:00
	 */
	private Date expirationDateTime;

	/**
	 * Specified start date and time for the transaction query period. If this is
	 * not populated, the start date will be open ended, and data will be returned
	 * from the earliest available transaction. All dates in the JSON payloads are
	 * represented in ISO 8601 date-time format. All date-time fields in responses
	 * must include the timezone. An example is below: 2017-04-05T10:43:07+00:00
	 */
	private Date transactionFromDateTime;

	/**
	 * Specified end date and time for the transaction query period. If this is not
	 * populated, the end date will be open ended, and data will be returned to the
	 * latest available transaction. All dates in the JSON payloads are represented
	 * in ISO 8601 date-time format. All date-time fields in responses must include
	 * the timezone. An example is below: 2017-04-05T10:43:07+00:00
	 */
	private Date transactionToDateTime;

}
