package com.xuxian.marketpro.net.httpclient;

/**
 * Created by youarenotin on 16/8/27.
 */
public class HttpPost extends HttpMethod {
    public HttpPost(String baseUrl, ParameterList params) {
        super(baseUrl, params);
        this.methodType=MethodType.POST;
        this.contentType=HttpMethod.URLENCODED;
        if (params!=null)
        {
            this.content=params.urlEncodedBytes();
        }
    }

    public HttpPost(String path, ParameterList params, String contentType, byte[] data) {
        super(path, params);
        this.methodType = MethodType.POST;
        this.contentType = contentType;
        this.content = data;
    }
}
