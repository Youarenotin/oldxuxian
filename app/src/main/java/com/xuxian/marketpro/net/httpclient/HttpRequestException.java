package com.xuxian.marketpro.net.httpclient;

/**
 * Created by youarenotin on 16/7/26.
 */
public class HttpRequestException extends Exception {
    public static int OTHER_EXCEPTION = 2;
    public static int TIME_OUT_EXCEPTION = 1;
    private int exceptionCode;

    public HttpRequestException(Throwable throwable, int exceptionCode) {
        super(throwable);
        this.exceptionCode = exceptionCode;
    }
    public int getExceptionCode(){
        return this.exceptionCode;
    }
    public boolean  isTimeOutException(){
        return TIME_OUT_EXCEPTION==exceptionCode;
    }
}
