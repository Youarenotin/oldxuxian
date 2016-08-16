package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ab.http.IHttpResponseCallBack;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.presentation.db.UserDb;
import com.xuxian.marketpro.presentation.entity.LoginEntity;

/**
 * Created by youarenotin on 16/8/17.
 */
public class LoginActivity extends SuperSherlockActivity {
    public static String USER_ID = "USER_ID";
    public static String USER_TOKEN = "USER_TOKEN";
    public static String USER_EMAIL = "USER_EMAIL";
    public static String USER_NAME = "USER_NAME";
    public static String USER_PHONE = "USER_PHONE";
    public static String USER_HEAD_ICON = "USER_HEAD_ICON";
    private Button btn_login;
    private Button btn_registered_users;
    private EditText ed_login_password;
    private EditText ed_login_user;
    private IHttpResponseCallBack<LoginEntity> loginHttpResponseCallBack;
    private TextView tv_login_find_password;
    private UserDb userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
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

    }

    @Override
    protected void initFindViewById() {

    }

    @Override
    protected void setListener() {

    }
}
