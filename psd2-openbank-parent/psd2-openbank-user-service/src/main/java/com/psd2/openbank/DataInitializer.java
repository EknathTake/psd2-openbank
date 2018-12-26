package com.psd2.openbank;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.psd2.openbank.domain.User;
import com.psd2.openbank.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

	/*@Autowired
	VehicleRepository vehicles;*/

	@Autowired
	UserRepository users;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		// log.debug("initializing vehicles data...");
		// Arrays.asList("moto", "car").forEach(v ->
		// this.vehicles.saveAndFlush(Vehicle.builder().name(v).build()));
		/*Vehicle v = new Vehicle();
		v.setName("moto");
		Vehicle v1 = new Vehicle();
		v1.setName("moto");

		this.vehicles.saveAndFlush(v);
		this.vehicles.saveAndFlush(v1);*/

		// log.debug("printing all vehicles...");
		// this.vehicles.findAll().forEach(v -> System.out.println(" Vehicle :" +
		// v.toString()));

		User u = new User();
		u.setUsername("user");
		u.setPassword(this.passwordEncoder.encode("password"));
		u.setRoles(Arrays.asList("ROLE_USER"));
		this.users.save(u);

		User u1 = new User();
		u1.setUsername("admin");
		u1.setPassword(this.passwordEncoder.encode("password"));
		u1.setRoles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
		this.users.save(u1);

		// log.debug("printing all users...");
		// this.users.findAll().forEach(v -> System.out.println(" User :" +
		// v.toString()));
	}
}
