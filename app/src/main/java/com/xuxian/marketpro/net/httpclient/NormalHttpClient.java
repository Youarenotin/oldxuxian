package com.xuxian.marketpro.net.httpclient;

/**
 * Created by youarenotin on 16/7/26.
 */
public class   NormalHttpClient extends AbsHttpClient {

    @Override
    public HttpResponse delete(String url, ParameterList parameterList) throws HttpRequestException {
        return null;
    }

    @Override
    public HttpResponse get(String url, ParameterList parameterList) throws HttpRequestException {
        return tryMany(new HttpGet(url,parameterList));
    }

    @Override
    public HttpResponse head(String url, ParameterList parameterList) throws HttpRequestException {
        return null;
    }

    @Override
    public HttpResponse post(String url, ParameterList parameterList) throws HttpRequestException {
        return tryMany(new HttpPost(url,parameterList));
    }

    @Override
    public HttpResponse post(String url, String contentType, byte[] bArr) throws HttpRequestException {
        return tryMany(new HttpPost(url,null,contentType,bArr));
    }

    @Override
    public HttpResponse put(String url, String str2, byte[] bArr) throws HttpRequestException {
        return null;
    }
}
