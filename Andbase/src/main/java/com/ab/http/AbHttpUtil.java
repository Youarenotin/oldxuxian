/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ab.http;

import android.content.Context;

import com.ab.util.AbLogUtil;
import com.ab.util.AbStrUtil;
import com.ab.util.JsonHelper;

// TODO: Auto-generated Javadoc
/**
 * © 2012 amsoft.cn
 * 名称：AbHttpUtil.java 
 * 描述：Http执行工具类，可处理get，post，以及异步处理文件的上传下载
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2013-10-22 下午4:15:52
 */
public class AbHttpUtil {
	
	/** 实例化单例对象. */
	private AbHttpClient mClient = null;
	
	/** 工具类单例. */
	private static AbHttpUtil mAbHttpUtil = null;
	
	
	/**
	 * 描述：获取实例.
	 *
	 * @param context the context
	 * @return single instance of AbHttpUtil
	 */
	public static AbHttpUtil getInstance(Context context){
	    if (null == mAbHttpUtil){
	    	mAbHttpUtil = new AbHttpUtil(context);
	    }
	    
	    return mAbHttpUtil;
	}
	
	/**
	 * 初始化AbHttpUtil.
	 *
	 * @param context the context
	 */
	private AbHttpUtil(Context context) {
		super();
		this.mClient = new AbHttpClient(context);
	}
	
	/**
	 * 描述：无线程的get请求.
	 *
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void getWithoutThread(String url, AbStringHttpResponseListener responseListener) {
		mClient.getWithoutThread(url,null,responseListener);
	}
	
	/**
	 * 描述：无线程的get请求.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void getWithoutThread(String url, AbRequestParams params, AbStringHttpResponseListener responseListener) {
		mClient.getWithoutThread(url,params,responseListener);
	}
	
	/**
	 * 描述：无线程的post请求.
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void postWithoutThread(String url,AbStringHttpResponseListener responseListener) {
		mClient.postWithoutThread(url,null,responseListener);
	}
	
	/**
	 * 描述：无线程的post请求.
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void postWithoutThread(String url,AbRequestParams params,AbStringHttpResponseListener responseListener) {
		mClient.postWithoutThread(url,params,responseListener);
	}
	
	/**
	 * 描述：无参数的get请求.
	 *
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void get(String url, AbHttpResponseListener responseListener) {
		mClient.get(url,null,responseListener);
	}
	
	/**
	 * 描述：带参数的get请求.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void get(String url, AbRequestParams params,
			AbHttpResponseListener responseListener) {
		mClient.get(url, params, responseListener);
	}
	
	/**
	 *  
	 * 描述：下载数据使用，会返回byte数据(下载文件或图片).
	 *
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void get(String url, AbBinaryHttpResponseListener responseListener) {
		mClient.get(url,null,responseListener);
	}
	
	/**
	 * 描述：文件下载的get.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void get(String url, AbRequestParams params,
			AbFileHttpResponseListener responseListener) {
		mClient.get(url, params, responseListener);
	}
	
	/**
	 * 描述：无参数的get请求.
	 *
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void getWithCache(String url, AbHttpResponseListener responseListener) {
		mClient.getWithCache(url,null,responseListener);
	}
	
	/**
	 * 描述：带参数的get请求.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void getWithCache(String url, AbRequestParams params,
			AbHttpResponseListener responseListener) {
		mClient.getWithCache(url, params, responseListener);
	}
	
	/**
	 *  
	 * 描述：下载数据使用，会返回byte数据(下载文件或图片).
	 *
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void getWithCache(String url, AbBinaryHttpResponseListener responseListener) {
		mClient.getWithCache(url,null,responseListener);
	}
	
	/**
	 * 描述：文件下载的get.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void getWithCache(String url, AbRequestParams params,
			AbFileHttpResponseListener responseListener) {
		mClient.getWithCache(url, params, responseListener);
	}
	
	/**
	 * 描述：无参数的post请求.
	 *
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void post(String url, AbHttpResponseListener responseListener) {
		mClient.post(url,null, responseListener);
	}
	
	/**
	 * 描述：带参数的post请求.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void post(String url, AbRequestParams params,
			AbHttpResponseListener responseListener) {
		mClient.post(url, params, responseListener);
	}
	
	/**
	 * 描述：文件下载的post.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void post(String url, AbRequestParams params,
			AbFileHttpResponseListener responseListener) {
		mClient.post(url, params, responseListener);
	}
	
	/**
	 * 描述：post Json.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void postJson(String url, AbJsonParams params,
			AbStringHttpResponseListener responseListener) {
		mClient.postJson(url, params, responseListener);
	}

	public<T> void postAndParse(String url , Class<T> clazz ,IHttpResponseCallBack<T> callBack){
		postAndParse(url,null,clazz,callBack);
	}

	public <T> void postAndParse(String url, AbRequestParams params, Class<T> clazz, IHttpResponseCallBack<T> callBack) {
    	mClient.post(url, params, new MyPostHttpResponseListener<T>(clazz,callBack));
	}

	public<T> void getAndParse(String url ,Class<T> clazz ,IHttpResponseCallBack callBack){
		getAndParse(url,null,clazz,callBack);
	}

	private <T> void getAndParse(String url, AbRequestParams params, Class<T> clazz, IHttpResponseCallBack callBack) {
		mClient.get(url,params,new MyGetHttpResponseListener<T>(clazz,callBack));
	}


	/**
	 * 描述：一般通用请求.
	 *
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void request(String url,AbStringHttpResponseListener responseListener) {
		request(url,null,responseListener);
	}
	
	/**
	 * 描述：一般通用请求.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void request(String url, AbRequestParams params,
			AbStringHttpResponseListener responseListener) {
		mClient.doRequest(url, params, responseListener);
	}
	
	/**
	 * 描述：设置连接超时时间(第一次请求前设置).
	 *
	 * @param timeout 毫秒
	 */
	public void setTimeout(int timeout) {
		mClient.setTimeout(timeout);
	}
	
