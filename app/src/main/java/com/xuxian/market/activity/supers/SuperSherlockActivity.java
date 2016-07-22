package com.xuxian.market.activity.supers;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.xuxian.market.R;
import com.xuxian.market.presentation.application.MyApplication;

/**
 * 作者：lubo on 7/22 0022 10:18
 * 邮箱：lubo_wen@126.com
 */
public abstract class SuperSherlockActivity  extends SherlockActivity{
    public static final String INTENT_BUNDLE = "intent_bundle";
    public static final String INTENT_OBJECT = "intent_object";
    public AbLoadingDialog abLoadingDialog;
    public String[] items;
    private ImageView iv_title_bar_left_icon;
    private ImageView iv_title_bar_right_icon;
    private LinearLayout ll_title_bar_left_click;
    private LinearLayout ll_title_bar_right_click;
    public int loadingStatus;
    private RelativeLayout rl_title_bar;
    private View title_bar;
    private TextView tv_title_bar_center_title;
    private TextView tv_title_bar_left_text;
    private TextView tv_title_bar_right_text;


    protected abstract Activity getActivity();

    protected abstract void init();

    protected abstract void initTitleBar();

    protected abstract void initFindViewById();

    protected abstract void setListener();

    public SuperSherlockActivity() {
        this.loadingStatus=0;
        this.items = new String[]{"分享微信好友", "分享微信朋友圈"};

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
        titleBar();
        initLoadingDialog();
    }

    private void initLoadingDialog() {
    }

    protected  void titleBar(){
        this.title_bar = LayoutInflater.from(getActivity()).inflate(R.layout.title_bar_layout, null);
        this.rl_title_bar = (RelativeLayout) this.title_bar.findViewById(R.id.rl_title_bar);
        this.ll_title_bar_left_click = (LinearLayout) this.title_bar.findViewById(R.id.ll_title_bar_left_click);
        this.iv_title_bar_left_icon = (ImageView) this.title_bar.findViewById(R.id.iv_title_bar_left_icon);
        this.tv_title_bar_left_text = (TextView) this.title_bar.findViewById(R.id.tv_title_bar_left_text);
        this.tv_title_bar_center_title = (TextView) this.title_bar.findViewById(R.id.tv_title_bar_center_title);
        this.ll_title_bar_right_click = (LinearLayout) this.title_bar.findViewById(R.id.ll_title_bar_right_click);
        this.iv_title_bar_right_icon = (ImageView) this.title_bar.findViewById(R.id.iv_title_bar_right_icon);
        this.tv_title_bar_right_text = (TextView) this.title_bar.findViewById(R.id.tv_title_bar_right_text);
        this.ll_title_bar_left_click.setOnClickListener(new C08862());
        setCustomView(this.title_bar);

    }
}
