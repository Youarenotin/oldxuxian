package com.xuxian.marketpro.presentation.View.widght.tablayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 作者：lubo on 6/16 0016 11:15
 * 邮箱：lubo_wen@126.com
 */
public class SlideTabStrip extends LinearLayout {
    private final Paint mSelectedIndicatorPaint;
    private int mSelectedPosition;
    private float mSelectedOffset;
    private float acceleration = 0.5f;

    public SlideTabStrip(Context context) {
        this(context, null);
    }

    public SlideTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideTabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        mSelectedIndicatorPaint = new Paint();
    }


    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mSelectedPosition = position;
        mSelectedOffset = positionOffset;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int childCount = getChildCount();
        mSelectedIndicatorPaint.setColor(Color.WHITE);
        mSelectedIndicatorPaint.setAntiAlias(true);
        if (childCount > 0) {
            View view = getChildAt(mSelectedPosition);
            int left = view.getLeft();
            int right = view.getRight();
            View nextView = getChildAt(mSelectedPosition + 1);
            if (nextView == null) {
                return;
            }
            float footX = 0f;
            if (mSelectedOffset > 0.2 && mSelectedOffset < 0.5) {
                float temp = (float) ((mSelectedOffset - 0.2) / (0.3));
                footX = (float) ((Math.atan(temp * acceleration * 2 - acceleration) + (Math.atan(acceleration))) / (2 * (Math.atan(acceleration))));
                left = view.getLeft();
                right = (int) (right + (footX) * nextView.getWidth());
                RectF rectF = getRectF(left, (int) (height - ((int) 2 * getResources().getDisplayMetrics().density)), right, height);
                canvas.drawRoundRect(rectF, 3, 3, mSelectedIndicatorPaint);
            }
            if (mSelectedOffset > 0.5 && mSelectedOffset < 0.8) {
                float temp = (float) ((mSelectedOffset - 0.5) / (0.3));
                footX = (float) ((Math.atan(temp * acceleration * 2 - acceleration) + (Math.atan(acceleration))) / (2 * (Math.atan(acceleration))));
                left = (int) (left + (footX) * view.getWidth());
                right = (int) (right + nextView.getWidth());
                RectF rectF = getRectF(left, (int) (height - ((int) 2 * getResources().getDisplayMetrics().density)), right, height);
                canvas.drawRoundRect(rectF, 3, 3, mSelectedIndicatorPaint);

            }
            if (mSelectedOffset > 0.8) {
                float temp = (float) ((0.8 - mSelectedOffset) / (0.2));
                footX = (float) ((Math.atan(temp * acceleration * 2 - acceleration) + (Math.atan(acceleration))) / (2 * (Math.atan(acceleration))));
                left = view.getLeft();
                right = view.getRight();
                right = right + nextView.getWidth();
                left = left + view.getWidth();
                RectF rectf = getRectF(left,
                        (int) (height - (((int) 2 * getResources().getDisplayMetrics().density) + (1 - footX) * getResources().getDisplayMetrics().density)),
                        right,
                        height
                );
                canvas.drawRoundRect(rectf, 3, 3, mSelectedIndicatorPaint);
//                canvas.drawRoundRect(
//                        left,
//                        height - (((int) 2 * getResources().getDisplayMetrics().density)+(1-footX) * getResources().getDisplayMetrics().density)
//                        //+ footX + 1 * getResources().getDisplayMetrics().density),
//                        , right,
//                        height
//                        , 3
//                        , 3
//                        ,mSelectedIndicatorPaint);
            }
            if (mSelectedOffset < 0.2) {
                float temp = (float) ((mSelectedOffset - 0.2) / (0.2));
                footX = (float) ((Math.atan(temp * acceleration * 2 - acceleration) + (Math.atan(acceleration))) / (2 * (Math.atan(acceleration))));
                left = view.getLeft();
                right = view.getRight();
                RectF rectF = getRectF(left,
                        (int) (height - ((int) 2 * getResources().getDisplayMetrics().density + (1 - footX) * getResources().getDisplayMetrics().density)),
                        right,
                        height
                );
                canvas.drawRoundRect(rectF, 3, 3, mSelectedIndicatorPaint);
            }
            Log.d("offset", " " + footX);
//            canvas.drawRect(left,height-((int)2*getResources().getDisplayMetrics().density),right,height,mSelectedIndicatorPaint);
        }

    }

    private RectF getRectF(int l, int t, int r, int b) {
        return new RectF(l, t, r, b);
    }

}
