package com.xuxian.marketpro.activity.supers;

import android.app.ActionBar;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbViewUtil;
import com.actionbarsherlock.app.SherlockFragment;
import com.xuxian.marketpro.R;

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
        this.loadingStatus =View.VISIBLE;
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
        getSherlockActivity().getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSherlockActivity().getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSherlockActivity().getSupportActionBar().setCustomView(view);
    }

    public View getTitle_bar() {
        return this.title_bar;
    }

    public TextView getTv_title_bar_right_text() {
        return this.tv_title_bar_right_text;
    }

    public void setTitleBarBg(int color) {
        this.rl_title_bar.setBackgroundColor(Color.parseColor(String.valueOf(color)));
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
        this.tv_title_bar_left_text.setTextColor(Color.parseColor(String.valueOf(color)));
    }

    public void setTitle(String title) {
        this.tv_title_bar_center_title.setText(title);
    }

    public void setTitle(int title) {
        this.tv_title_bar_center_title.setText(String.valueOf(title));
    }

    public void setTitleColor(int color) {
        this.tv_title_bar_center_title.setTextColor(Color.parseColor(String.valueOf(color)));
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
        this.tv_title_bar_right_text.setTextColor(Color.parseColor(String.valueOf(color)));
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
