package com.xuxian.marketpro.net.httpclient;

import android.os.SystemClock;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by youarenotin on 16/8/25.
 */
public abstract class AbsHttpClient {

    public static final String BOUNDARY = "------issmobile------";
    public static final String CONTENT_TYPE_MULTIPART = "multipart/form-data; boundary=------issmobile------";
    public static final String CONTENT_TYPE_URLENCODED = "application/x-www-form-urlencoded;charset=UTF-8";
    public static final String CRLF = "\r\n";
    public static final String LOGTAG = "IssHttpStat";
    private static boolean sEnableLog = true;
    protected int connectionTimeout;
    private int maxRetries;
    protected int readTimeout;


    public abstract HttpResponse delete(String url, ParameterList parameterList) throws HttpRequestException;

    public abstract HttpResponse get(String url, ParameterList parameterList) throws HttpRequestException;

    public abstract HttpResponse head(String url, ParameterList parameterList) throws HttpRequestException;

    public abstract HttpResponse post(String url, ParameterList parameterList) throws HttpRequestException;

    public abstract HttpResponse post(String url, String str2, byte[] bArr) throws HttpRequestException;

    public abstract HttpResponse put(String url, String str2, byte[] bArr) throws HttpRequestException;

    public AbsHttpClient() {
        this.maxRetries = 3;
        this.connectionTimeout = 2000;
        this.readTimeout = 8000;
    }

    /**
     * 基于httpurlconnection进行访问网络
     * @param httpMethod
     * @return
     * @throws HttpRequestException
     */
    public HttpResponse tryMany(HttpMethod httpMethod) throws HttpRequestException {
        while (httpMethod.numTries < this.maxRetries) {
            try {
                long begin = SystemClock.elapsedRealtime();
                HttpResponse response = execute(httpMethod);
                long end = SystemClock.elapsedRealtime();
                if (sEnableLog) {
                    Log.i(LOGTAG, "request url=" + httpMethod.getRequestUrl() + ",respond time(ms)=" + (end - begin) + ",retry=" + httpMethod.numTries);
                }
                if (response != null) {
                    httpMethod.numTries++;
                    return response;
                }
            }catch (HttpRequestException e){
                if (!e.isTimeOutException() || httpMethod.numTries >= this.maxRetries - 1) {
                    throw e;
                }
            }finally {
                httpMethod.numTries++;
            }
        }
        return null;
    }

    protected HttpResponse execute(HttpMethod httpMethod) throws HttpRequestException {
        return execute(httpMethod, new BasicRequestHandler(){});
    }

    private HttpResponse execute(HttpMethod httpMethod, BasicRequestHandler handler) throws HttpRequestException {
        HttpURLConnection httpURLConnection = null;
        long startTime = System.currentTimeMillis();
        try {
            HttpResponse response;
            httpMethod.isConnected=false;
            httpURLConnection = handler.openConnection(httpMethod.getRequestUrl());
            httpURLConnection.setConnectTimeout(this.connectionTimeout);
            httpURLConnection.setReadTimeout(this.readTimeout);
            handler.prepareConnection(httpURLConnection,httpMethod);
            handler.writeHeaders(httpURLConnection,httpMethod);
            httpURLConnection.connect();
            httpMethod.isConnected=true;
            if (httpURLConnection.getDoOutput()){
                handler.writeStream(httpURLConnection,httpMethod);
            }
            if (httpURLConnection.getDoInput()){
                response=handler.readInputStream(httpURLConnection);
            }else {
                response=new HttpResponse(httpURLConnection,null);
            }
            if (httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            httpMethod.isConnected=false;
            return response;
        } catch (Exception e) {
            if (isTimeoutException(startTime, httpMethod.isConnected)) {
                throw new HttpRequestException(e, HttpRequestException.TIME_OUT_EXCEPTION);
            }
            throw new HttpRequestException(e, HttpRequestException.OTHER_EXCEPTION);
        }catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            httpMethod.isConnected = false;
        }
        return null;
    }

    public void setMaxRetries(int maxRetries){
        if (maxRetries<1 || maxRetries>18){
            throw new IllegalArgumentException("Maximum retries must be between 1 and 18");
        }
        this.maxRetries=maxRetries;
    }

    public ParameterList newParams() {
        return new ParameterList();
    }

    protected boolean isTimeoutException(long startTime, boolean isConnected) {
        long elapsedTime = (System.currentTimeMillis() - startTime) + 10;
        if (isConnected) {
            if (elapsedTime >= ((long) this.readTimeout)) {//如果经过的时间大于超时时长
                return true;//返回超时
            }
            return false;
        } else if (elapsedTime < ((long) this.connectionTimeout)) {//如果经过的时间小于超时时长
            return false;//返回没超时
        } else {
            return true;
        }
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public static void setEnableLog(boolean enableLog) {
        sEnableLog = enableLog;
    }
}
