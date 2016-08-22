package com.xuxian.marketpro.activity;

import android.app.Activity;

import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;

/**
 * Created by youarenotin on 16/8/22.
 */
public class MessageWebViewActivity extends SuperSherlockActivity{
    public static final String INTENT_SHARE = "share";
    public static final String INTENT_TITLE = "title";
    public static final String INTENT_URL = "url";
    private boolean share;
    private int store_id;
    private String title;
    private String url;
    private String user_id;
//    private ProgressWebView webView;

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initFindViewById() {

    }

    @Override
    protected void setListener() {

    }
}
