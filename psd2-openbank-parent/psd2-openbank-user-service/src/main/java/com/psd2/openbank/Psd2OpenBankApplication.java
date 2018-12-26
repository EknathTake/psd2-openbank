package com.psd2.openbank;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.psd2.openbank.domain.User;

@SpringBootApplication
public class Psd2OpenBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(Psd2OpenBankApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}

@Configuration
@EnableJpaAuditing
class DataJpaConfig {

	@Bean
	public AuditorAware<User> auditor() {
		return () -> {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (authentication == null || !authentication.isAuthenticated()) {
				return Optional.empty();
			}

			return Optional.of((User) authentication.getPrincipal());
		};
	}
}
