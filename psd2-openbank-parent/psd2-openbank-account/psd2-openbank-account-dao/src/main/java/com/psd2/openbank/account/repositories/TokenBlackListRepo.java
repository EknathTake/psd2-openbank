package com.psd2.openbank.account.repositories;

import org.springframework.data.repository.Repository;

import com.psd2.openbank.account.entity.TokenBlackListEntity;

import java.util.List;
import java.util.Optional;

public interface TokenBlackListRepo  extends Repository<TokenBlackListEntity, Long> {
   Optional<TokenBlackListEntity> findByJti(String jti);
   List<TokenBlackListEntity> queryAllByUserIdAndIsBlackListedTrue(Long userId);
   void save(TokenBlackListEntity tokenBlackList);
   List<TokenBlackListEntity> deleteAllByUserIdAndExpiresBefore(Long userId, Long date);
}
