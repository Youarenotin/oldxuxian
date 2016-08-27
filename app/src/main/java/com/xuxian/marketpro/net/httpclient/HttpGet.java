package com.xuxian.marketpro.net.httpclient;

/**
 * Created by youarenotin on 16/8/27.
 */
public class HttpGet extends HttpMethod {
    public HttpGet(String baseUrl, ParameterList params) {
        super(baseUrl, params);
        this.methodType=MethodType.GET;
    }
}