    /**
     * 打开ssl 自签名(第一次请求前设置).
     * @param enabled
     */
    public void setEasySSLEnabled(boolean enabled){
    	mClient.setOpenEasySSL(enabled);
    }

    /**
	 * 设置编码(第一次请求前设置).
	 * @param encode
	 */
	public void setEncode(String encode) {
		mClient.setEncode(encode);
	}
	
	/**
     * 设置用户代理(第一次请求前设置).
     * @param userAgent
     */
	public void setUserAgent(String userAgent) {
		mClient.setUserAgent(userAgent);
	}
	
	/**
	 * 关闭HttpClient
	 * 当HttpClient实例不再需要是，确保关闭connection manager，以释放其系统资源  
	 */
	public void shutdownHttpClient(){
	    if(mClient != null){
	    	mClient.shutdown();
	    }
	}
	
	/**
	 * 
	 * 设置缓存的最大时间.
	 * @return
	 */
	public long getCacheMaxAge() {
		return mClient.getCacheMaxAge();
	}

	/**
	 * 
	 * 获取缓存的最大时间.
	 * @param cacheMaxAge
	 */
	public void setCacheMaxAge(long cacheMaxAge) {
		mClient.setCacheMaxAge(cacheMaxAge);
	}

	/**
	 *
	 * @param <T>
     */
	public class MyPostHttpResponseListener<T> extends AbStringHttpResponseListener{
		private Class<T> clazz;
		private IHttpResponseCallBack callBack;

		public MyPostHttpResponseListener(Class<T> clazz, IHttpResponseCallBack callBack) {
			this.clazz = clazz;
			this.callBack = callBack;
		}
		@Override
		public void onStart() {
			this.callBack.StartToParse();
		}

		@Override
		public void onFinish() {
			this.callBack.EndToParse();
		}

		@Override
		public void onFailure(int statusCode, String content, Throwable error) {
			if (!AbStrUtil.isEmpty(content)){
				AbLogUtil.e("json parse failed :" ,"statuscode = "+statusCode+"content = "+content);
			}
			this.callBack.FailedParseBean(content);
		}

		@Override
		public void onSuccess(int statusCode, String content) {
			if (!AbStrUtil.isEmpty(content)) {
				AbLogUtil.i("json parse success:", content);
			}
			this.callBack.SucceedParseBean(JsonHelper.parseObjectByJsonStr(content,clazz));
		}
	}

	public class MyGetHttpResponseListener<T> extends AbStringHttpResponseListener{
		private Class<T> clazz;
		private IHttpResponseCallBack callBack;

		public MyGetHttpResponseListener(Class<T> clazz, IHttpResponseCallBack callBack) {
			this.clazz = clazz;
			this.callBack = callBack;
		}

		@Override
		public void onSuccess(int statusCode, String content) {
			callBack.SucceedParseBean(JsonHelper.parseObjectByJsonStr(content,clazz));
		}

		@Override
		public void onStart() {
			callBack.StartToParse();
		}

		@Override
		public void onFinish() {
			callBack.EndToParse();
		}

		@Override
		public void onFailure(int statusCode, String content, Throwable error) {
			callBack.FailedParseBean(content);
		}
	}
}
