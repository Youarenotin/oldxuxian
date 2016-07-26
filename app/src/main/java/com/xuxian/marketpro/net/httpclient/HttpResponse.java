package com.xuxian.marketpro.net.httpclient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by youarenotin on 16/7/26.
 */
public class HttpResponse {
    private byte[] body;
    private Map<String, List<String>> headers;
    private int status;
    private String url;

    public HttpResponse(HttpURLConnection urlConnection, byte[] body) {
        try {
            this.status = urlConnection.getResponseCode();
            this.url = urlConnection.getURL().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.headers = urlConnection.getHeaderFields();
        this.body = body;
    }

    public int getStatus() {
        return this.status;
    }

    public String getUrl() {
        return this.url;
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public byte[] getBody() {
        return this.body;
    }

    public String getBodyAsString() {
        if (this.body != null) {
            return new String(this.body);
        }
        return null;
    }
}
