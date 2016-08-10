package com.xuxian.marketpro.presentation.View.viewpage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ab.util.AbScreenUtils;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.presentation.application.MyApplication;

/**
 * Created by youarenotin on 16/8/9.
 */
public class BannerViewPager extends ViewPager {
    private static final int MSG_CHANGE_PHOTO = 1;
    private static final int PHOTO_CHANGE_TIME = 5000;
    private int FIRST_ITEM_INDEX;
    private int len;
    private int mCurrentPagePosition;
    private Handler mHandler;
    private boolean mIsChanged;
    private int playingDirection;
    private Runnable runnable;
    private ImageView[] tips;
    private int width;


    public BannerViewPager(Context context) {
        super(context);
        init(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init( context);
    }

    private void init(Context context) {
        this.mIsChanged = false;
        this.FIRST_ITEM_INDEX = MSG_CHANGE_PHOTO;
        this.mCurrentPagePosition = this.FIRST_ITEM_INDEX;
        this.playingDirection = 0;
        this.mHandler = new Handler() {
            public void dispatchMessage(Message msg) {
                switch (msg.what) {
                    case BannerViewPager.MSG_CHANGE_PHOTO /*1*/:
                        int count = BannerViewPager.this.len;
                        int i = BannerViewPager.this.getCurrentItem();
                        if (BannerViewPager.this.playingDirection == 0) {
                            if (i == count - 1) {
                                BannerViewPager.this.playingDirection = -1;
                                i = 0;
                            } else {
                                i += BannerViewPager.MSG_CHANGE_PHOTO;
                            }
                        } else if (i == 0) {
                            BannerViewPager.this.playingDirection = 0;
                            i += BannerViewPager.MSG_CHANGE_PHOTO;
                        } else {
                            i = 0;
                        }
                        BannerViewPager.this.setCurrentItem(i, true);
                        if (BannerViewPager.this.len > 0) {
                            BannerViewPager.this.mHandler.postDelayed(BannerViewPager.this.runnable, 10000);
                            break;
                        }
                        break;
                }
                super.dispatchMessage(msg);
            }
        };
        this.runnable = new Runnable() {
            public void run() {
                BannerViewPager.this.mHandler.sendEmptyMessage(BannerViewPager.MSG_CHANGE_PHOTO);
            }
        };
        this.width = AbScreenUtils.getScreenWidth(context);
    }

    public void startPlay() {
        if (this.mHandler != null) {
            this.mHandler.postDelayed(this.runnable, 10000);
        }
    }

    public void stopPlay() {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(this.runnable);
        }
    }


    public void addImage(int length, ViewGroup group) {
        this.len = length;
        this.tips = new ImageView[length];
        for (int i = 0; i < length; i += MSG_CHANGE_PHOTO) {
            ImageView imageView = new ImageView(MyApplication.getInstance());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 10;
            imageView.setLayoutParams(lp);
            this.tips[i] = imageView;
            if (i == 0) {
                this.tips[i].setImageResource(R.drawable.page_indicator_focused);
            } else {
                this.tips[i].setImageResource(R.drawable.page_indicator_unfocused);
            }
            group.addView(imageView);
        }
        init();
    }

    public void init() {
        setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int pState) {
                if (pState == 0 && BannerViewPager.this.mIsChanged) {
                    BannerViewPager.this.mIsChanged = false;
                    BannerViewPager.this.setCurrentItem(BannerViewPager.this.mCurrentPagePosition, false);
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageSelected(int pPosition) {
                BannerViewPager.this.mIsChanged = true;
                if (pPosition > BannerViewPager.this.len) {
                    BannerViewPager.this.mCurrentPagePosition = BannerViewPager.this.FIRST_ITEM_INDEX;
                } else if (pPosition < BannerViewPager.this.FIRST_ITEM_INDEX) {
                    BannerViewPager.this.mCurrentPagePosition = BannerViewPager.this.len;
                } else {
                    BannerViewPager.this.mCurrentPagePosition = pPosition;
                }
                BannerViewPager.this.setImageBackground(BannerViewPager.this.mCurrentPagePosition % BannerViewPager.this.len);
            }
        });
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (BannerViewPager.this.len == 0 || BannerViewPager.this.len == BannerViewPager.MSG_CHANGE_PHOTO) {
                    return true;
                }
                return false;
            }
        });
    }

    private void setImageBackground(int selectItemsIndex) {
        for (int i = 0; i < this.tips.length; i += MSG_CHANGE_PHOTO) {
            if (i == selectItemsIndex) {
                this.tips[i].setImageResource(R.drawable.page_indicator_focused);
            } else {
                this.tips[i].setImageResource(R.drawable.page_indicator_unfocused);
            }
        }
    }
}
