package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.xuxian.marketpro.R;

/**
 * Created by youarenotin on 16/9/1.
 */
public class Splash extends Activity{
    private ImageView iv_app_start;
    private  shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        iv_app_start= (ImageView) findViewById(R.id.iv_app_start);
        shimmerFrameLayout=findViewById(R.id.shimmer_view_container);
    }
}
