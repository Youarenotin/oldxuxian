package com.xuxian.marketpro.presentation.entity;

/**
 * Created by youarenotin on 16/8/17.
 */
public class LoginEntity {
    private UserEntity data;
    private StatusEntity status;

    public StatusEntity getStatus() {
        return this.status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public UserEntity getData() {
        return this.data;
    }

    public void setData(UserEntity data) {
        this.data = data;
    }
}
