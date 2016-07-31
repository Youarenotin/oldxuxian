package com.xuxian.marketpro.presentation.View.widght;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuxian.marketpro.R;

/**
 * Created by youarenotin on 16/7/25.
 */
public class ActivityStateView extends LinearLayout {
    public static final int ACTIVITY_STATE_LOADING = 1;

    public static final int ACTIVITY_STATE_NETWORK_ERROR = 2;
    public static final int ACTIVITY_STATE_NODATA = 3;
    private AnimationDrawable animationDrawable;
    private int currentState;
    private OnClickListener listener;
    private String loadingPromt = "";
    private String netErrorText = "";
    private String nodataText = "";

    public ActivityStateView(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
    }

    public ActivityStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER);
    }

    public void setClickListener(OnClickListener listener) {
        this.listener = listener;
        if (this.currentState == ACTIVITY_STATE_NETWORK_ERROR) {
            setClickable(true);
            setOnClickListener(this.listener);
        }
    }

    public void setState(int state) {
        this.currentState = state;
        removeAllViews();
        if (state == ACTIVITY_STATE_LOADING) {
            setClickable(false);
            addLoadingView();
            animationDrawable.start();
        } else if (state == ACTIVITY_STATE_NETWORK_ERROR) {
            setClickable(false);
            addOtherView(state);
        } else if (state == ACTIVITY_STATE_NODATA) {
            setClickable(true);
            addOtherView(state);
        }
    }


    public void setState(int state, String msg) {
        this.currentState = state;
        removeAllViews();
        if (state == ACTIVITY_STATE_LOADING) {
            setClickable(false);
            addLoadingView();
            animationDrawable.start();
        } else if (state == ACTIVITY_STATE_NETWORK_ERROR) {
            setClickable(false);
            addOtherView(state, msg);
        } else if (state == ACTIVITY_STATE_NODATA) {
            setClickable(true);
            addOtherView(state);
        }
    }

    private void addOtherView(int state) {
        setOrientation(HORIZONTAL);
        TextView loadingText;
        LayoutParams textParams;
        setOrientation(VERTICAL);
        ImageView loadingImage = new ImageView(getContext());
        LayoutParams imgParams = new LayoutParams(-2, -2);
        if (state == ACTIVITY_STATE_NODATA) {
            loadingImage.setImageResource(R.drawable.mzw_neterror_iamge);
            loadingImage.setLayoutParams(imgParams);
            addView(loadingImage);
            loadingText = new TextView(getContext());
            textParams = new LayoutParams(-2, -2);
            textParams.topMargin = 10;
        } else {
            loadingImage.setImageResource(R.drawable.mzw_neterror_iamge);
            loadingImage.setLayoutParams(imgParams);
            addView(loadingImage);
            loadingText = new TextView(getContext());
            textParams = new LayoutParams(-2, -2);
            textParams.topMargin = 10;
        }
        String text = state == ACTIVITY_STATE_NODATA ? this.nodataText.equals("") ? "即将开店" : this.nodataText : this.netErrorText.equals("") ? "网络异常\n点击屏幕重试" : this.nodataText;
        loadingText.setText(text);
        loadingText.setTextColor(Color.parseColor("#ff6600"));
        loadingText.setTextSize(18.0f);
        loadingText.setGravity(ACTIVITY_STATE_LOADING);
        loadingText.setLayoutParams(textParams);
        addView(loadingText);
    }


    private void addLoadingView() {
        setOrientation(HORIZONTAL);
        ImageView loadingImage = new ImageView(getContext());
        LayoutParams params =new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingImage.setImageResource(R.drawable.activity_state_view_loading_anim);
        animationDrawable= (AnimationDrawable) loadingImage.getDrawable();
        loadingImage.setLayoutParams(params);
        addView(loadingImage);
        TextView loadingText =new TextView(getContext());
        loadingText.setTextColor(Color.BLUE);
        LayoutParams textParmas =new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParmas.leftMargin=20;
        loadingText.setText(loadingPromt.equals("")?"正在加载..":loadingPromt);
        loadingText.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        loadingText.setSingleLine(true);
        loadingText.setLayoutParams(textParmas);
        addView(loadingText);
    }

    private void addOtherView(int state, String msg) {
        setOrientation(VERTICAL);
        ImageView loadingImage = new ImageView(getContext());
        LayoutParams imgParams = new LayoutParams(-2, -2);
        loadingImage.setImageResource(state == ACTIVITY_STATE_NODATA ? R.drawable.mzw_nodata_image : R.drawable.mzw_neterror_iamge);
        loadingImage.setLayoutParams(imgParams);
        loadingImage.setVisibility(GONE);
        addView(loadingImage);
        TextView loadingText = new TextView(getContext());
        LayoutParams textParams = new LayoutParams(-2, -2);
        textParams.topMargin = 10;
        String text = state == ACTIVITY_STATE_NODATA ? this.nodataText.equals("") ? "即将开店" : this.nodataText : this.netErrorText.equals("") ? msg : this.nodataText;
        loadingText.setText(text);
        loadingText.setTextColor(Color.parseColor("#ff6600"));
        loadingText.setTextSize(18.0f);
        loadingText.setGravity(Gravity.CENTER_HORIZONTAL);
        loadingText.setLayoutParams(textParams);
        addView(loadingText);

    }
    public void setNetErrorText(String netErrorText) {
        this.netErrorText = netErrorText;
    }

    public void setLoadingPromt(String loadingPromt) {
        this.loadingPromt = loadingPromt;
    }

    public void setNodataText(String nodataText) {
        this.nodataText = nodataText;
    }

}
