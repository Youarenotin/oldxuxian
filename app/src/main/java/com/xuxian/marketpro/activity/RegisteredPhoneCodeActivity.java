package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.libraries.util.ActivityUtil;

/**
 * Created by youarenotin on 16/8/22.
 */
public class RegisteredPhoneCodeActivity extends SuperSherlockActivity implements View.OnClickListener {

    private Button bt_get_yanzhengma;
    private CheckBox cb_terms_service;
    private EditText ed_phonenumber;
    //    private NetworkAsyncTask phoneCodeAsyncTask;
    private TextView tv_terms_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_phonecode_layout);
        initTitleBar();
        initFindViewById();
        setListener();
        init();
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTitleBar() {
        setTitle("获得验证码");
    }

    @Override
    protected void initFindViewById() {
        this.ed_phonenumber = (EditText) findViewById(R.id.ed_phonenumber);
        this.bt_get_yanzhengma = (Button) findViewById(R.id.bt_get_yanzhengma);
        this.bt_get_yanzhengma.setAlpha(0.5f);
        this.cb_terms_service = (CheckBox) findViewById(R.id.cb_terms_service);
        this.tv_terms_service = (TextView) findViewById(R.id.tv_terms_service);
        this.tv_terms_service.setPadding(10, 0, 10, 0);
        if (this.cb_terms_service.isChecked()) {
            this.bt_get_yanzhengma.setEnabled(true);
        } else {
            this.bt_get_yanzhengma.setEnabled(false);
        }
    }

    @Override
    protected void setListener() {
        bt_get_yanzhengma.setOnClickListener(this);
        tv_terms_service.setOnClickListener(this);
        cb_terms_service.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bt_get_yanzhengma.setAlpha(1.0f);
                } else {
                    bt_get_yanzhengma.setAlpha(0.5f);
                }
                bt_get_yanzhengma.setEnabled(isChecked);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_get_yanzhengma:

                break;
            case R.id.tv_terms_service:
                ActivityUtil.startMessageWebViewActivity(getActivity(), "http://www.xuxian.com/index.php?controller=site&action=help&id=59", "服务条款", false);
                break;

        }
    }
}
