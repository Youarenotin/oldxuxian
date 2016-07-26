package com.xuxian.marketpro.net.httpclient;

import android.os.SystemClock;
import android.util.Log;

/**
 * Created by youarenotin on 16/7/26.
 */
public abstract class  AbsHttpClient {
    public static final String BOUNDARY = "------issmobile------";
    public static final String CONTENT_TYPE_MULTIPART = "multipart/form-data; boundary=------issmobile------";
    public static final String CONTENT_TYPE_URLENCODED = "application/x-www-form-urlencoded;charset=UTF-8";
    public static final String CRLF = "\r\n";
    public static final String LOGTAG = "IssHttpStat";
    private static boolean sEnableLog;
    protected int connectionTimeout;
    private int maxRetries;
    protected int readTimeout;

    public abstract HttpResponse delete(String str, ParameterList parameterList) throws HttpRequestException;

    public abstract HttpResponse get(String str, ParameterList parameterList) throws HttpRequestException;

    public abstract HttpResponse head(String str, ParameterList parameterList) throws HttpRequestException;

    public abstract HttpResponse post(String str, ParameterList parameterList) throws HttpRequestException;

    public abstract HttpResponse post(String str, String str2, byte[] bArr) throws HttpRequestException;

    public abstract HttpResponse put(String str, String str2, byte[] bArr) throws HttpRequestException;

    public AbsHttpClient() {
        this.maxRetries = 3;
        this.connectionTimeout = 2000;
        this.readTimeout = GetuiSdkHttpPost.CONNECTION_TIMEOUT_INT;
    }

    static {
        sEnableLog = true;
    }

    public HttpResponse tryMany(HttpMethod httpMethod) throws HttpRequestException {
        while (httpMethod.numTries < this.maxRetries) {
            try {
                long begin = SystemClock.elapsedRealtime();
                HttpResponse res = execute(httpMethod);
                long end = SystemClock.elapsedRealtime();
                if (sEnableLog) {
                    Log.i(LOGTAG, "request url=" + httpMethod.getRequestUrl() + ",respond time(ms)=" + (end - begin) + ",retry=" + httpMethod.numTries);
                }
                if (res != null) {
                    httpMethod.numTries++;
                    return res;
                }
            } catch (HttpRequestException e) {
                if (!e.isTimeOutException() || httpMethod.numTries >= this.maxRetries - 1) {
                    throw e;
                }
            } finally {
                httpMethod.numTries++;
            }
        }
        return null;
    }

    public void setMaxRetries(int maxRetries) {
        if (maxRetries < 1 || maxRetries > 18) {
            throw new IllegalArgumentException("Maximum retries must be between 1 and 18");
        }
        this.maxRetries = maxRetries;
    }

    protected HttpResponse execute(HttpMethod httpMethod) throws HttpRequestException {
        return execute(httpMethod, new BasicRequestHandler() {
        });
    }

    protected HttpResponse execute(HttpMethod httpMethod, RequestHandler requestHandler) throws HttpRequestException {
        HttpURLConnection uc = null;
        long startTime = System.currentTimeMillis();
        try {
            HttpResponse httpResponse;
            httpMethod.isConnected = false;
            uc = requestHandler.openConnection(httpMethod.getRequestUrl());
            uc.setConnectTimeout(this.connectionTimeout);
            uc.setReadTimeout(this.readTimeout);
            requestHandler.prepareConnection(uc, httpMethod);
            requestHandler.writeHeaders(uc, httpMethod);
            uc.connect();
            httpMethod.isConnected = true;
            if (uc.getDoOutput()) {
                requestHandler.writeStream(uc, httpMethod);
            }
            if (uc.getDoInput()) {
                httpResponse = requestHandler.readInputStream(uc);
            } else {
                httpResponse = new HttpResponse(uc, null);
            }
            if (uc != null) {
                uc.disconnect();
            }
            httpMethod.isConnected = false;
            return httpResponse;
        } catch (Exception e) {
            if (isTimeoutException(startTime, httpMethod.isConnected)) {
                throw new HttpRequestException(e, HttpRequestException.TIME_OUT_EXCEPTION);
            }
            throw new HttpRequestException(e, HttpRequestException.OTHER_EXCEPTION);
        } catch (Throwable th) {
            if (uc != null) {
                uc.disconnect();
            }
            httpMethod.isConnected = false;
        }
    }

    public ParameterList newParams() {
        return new ParameterList();
    }

    protected boolean isTimeoutException(long startTime, boolean isConnected) {
        long elapsedTime = (System.currentTimeMillis() - startTime) + 10;
        if (isConnected) {
            if (elapsedTime >= ((long) this.readTimeout)) {
                return true;
            }
            return false;
        } else if (elapsedTime < ((long) this.connectionTimeout)) {
            return false;
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
