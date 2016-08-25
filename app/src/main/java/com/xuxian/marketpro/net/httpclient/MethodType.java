package com.xuxian.marketpro.net.httpclient;

/**
 * Created by youarenotin on 16/8/25.
 */
public enum MethodType {
    GET(true, false),
    POST(true, true),
    PUT(true, true),
    DELETE(true, false),
    HEAD(false, false);

    private boolean doInput;
    private boolean doOutput;

    private MethodType(boolean doInput, boolean doOutput) {
        this.doInput = doInput;
        this.doOutput = doOutput;
    }

    public boolean getDoInput() {
        return this.doInput;
    }

    public boolean getDoOutput() {
        return this.doOutput;
    }

    public String getMethodName() {
        return toString();
    }
}
