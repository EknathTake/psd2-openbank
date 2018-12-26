package com.psd2.openbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psd2.openbank.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
