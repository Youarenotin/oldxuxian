package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.ab.util.AbPreferenceUtils;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.presentation.entity.LocalConstant;

/**
 * Created by youarenotin on 16/8/24.
 */
public class XianPinPopupActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xian_pin_popup);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            return true;
        return super.onKeyDown(keyCode, event);
    }

    public void know(View view){
        AbPreferenceUtils.savePrefBoolean(this, LocalConstant.SHOW_XIAN_PIN_POPUP,true);
        finish();
    }
}
