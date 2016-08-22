package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.ab.http.AbHttpUtil;
import com.ab.http.IHttpResponseCallBack;
import com.ab.model.AbPoint;
import com.ab.util.AbPreferenceUtils;
import com.xuxian.marketpro.activity.store.StoreFragmentActivity;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.net.NewIssRequest;
import com.xuxian.marketpro.net.RequestParamsNet;
import com.xuxian.marketpro.presentation.entity.SharedContentEntity;

import static android.app.AlertDialog.*;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleBar();
        initFindViewById();
        setListener();
        init();
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTitleBar() {
        this.title = getIntent().getExtras().getString(INTENT_TITLE);
        this.url = getIntent().getStringExtra(INTENT_URL);
        this.share = getIntent().getBooleanExtra(INTENT_SHARE, false);
        if (this.share) {
            setTitleRightViewShow(true);
            setTitleRightText("分享");
        } else {
            setTitleRightViewShow(false);
            setTitleRightText("");
        }
        setTitle(title);
        getTitleRightClick().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Builder builder =   new Builder(getActivity());
                builder.setTitle("微信分享");
                builder.setItems(new String[]{"1"}, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        store_id= AbPreferenceUtils.loadPrefInt(getActivity(), StoreFragmentActivity.SITE_ID,0);
                        user_id=AbPreferenceUtils.loadPrefString(getActivity(),LoginActivity.USER_ID,"0");
                        AbHttpUtil.getInstance(getActivity()).postAndParse(
                                NewIssRequest.GET_SHARED_CONTENT,
                                RequestParamsNet.getInstance(getActivity()).getSharedTitleAndContent(url),
                                SharedContentEntity.class,
                                new shareContentIHttpResponseCallBack(which)
                        );
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    protected void initFindViewById() {

    }

    @Override
    protected void setListener() {

    }
}

class shareContentIHttpResponseCallBack implements IHttpResponseCallBack<SharedContentEntity> {
    private int which;

    public shareContentIHttpResponseCallBack(int which) {
        this.which=which;
    }

    @Override
    public void EndToParse() {

    }

    @Override
    public void FailedParseBean(String str) {

    }

    @Override
    public void StartToParse() {

    }

    @Override
    public void SucceedParseBean(SharedContentEntity sharedContentEntity) {

    }
}