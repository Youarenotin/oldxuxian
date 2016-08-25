package com.xuxian.marketpro.net.httpclient;

/**
 * Created by youarenotin on 16/7/26.
 */
public class HttpMethod {
    public static final String MULTIPART = "multipart/form-data";
    public static final String URLENCODED = "application/x-www-form-urlencoded;charset=UTF-8";
    static int[] fib;
    private String baseUrl;
    protected byte[] content;
    protected String contentType;
    public boolean isConnected;
    protected MethodType methodType;
    int numTries;
    protected ParameterList params;

    public HttpMethod(String baseUrl, ParameterList params) {
        this.baseUrl = baseUrl;
        this.params = params;
    }

    public String getRequestUrl(){
        return null;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getContent() {
        return content;
    }

    public ParameterList getParams() {
        return params;
    }


    static {
        fib = new int[20];
        int i = 0;
        while (i < 20) {
            fib[i] = i < 2 ? i : fib[i - 2] + fib[i - 1];
            i++;
        }
    }

    protected int getNextTimeout() {
        return fib[this.numTries + 2] * 1000;
    }
}
