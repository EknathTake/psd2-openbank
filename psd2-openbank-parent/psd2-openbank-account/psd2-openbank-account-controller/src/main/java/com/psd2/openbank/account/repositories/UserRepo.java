package com.psd2.openbank.account.repositories;


import org.springframework.data.repository.Repository;

import com.psd2.openbank.account.entity.UserEntity;

import java.util.Collection;
import java.util.Optional;

public interface UserRepo extends Repository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    UserEntity save(UserEntity account);
    int deleteAccountById(Long id);
}
