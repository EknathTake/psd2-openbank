package com.psd2.openbank.account;

import java.util.Arrays;

import javax.sql.DataSource;

/*import com.psd2.openbank.account.models.UserEntity;
import com.psd2.openbank.account.models.RoleEntity;
import com.psd2.openbank.account.service.UserService;*/

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

import com.psd2.openbank.account.entity.RoleEntity;
import com.psd2.openbank.account.entity.UserEntity;
import com.psd2.openbank.account.service.UserService;

@SpringBootApplication
@EnableAsync
public class Psd2OpenBankAccountApplication {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(Psd2OpenBankAccountApplication.class, args);
	}

	@Bean
	@Qualifier("mainDataSource")
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).build();
		return db;
	}

	@Bean
	CommandLineRunner init(UserService accountService) {
		return (evt) -> Arrays.asList("user,admin,john,robert,ana".split(",")).forEach(username -> {
			UserEntity acct = new UserEntity();
			acct.setUsername(username);
			acct.setPassword("password");
			acct.setFirstName(username);
			acct.setLastName("LastName");
			acct.grantAuthority(RoleEntity.ROLE_USER);
			if (username.equals("admin"))
				acct.grantAuthority(RoleEntity.ROLE_ADMIN);
			accountService.registerUser(acct);
		});
	}

}
