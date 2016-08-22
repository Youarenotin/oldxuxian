package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.IHttpResponseCallBack;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbToastUtil;
import com.easyandroidanimations.library.BounceAnimation;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.libraries.util.monitor.GoodsMonitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor.AddressCartEnum;
import com.xuxian.marketpro.net.NewIssRequest;
import com.xuxian.marketpro.net.RequestParamsNet;
import com.xuxian.marketpro.presentation.db.UserDb;
import com.xuxian.marketpro.presentation.entity.LoginEntity;
import com.xuxian.marketpro.presentation.entity.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by youarenotin on 16/8/17.
 */
public class LoginActivity extends SuperSherlockActivity implements View.OnClickListener {
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

    public LoginActivity() {
        userDb =new UserDb(getActivity());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
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
        setTitle(R.string.login_title);
        setTitleLeftViewBg(R.drawable.btn_bg_gray);
    }

    @Override
    protected void initFindViewById() {
        this.btn_registered_users = (Button) findViewById(R.id.btn_registered_users);
        this.tv_login_find_password = (TextView) findViewById(R.id.tv_login_find_password);
        this.ed_login_user = (EditText) findViewById(R.id.ed_login_user);
        this.ed_login_password = (EditText) findViewById(R.id.ed_login_password);
        this.btn_login = (Button) findViewById(R.id.btn_login);
    }

    @Override
    protected void setListener() {
        this.btn_registered_users.setOnClickListener(this);
        this.tv_login_find_password.setOnClickListener(this);
        this.btn_login.setOnClickListener(this);
        loginHttpResponseCallBack=new IHttpResponseCallBack<LoginEntity>() {
            @Override
            public void EndToParse() {

            }

            @Override
            public void FailedParseBean(String str) {
                dismissLoadingDialog();
                AbToastUtil.showToast(LoginActivity.this.getActivity(), "网络不给力,请重试");
            }

            @Override
            public void StartToParse() {
                showLoadingDialog("正在登录");
            }

            @Override
            public void SucceedParseBean(LoginEntity t) {
                dismissLoadingDialog();
                if (t != null && AbDialogUtil.isStatus(LoginActivity.this, t.getStatus().getCode(),t.getStatus().getMessage())) {
                    UserEntity userEntity = t.getData();
                    if (userEntity == null) {
                        return;
                    }
                    if (userEntity.getUsers() == null || userEntity.getUsers().isEmpty() || userEntity.getUsers().size() <= 1) {
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_ID, userEntity.getUserid());
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_TOKEN, userEntity.getToken());
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_EMAIL, userEntity.getEmail());
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_NAME, userEntity.getUsername());
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_PHONE, userEntity.getPhone());
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_HEAD_ICON, userEntity.getHead_ico());
                        userDb.saveData(userEntity);
                        GoodsMonitor.getInstance().issueGoodsMonitorCallback(monitor.GoodsEnum.REFRESH_LISTVIEW);
//                        AddressMonitor.getInstance().IssuedMonitor(AddressCartEnum.QUERY_ALL_ADDRESSES, null);
//                        UserMonitor.getInstance().IssuedMonitor(userEntity);
                        return;
                    }
                    List<String> userNameList = new ArrayList();
                    Map<String, UserEntity> mapUser = new HashMap();
                    for (UserEntity loginDto : userEntity.getUsers()) {
                        mapUser.put(loginDto.getUsername(), loginDto);
                        userNameList.add(loginDto.getUsername());
                    }
                    String[] usersItems = new String[userNameList.size()];
                    userNameList.toArray(usersItems);
                    loginSuccess(userEntity, usersItems, mapUser);
                }
            }
        };
    }

    private void loginSuccess(UserEntity userEntity, String[] usersItems, Map<String, UserEntity> mapUser) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_find_password:
                ActivityUtil.startFindActivity(getActivity());
                break;
            case R.id.btn_registered_users:
                ActivityUtil.startRegisterActivity(getActivity());
            case R.id.btn_login:
                login(v);
            default:
        }
    }

    private void login(View v) {
        //不满足
        if (TextUtils.isEmpty(ed_login_password.getText().toString())&&TextUtils.isEmpty(ed_login_user.getText().toString())){
            new BounceAnimation(ed_login_user).setNumOfBounces(3).setDuration(100).animate();
            new BounceAnimation(ed_login_password).setNumOfBounces(3).setDuration(100).animate();
        }
        else if (TextUtils.isEmpty(ed_login_password.getText().toString())){
            new BounceAnimation(ed_login_password).setNumOfBounces(3).setDuration(100).animate();
        }
        else if (TextUtils.isEmpty(ed_login_user.getText().toString())){
            new BounceAnimation(ed_login_user).setNumOfBounces(3).setDuration(100).animate();
        }else {//满足条件
            AbHttpUtil.getInstance(getActivity()).postAndParse(
                    NewIssRequest.LOGIN,
                    RequestParamsNet.getInstance(getActivity()).login(ed_login_user.getText().toString(),ed_login_password.getText().toString()),
                    LoginEntity.class,
                    loginHttpResponseCallBack
                    );
        }

    }
}
