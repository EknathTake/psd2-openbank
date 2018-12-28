package com.psd2.openbank.account.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psd2.openbank.account.models.RoleEntity;
import com.psd2.openbank.account.models.UserEntity;
import com.psd2.openbank.account.repositories.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo accountRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Optional<UserEntity> account = accountRepo.findByUsername(s);
		if (account.isPresent()) {
			return account.get();
		} else {
			throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
		}
	}

	public UserEntity findAccountByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> account = accountRepo.findByUsername(username);
		if (account.isPresent()) {
			return account.get();
		} else {
			throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
		}
	}

	public UserEntity registerUser(UserEntity account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.grantAuthority(RoleEntity.ROLE_USER);
		return accountRepo.save(account);
	}

	@Transactional // To successfully remove the date @Transactional annotation must be added
	public boolean removeAuthenticatedAccount() throws UsernameNotFoundException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity acct = findAccountByUsername(username);
		int del = accountRepo.deleteAccountById(acct.getId());
		return del > 0;
	}
}
