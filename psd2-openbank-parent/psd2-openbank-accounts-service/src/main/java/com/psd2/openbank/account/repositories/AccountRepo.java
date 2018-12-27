package com.psd2.openbank.account.repositories;


import org.springframework.data.repository.Repository;

import com.psd2.openbank.account.models.Account;

import java.util.Collection;
import java.util.Optional;

public interface AccountRepo extends Repository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Account save(Account account);
    int deleteAccountById(Long id);
}
