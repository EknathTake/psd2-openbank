package com.psd2.openbank.web;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psd2.openbank.domain.User;
import com.psd2.openbank.repository.UserRepository;
import com.psd2.openbank.security.jwt.Psd2OpenBankJwtToken;
import com.psd2.openbank.security.jwt.Psd2OpenBankJwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	Psd2OpenBankJwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository users;

	@PostMapping("/signin")
	public ResponseEntity signin(@RequestBody AuthenticationRequest data) {

		try {
			String username = data.getUsername();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			Optional<User> findByUsername = this.users.findByUsername(username);
			Psd2OpenBankJwtToken token = jwtTokenProvider.createToken(username, findByUsername
					.orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());

			/*
			 * Map<Object, Object> model = new HashMap<>(); model.put("username", username);
			 * model.put("token", token);
			 */
			return ok(token);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}
	}
}
