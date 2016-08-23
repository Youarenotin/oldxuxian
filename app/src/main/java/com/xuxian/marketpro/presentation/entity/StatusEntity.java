package com.xuxian.marketpro.presentation.entity;

/**
 * Created by youarenotin on 16/8/10.
 */
public class StatusEntity extends BaseEntity {
    private int code;
    private String errmsg;
    private String message;
    private int  requestType;

    public StatusEntity(int code, String errmsg, String message) {
        this.code = code;
        this.errmsg = errmsg;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }
}
