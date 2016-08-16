package com.xuxian.marketpro.presentation.View.widght.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;

import com.xuxian.marketpro.R;

/**
 * Created by youarenotin on 16/8/16.
 */
public class BasePopWindow extends PopupWindow {
    protected Context mContext;
    protected View mLayout;

    public BasePopWindow(View view, Context context, int width, int height, boolean focusable) {
        super(context);
        this.mContext = context;
        setWidth(width);
        setHeight(height);
        setFocusable(focusable);
        this.mLayout = view;
        setContentView(this.mLayout);
        setAnimationStyle(R.style.popAnim);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        setInputMethodMode(1);
        setSoftInputMode(16);
        setSoftInputMode(16);
    }
}
