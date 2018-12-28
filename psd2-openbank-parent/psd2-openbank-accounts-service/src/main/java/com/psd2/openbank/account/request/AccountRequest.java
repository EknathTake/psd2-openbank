package com.psd2.openbank.account.request;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * @author eknath.take
 *
 */
@Data
@ToString(exclude = { "serialVersionUID" })
public class AccountRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2266403128592211812L;

	/**
	 * Permissions
	 */

	private Set<String> permissions;

	/**
	 * Specified date and time the permissions will expire. If this is not
	 * populated, the permissions will be open ended. All dates in the JSON payloads
	 * are represented in ISO 8601 date-time format. All date-time fields in
	 * responses must include the timezone. An example is below:
	 * 2017-04-05T10:43:07+00:00
	 */
	@NotNull(message = "The expirationDateTime must not be null.")
	@Future(message = "The expirationDateTime must be in future in format yyyy-MM-dd HH:mm:ss.")
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

}
