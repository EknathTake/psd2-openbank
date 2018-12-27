package com.psd2.openbank.account;

import com.psd2.openbank.account.models.Account;
import com.psd2.openbank.account.models.Role;
import com.psd2.openbank.account.service.AccountService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.util.Arrays;

@SpringBootApplication
@EnableAsync
public class Psd2OpenBankApplication {

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(Psd2OpenBankApplication.class, args);
	}

	@Bean @Qualifier("mainDataSource")
	public DataSource dataSource(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.H2)
				.build();
		return db;
	}

	@Bean
	CommandLineRunner init(
			AccountService accountService
	) {
		return (evt) -> Arrays.asList(
				"user,admin,john,robert,ana".split(",")).forEach(
				username -> {
					Account acct = new Account();
					acct.setUsername(username);
					acct.setPassword("password");
					acct.setFirstName(username);
					acct.setLastName("LastName");
					acct.grantAuthority(Role.ROLE_USER);
					if (username.equals("admin"))
						acct.grantAuthority(Role.ROLE_ADMIN);
					accountService.registerUser(acct);
				}
		);
	}
}
