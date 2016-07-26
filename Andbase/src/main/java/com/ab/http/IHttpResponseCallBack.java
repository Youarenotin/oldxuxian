package com.ab.http;

/**
 * Created by youarenotin on 16/7/26.
 */
public interface IHttpResponseCallBack<T> {
    void EndToParse();

    void FailedParseBean(String str);

    void StartToParse();

    void SucceedParseBean(T t);
}
