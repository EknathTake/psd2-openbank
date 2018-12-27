package com.psd2.openbank.account.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString(exclude = { "serialVersionUID" })
@Entity
@Table(name = "T_ACCOUNT")
public class AccountEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 630576943287478800L;

	/**
	 * Unique identification as assigned to identify the account request resource.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountRequestId;

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

	/*
	 * @ElementCollection(targetClass = AccountStatus.class)
	 * 
	 * @CollectionTable(name = "T_STATUS", joinColumns = @JoinColumn(name =
	 * "accountRequestId"))
	 */
	@Enumerated(EnumType.STRING)
	@OneToMany(mappedBy = "account")
	private Set<AccountStatus> status;

	/**
	 * Permissions
	 */
	@Enumerated(EnumType.STRING)
	@OneToMany(mappedBy = "account")
	private Set<AccountPermissions> permissions;

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
