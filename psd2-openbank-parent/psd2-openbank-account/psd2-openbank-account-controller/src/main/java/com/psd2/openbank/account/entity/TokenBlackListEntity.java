package com.psd2.openbank.account.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TokenBlackListEntity {

    @Id
    private String jti;
    private Long userId;
    private Long expires;
    private Boolean isBlackListed;

    public TokenBlackListEntity() {
    }

    public TokenBlackListEntity(Long userId, String jti, Long expires) {
        this.jti = jti;
        this.userId = userId;
        this.expires = expires;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public boolean isBlackListed() {
        return isBlackListed;
    }

    public void setBlackListed(boolean blackListed) {
        isBlackListed = blackListed;
    }
}
