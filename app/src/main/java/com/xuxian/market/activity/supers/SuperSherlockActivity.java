package com.xuxian.market.activity.supers;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbViewUtil;
import com.ab.view.AbLoadingDialog;
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
        this.ll_title_bar_left_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        setCustomView(this.title_bar);
    }

    public void setCustomView(View title_bar) {
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayOptions(16);
        getSupportActionBar().setCustomView(title_bar);
    }

    public View getTitle_bar() {
        return this.title_bar;
    }

    public TextView getTv_title_bar_right_text() {
        return this.tv_title_bar_right_text;
    }

    public void setTitleBarBg(int color) {
        this.rl_title_bar.setBackgroundColor(Color.parseColor(getString(color)));
    }

    public void setTitleLeftViewBg(int resid) {
        this.ll_title_bar_left_click.setBackgroundResource(resid);
    }

    public void setTitleLeftIcon(int resid) {
        this.iv_title_bar_left_icon.setBackgroundResource(resid);
    }

    public void setTitleLeftIcon(int resid, int width, int height) {
        this.iv_title_bar_left_icon.setBackgroundResource(resid);
        AbViewUtil.setViewWH(this.iv_title_bar_left_icon, width, height);
    }

    public void setTitleLeftText(String text) {
        this.tv_title_bar_left_text.setText(text);
    }

    public void setTitleLeftTextColor(int color) {
        this.tv_title_bar_left_text.setTextColor(Color.parseColor(getString(color)));
    }

    public void setTitle(String title) {
        this.tv_title_bar_center_title.setText(title);
    }

    public void setTitle(int title) {
        this.tv_title_bar_center_title.setText(getString(title));
    }

    public void setTitleColor(int color) {
        this.tv_title_bar_center_title.setTextColor(Color.parseColor(getString(color)));
    }

    public void setTitleRightViewBg(int resid) {
        this.ll_title_bar_right_click.setBackgroundResource(resid);
    }

    public void setTitleRightIcon(int resid) {
        this.iv_title_bar_right_icon.setBackgroundResource(resid);
    }

    public void setTitleRightIcon(int resid, int width, int height) {
        this.iv_title_bar_right_icon.setBackgroundResource(resid);
        AbViewUtil.setViewWH(this.iv_title_bar_left_icon, width, height);
    }

    public void setTitleRightText(String text) {
        this.tv_title_bar_right_text.setText(text);
    }

    public void setTitleRightTextColor(int color) {
        this.tv_title_bar_right_text.setTextColor(Color.parseColor(getString(color)));
    }

    public void setTitleLeftViewShow(boolean boo) {
        if (boo) {
            this.ll_title_bar_left_click.setVisibility(View.VISIBLE);
        } else {
            this.ll_title_bar_left_click.setVisibility(View.INVISIBLE);
        }
    }

    public void setTitleLeftTextShow(boolean boo) {
        if (boo) {
            this.tv_title_bar_left_text.setVisibility(View.VISIBLE);
        } else {
            this.tv_title_bar_left_text.setVisibility(View.INVISIBLE);
        }
    }

    public void setTitleRightViewShow(boolean boo) {
        if (boo) {
            this.ll_title_bar_right_click.setVisibility(View.VISIBLE);
        } else {
            this.ll_title_bar_right_click.setVisibility(View.INVISIBLE);
        }
    }

    public void setTitleRightIconShow(boolean boo) {
        if (boo) {
            this.iv_title_bar_right_icon.setVisibility(View.VISIBLE);
        } else {
            this.iv_title_bar_right_icon.setVisibility(View.INVISIBLE);
        }
    }

    public void setTitleRightTextShow(boolean boo) {
        if (boo) {
            this.tv_title_bar_right_text.setVisibility(View.VISIBLE);
        } else {
            this.tv_title_bar_right_text.setVisibility(View.INVISIBLE);
        }
    }

    public LinearLayout getTitleLeftClick() {
        return this.ll_title_bar_left_click;
    }

    public LinearLayout getTitleRightClick() {
        return this.ll_title_bar_right_click;
    }


}
