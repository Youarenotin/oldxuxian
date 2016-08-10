package com.xuxian.marketpro.presentation.entity;

/**
 * Created by youarenotin on 16/8/10.
 */
public class StatusEntity extends BaseEntity {
    private int code;
    private String errmsg;
    private String message;
    private int  requestType;

    public StatusEntity(String message, String errmsg, int code) {
        this.message = message;
        this.errmsg = errmsg;
        this.code = code;
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
