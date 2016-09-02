package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbScreenUtils;
import com.ab.util.AbViewUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.presentation.application.MyApplication;

/**
 * Created by youarenotin on 16/9/1.
 */
public class Splash extends Activity{
    private ImageView iv_app_start;
    private ShimmerFrameLayout shimmerFrameLayout;
    private Handler handler;
    public static final String START_ID = "START_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        MyApplication.addActivity(this);
        iv_app_start= (ImageView) findViewById(R.id.iv_app_start);
        shimmerFrameLayout= (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        int screenWidth = AbScreenUtils.getScreenWidth(Splash.this);
        int screenHight=AbScreenUtils.getScreenHeight(Splash.this);
        AbViewUtil.setViewWH(iv_app_start,screenWidth/2,screenHight/2);

        shimmerFrameLayout.useDefaults();
        shimmerFrameLayout.startShimmerAnimation();
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AbPreferenceUtils.loadPrefBoolean(Splash.this,START_ID,true)){
                    ActivityUtil.startCityListActivity(Splash.this);
                }else {
                    ActivityUtil.startTabMainActivity(Splash.this);
                }
                overridePendingTransition(R.anim.fade,R.anim.hold);
                finish();
            }
        },3000);

    }
}
