package com.psd2.openbank.account.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psd2.openbank.account.models.UserEntity;
import com.psd2.openbank.account.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService accountService;

	/**
	 * 
	 * @param account
	 * @return
	 */
	@PreAuthorize("hasRole('REGISTER')")
	@PostMapping("/api/account/register")
	public ResponseEntity<UserEntity> registerAccount(@RequestBody UserEntity account) {
		account = accountService.registerUser(account);
		return new ResponseEntity<>(account, HttpStatus.CREATED);
	}

	@PreAuthorize("isFullyAuthenticated()")
	@DeleteMapping("/api/account/remove")
	public ResponseEntity<GeneralController.RestMsg> removeAccount(Principal principal) {
		boolean isDeleted = accountService.removeAuthenticatedAccount();
		if (isDeleted) {
			return new ResponseEntity<>(
					new GeneralController.RestMsg(String.format("[%s] removed.", principal.getName())), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new GeneralController.RestMsg(
							String.format("An error ocurred while delete [%s]", principal.getName())),
					HttpStatus.BAD_REQUEST);
		}
	}

}
