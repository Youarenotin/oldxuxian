package com.xuxian.marketpro.net.httpclient;

/**
 * Created by youarenotin on 16/8/27.
 */
public class HttpPost extends HttpMethod {
    public HttpPost(String baseUrl, ParameterList params) {
        super(baseUrl, params);
        this.methodType=MethodType.POST;
    }


}
