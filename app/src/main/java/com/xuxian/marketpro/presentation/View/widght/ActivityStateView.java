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
    private OnClickListener onClickListener;
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
        loadingText.setTextColor(Color.YELLOW);
        LayoutParams textParmas =new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParmas.leftMargin=20;
        loadingText.setText(loadingPromt.equals("")?"正在加载..":loadingPromt);
        loadingText.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        loadingText.setSingleLine(true);
        loadingText.setLayoutParams(textParmas);
        addView(loadingText);
    }

    private void addOtherView(int state, String msg) {

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
