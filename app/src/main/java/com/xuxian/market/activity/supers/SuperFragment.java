package com.xuxian.market.activity.supers;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbViewUtil;
import com.actionbarsherlock.app.SherlockFragment;
import com.xuxian.market.R;

/**
 * 作者：lubo on 7/21 0021 14:29
 * 邮箱：lubo_wen@126.com
 */
public abstract class SuperFragment extends SherlockFragment {
    private ImageView iv_title_bar_left_icon;
    private ImageView iv_title_bar_right_icon;
    private LinearLayout ll_title_bar_left_click;
    private LinearLayout ll_title_bar_right_click;
    public int loadingStatus;
    private RelativeLayout rl_title_bar;
    private TextView tv_title_bar_center_title;
    private TextView tv_title_bar_left_text;
    private TextView tv_title_bar_right_text;
    private View title_bar;

    public SuperFragment() {
        this.loadingStatus =0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void  init();
    protected abstract void  initTitleBar();
    protected abstract void initFindViewById(View view);
    protected abstract void setListener();

    /**
     * 初始化titleBar
     */
    public void titleBar(){
        title_bar = LayoutInflater.from(getActivity()).inflate(R.layout.title_bar_layout, null);
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

    }

    public int getLoadingStatus() {
        return this.loadingStatus;
    }

    public void setLoadingStatus(int loadingStatus) {
        this.loadingStatus = loadingStatus;
    }

    public void setCustomView(View view){
        getSherlockActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getSherlockActivity().getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSherlockActivity().getActionBar().setCustomView(view);
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



}
