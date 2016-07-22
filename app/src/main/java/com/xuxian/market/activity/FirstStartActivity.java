package com.xuxian.market.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.ab.util.AbScreenUtils;
import com.xuxian.market.R;
import com.xuxian.market.presentation.application.MyApplication;

/**
 * 作者：lubo on 7/22 0022 15:40
 * 邮箱：lubo_wen@126.com
 */
public class FirstStartActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_app_start);
        int width = AbScreenUtils.getScreenHeight(this);
        int height = AbScreenUtils.getScreenWidth(this);

    }
}
