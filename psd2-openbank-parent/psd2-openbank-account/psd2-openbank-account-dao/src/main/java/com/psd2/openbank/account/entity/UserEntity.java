package com.psd2.openbank.account.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class UserEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(unique = true)
	private String username;
	@JsonIgnore
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private List<RoleEntity> roles;
	private boolean accountNonExpired, accountNonLocked, credentialsNonExpired, enabled;

	public UserEntity() {
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void grantAuthority(RoleEntity authority) {
		if (roles == null)
			roles = new ArrayList<>();
		roles.add(authority);
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
		return authorities;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		String toStr = String.format("Account[%s]", id);
		toStr += String.format("\n username[%s]", username);
		toStr += String.format("\n | firstName[%s]", firstName);
		toStr += String.format("\n | lastName[%s]", lastName);
		toStr += String.format("\n | email[%s]", email);
		return toStr;
	}
}
