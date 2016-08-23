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

    public static class StatusEntity{
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        private int code;
        private String message;

    }
}
