package com.xuxian.marketpro.net.httpclient;

import com.xuxian.marketpro.net.httpclient.interfaces.RequestHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by youarenotin on 16/8/25.
 */
public abstract class BasicRequestHandler implements RequestHandler {
    @Override
    public HttpURLConnection openConnection(String str) throws IOException {
        try {
            return (HttpURLConnection) new URL(str).openConnection();
        } catch (IOException e) {
            throw new IllegalArgumentException(str + " is not a valid URL", e);
        }
    }

    @Override
    public InputStream openInput(HttpURLConnection httpURLConnection) throws IOException {
        return httpURLConnection.getInputStream();
    }

    @Override
    public OutputStream openOutput(HttpURLConnection httpURLConnection) throws IOException {
       return httpURLConnection.getOutputStream();
    }

    @Override
    public void prepareConnection(HttpURLConnection httpURLConnection, HttpMethod httpMethod) throws IOException {
        httpURLConnection.setDoOutput(httpMethod.getMethodType().getDoOutput());
        httpURLConnection.setDoInput(httpMethod.getMethodType().getDoInput());
        httpURLConnection.setRequestMethod(httpMethod.getMethodType().getMethodName());
        if (httpMethod.params != null && httpMethod.params.hasMultiPart()){
            //params不为空 且 有文件 content-type改变
            httpURLConnection.setRequestProperty("Connection","keep-Alive");
            httpURLConnection.setRequestProperty("Cache-Control","no-cache");
            httpURLConnection.setRequestProperty("Content-type",AbsHttpClient.CONTENT_TYPE_MULTIPART);
        }
        else if (httpMethod.getContentType()!=null){
            httpURLConnection.setRequestProperty("Content-type",httpMethod.getContentType());
        }
        httpURLConnection.setRequestProperty("Accept-Charset",RequestHandler.UTF8);
    }

    @Override
    public HttpResponse readInputStream(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = openInput(httpURLConnection);
        byte[] result=null;
        if (inputStream!=null){
            byte[] buffer = new byte[1024*8];//一次读8kb的输入流数据

        }
        return null;
    }

    @Override
    public void writeHeaders(HttpURLConnection httpURLConnection, HttpMethod httpMethod) {

    }

    @Override
    public void writeStream(HttpURLConnection httpURLConnection, HttpMethod httpMethod) throws IOException {

    }
}
