package com.xuxian.marketpro.presentation.View.widght.custom;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xuxian.marketpro.R;

/**
 * Created by youarenotin on 16/8/27.
 */
public class PullToRefreshView extends LinearLayout implements View.OnTouchListener {
    public static final long ONE_DAY = 86400000;
    public static final long ONE_HOUR = 3600000;
    public static final long ONE_MINUTE = 60000;
    public static final long ONE_MONTH = 2592000000L;
    public static final long ONE_YEAR = 31104000000L;
    public static final int SCROLL_SPEED = -20;
    public static final int STATUS_PULL_TO_REFRESH = 0;
    public static final int STATUS_REFRESHING = 2;
    public static final int STATUS_REFRESH_FINISHED = 3;
    public static final int STATUS_RELEASE_TO_REFRESH = 1;
    private static final String UPDATED_AT = "updated_at";
    private boolean ableToPull;
    private int currentStatus;
    private View header;
    private MarginLayoutParams headerLayoutParams;
    private int hideHeaderHeight;
    private int lastStatus;
    private long lastUpdateTime;
//    private StickyGridHeadersGridView listView;
    private ImageView load;
    private boolean loadOnce;
    private int mId;
    private PullToRefreshListener mListener;
    private SharedPreferences preferences;
    private EatProgressBar progress;
    private int touchSlop;
    private float yDown;

    public PullToRefreshView(Context context) {
        super(context);
        this.currentStatus = STATUS_REFRESH_FINISHED;
        this.lastStatus = this.currentStatus;
        this.mId = -1;
    }

    public PullToRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.currentStatus = STATUS_REFRESH_FINISHED;
        this.lastStatus = this.currentStatus;
        this.mId = -1;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.header = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh, null, true);
        this.progress = (EatProgressBar) this.header.findViewById(R.id.progress);
        this.load = (ImageView) this.header.findViewById(R.id.load);
        ((AnimationDrawable) this.load.getDrawable()).start();
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setOrientation(STATUS_RELEASE_TO_REFRESH);
        addView(this.header, STATUS_PULL_TO_REFRESH);
    }

    public void setIsAbleToPull(MotionEvent e) {

    }

    public interface PullToRefreshListener {
        void onRefresh();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        setIsAbleToPull(event);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
