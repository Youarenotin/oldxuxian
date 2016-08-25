package com.xuxian.marketpro.net.httpclient;

import com.xuxian.marketpro.activity.tab.forums.activity.ForumListActivity;
import com.xuxian.marketpro.net.httpclient.interfaces.RequestHandler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (inputStream!=null){
            try {
                byte[] buffer = new byte[1024*8];//一次读8kb的输入流数据
                while (true){
                    int count = inputStream.read(buffer);
                    if (count==-1)
                        break;
                    baos.write(buffer,0,count);
                }
                baos.flush();
                result=baos.toByteArray();
            } finally {
                baos.close();
                inputStream.close();

            }
        }
        return new HttpResponse(httpURLConnection,result);
    }

    @Override
    public void writeHeaders(HttpURLConnection httpURLConnection, HttpMethod httpMethod) {
        ParameterList params = httpMethod.getParams();
        if (params!=null){
            Iterator<ParameterList.Parameter> iterator = params.iterator();
            while (iterator.hasNext()){
                ParameterList.HeaderParameter param = (ParameterList.HeaderParameter) iterator.next();
                httpURLConnection.setRequestProperty(param.name,param.value);
            }
        }
    }

    @Override
    public void writeStream(HttpURLConnection httpURLConnection, HttpMethod httpMethod) throws IOException {
        OutputStream outputStream=null;
        InputStream inputStream=null;
        ParameterList.InputStreamParameter inputStreamParameter;
        try {
            outputStream=openOutput(httpURLConnection);
            ParameterList params = httpMethod.getParams();
            if (params!=null){
                if (params.hasMultiPart()){
                    DataOutputStream dos =new DataOutputStream(outputStream);
                    Iterator iterator=params.iterator();
                    while (iterator.hasNext()){
                        byte[] buffer;
                        int count ;
                        ParameterList.Parameter param = (ParameterList.Parameter) iterator.next();
                        if (param instanceof ParameterList.StringParameter){

                        }
                        if (param instanceof ParameterList.FileParameter){

                        }
                        if (param instanceof ParameterList.InputStreamParameter){

                        }
                        dos.writeBytes("--------issmobile--------\r\n");
                        outputStream.flush();
                    }
                }
            }
        }finally {

        }
    }
}
