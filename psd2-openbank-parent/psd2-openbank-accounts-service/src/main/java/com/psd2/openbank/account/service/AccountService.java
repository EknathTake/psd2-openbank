package com.psd2.openbank.account.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psd2.openbank.account.models.Account;
import com.psd2.openbank.account.models.Role;
import com.psd2.openbank.account.repositories.AccountRepo;

@Service
public class AccountService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Optional<Account> account = accountRepo.findByUsername(s);
		if (account.isPresent()) {
			return account.get();
		} else {
			throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
		}
	}

	public Account findAccountByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = accountRepo.findByUsername(username);
		if (account.isPresent()) {
			return account.get();
		} else {
			throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
		}
	}

	public Account registerUser(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.grantAuthority(Role.ROLE_USER);
		return accountRepo.save(account);
	}

	@Transactional // To successfully remove the date @Transactional annotation must be added
	public boolean removeAuthenticatedAccount() throws UsernameNotFoundException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account acct = findAccountByUsername(username);
		int del = accountRepo.deleteAccountById(acct.getId());
		return del > 0;
	}
}
