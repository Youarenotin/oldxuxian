package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.ab.util.AbScreenUtils;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.libraries.gaodemap.GaoDeLocationLibraries;
import com.xuxian.marketpro.libraries.util.monitor.monitor;
import com.xuxian.marketpro.presentation.application.MyApplication;

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
        setContentView(R.layout.textlayout);
        int width = AbScreenUtils.getScreenHeight(this);
        int height = AbScreenUtils.getScreenWidth(this);
        GaoDeLocationLibraries.getInstance(getApplicationContext()).startLocation(true, monitor.GaoDeLocationEnum.LOCATION_ADDRESS);
    }
}
