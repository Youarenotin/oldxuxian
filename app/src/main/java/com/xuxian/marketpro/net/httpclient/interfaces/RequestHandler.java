package com.xuxian.marketpro.net.httpclient.interfaces;

import com.xuxian.marketpro.net.httpclient.HttpMethod;
import com.xuxian.marketpro.net.httpclient.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Created by youarenotin on 16/8/25.
 */
public interface RequestHandler {
    public static final String UTF8 = "UTF-8";

    HttpURLConnection openConnection(String str) throws IOException;

    InputStream openInput(HttpURLConnection httpURLConnection) throws IOException;

    OutputStream openOutput(HttpURLConnection httpURLConnection) throws IOException;

    void prepareConnection(HttpURLConnection httpURLConnection, HttpMethod httpMethod) throws IOException;

    HttpResponse readInputStream(HttpURLConnection httpURLConnection) throws IOException;

    void writeHeaders(HttpURLConnection httpURLConnection, HttpMethod httpMethod);

    void writeStream(HttpURLConnection httpURLConnection, HttpMethod httpMethod) throws IOException;

}
