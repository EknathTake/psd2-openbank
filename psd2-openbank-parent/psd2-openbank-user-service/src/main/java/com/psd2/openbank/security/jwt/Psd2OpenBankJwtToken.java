package com.psd2.openbank.security.jwt;

import java.util.Date;

public class Psd2OpenBankJwtToken {

	private String username;

	private String accessToken;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private Date validity;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JwtToken [username=");
		builder.append(username);
		builder.append(", accessToken=");
		builder.append(accessToken);
		builder.append(", validity=");
		builder.append(validity);
		builder.append("]");
		return builder.toString();
	}

}
