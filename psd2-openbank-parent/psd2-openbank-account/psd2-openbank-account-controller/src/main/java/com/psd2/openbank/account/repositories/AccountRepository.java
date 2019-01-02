package com.psd2.openbank.account.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.psd2.openbank.account.entity.AccountEntity;


@Repository("accountRepository")
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

}
