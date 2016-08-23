package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.http.AbHttpUtil;
import com.ab.http.IHttpResponseCallBack;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbToastUtil;
import com.easyandroidanimations.library.BounceAnimation;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.libraries.util.monitor.AddressMonitor;
import com.xuxian.marketpro.libraries.util.monitor.GoodsMonitor;
import com.xuxian.marketpro.libraries.util.monitor.UserMonitor;
import com.xuxian.marketpro.net.NewIssRequest;
import com.xuxian.marketpro.net.RequestParamsNet;
import com.xuxian.marketpro.presentation.db.UserDb;
import com.xuxian.marketpro.presentation.entity.LoginEntity;
import com.xuxian.marketpro.presentation.entity.StatusAndPageEntity;
import com.xuxian.marketpro.presentation.entity.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xuxian.marketpro.libraries.util.monitor.monitor.AddressCartEnum;
import static com.xuxian.marketpro.libraries.util.monitor.monitor.GoodsEnum;

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
        userDb = new UserDb(getActivity());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
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
        UserMonitor.getInstance().registerUserMonitorCallback(LoginActivity.class.getSimpleName(), new UserMonitor.UserMonitorCallback() {
            @Override
            public void appOperation(UserEntity userEntity) {
                getActivity().finish();
            }
        });
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
        loginHttpResponseCallBack = new IHttpResponseCallBack<LoginEntity>() {
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
                if (t != null && AbDialogUtil.isStatus(LoginActivity.this, t.getStatus().getCode(), t.getStatus().getMessage())) {
                    UserEntity userEntity = t.getData();
                    if (userEntity == null) {
                        return;
                    }
                    if (userEntity.getUsers() == null || userEntity.getUsers().isEmpty() || userEntity.getUsers().size() <= 1) {
                        //保存 user_id
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_ID, userEntity.getUserid());
                        //保存 user_token
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_TOKEN, userEntity.getToken());
                        //保存 user_email
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_EMAIL, userEntity.getEmail());
                        //保存 user_name
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_NAME, userEntity.getUsername());
                        //保存 user_phone
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_PHONE, userEntity.getPhone());
                        //保存 head url
                        AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_HEAD_ICON, userEntity.getHead_ico());
                        userDb.saveData(userEntity);//保存到db中
                        GoodsMonitor.getInstance().issueGoodsMonitorCallback(GoodsEnum.REFRESH_LISTVIEW);
                        AddressMonitor.getInstance().issueMonitorCallback(AddressCartEnum.QUERY_ALL_ADDRESSES, null);
                        UserMonitor.getInstance().issueMonitor(userEntity);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("选择账号");
        builder.setItems(usersItems, new ChooseOnClickListenser(usersItems, mapUser));
        builder.create().show();
    }


    class ChooseOnClickListenser implements DialogInterface.OnClickListener {
        private String[] usersItems;
        private Map<String, UserEntity> mapUser;

        public ChooseOnClickListenser(String[] usersItems, Map<String, UserEntity> mapUser) {
            this.mapUser = mapUser;
            this.usersItems = usersItems;
        }


        @Override
        public void onClick(DialogInterface dialog, int which) {
            String usersItem = usersItems[which];
            UserEntity userEntity = mapUser.get(usersItem);
            AbHttpUtil.getInstance(getActivity()).postAndParse(
                    NewIssRequest.SUREMOBILE,
                    RequestParamsNet.getInstance(getActivity()).suremobile(userEntity.getUserid(), userEntity.getPhone()),
                    StatusAndPageEntity.class,
                    new SuremobileIHttpResponseCallBack(userEntity));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_find_password:
                ActivityUtil.startFindActivity(getActivity());
                break;
            case R.id.btn_registered_users:
                ActivityUtil.startRegisterActivity(getActivity());
                break;
            case R.id.btn_login:
                login(v);
                break;
            default:
        }
    }

    /**
     * 点击登录
     *
     * @param v
     */
    private void login(View v) {
        //不满足
        if (TextUtils.isEmpty(ed_login_password.getText().toString()) && TextUtils.isEmpty(ed_login_user.getText().toString())) {
            new BounceAnimation(ed_login_user).setNumOfBounces(3).setDuration(300).animate();
            new BounceAnimation(ed_login_password).setNumOfBounces(3).setDuration(300).animate();
        } else if (TextUtils.isEmpty(ed_login_password.getText().toString())) {
            new BounceAnimation(ed_login_password).setNumOfBounces(3).setDuration(300).animate();
        } else if (TextUtils.isEmpty(ed_login_user.getText().toString())) {
            new BounceAnimation(ed_login_user).setNumOfBounces(3).setDuration(300).animate();
        } else {//满足条件
            AbHttpUtil.getInstance(getActivity()).postAndParse(
                    NewIssRequest.LOGIN,
                    RequestParamsNet.getInstance(getActivity()).login(ed_login_user.getText().toString(), ed_login_password.getText().toString()),
                    LoginEntity.class,
                    this.loginHttpResponseCallBack
            );
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class SuremobileIHttpResponseCallBack implements IHttpResponseCallBack<StatusAndPageEntity> {
        private UserEntity userEntity;

        public SuremobileIHttpResponseCallBack(UserEntity userEntity) {
            this.userEntity = userEntity;
        }

        @Override
        public void EndToParse() {

        }

        @Override
        public void FailedParseBean(String str) {
            dismissLoadingDialog();
            Toast.makeText(getActivity(), "网络不给力,请重试", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void StartToParse() {
            showLoadingDialog("");
        }

        @Override
        public void SucceedParseBean(StatusAndPageEntity t) {
            dismissLoadingDialog();
            if (t != null) {
                StatusAndPageEntity.StatusEntity statusEntity = t.getStatus();
                if (statusEntity != null && AbDialogUtil.isStatus(getActivity(), t.getStatus().getCode(), t.getStatus().getMessage())) {
                    AbToastUtil.showToast(LoginActivity.this.getActivity(), statusEntity.getMessage());
                    AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_ID, this.userEntity.getUserid());
                    AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_TOKEN, this.userEntity.getToken());
                    AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_EMAIL, this.userEntity.getEmail());
                    AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_NAME, this.userEntity.getUsername());
                    AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_PHONE, this.userEntity.getPhone());
                    AbPreferenceUtils.savePrefString(LoginActivity.this.getActivity(), LoginActivity.USER_HEAD_ICON, this.userEntity.getHead_ico());
                    userDb.saveData(this.userEntity);
                    GoodsMonitor.getInstance().issueGoodsMonitorCallback(GoodsEnum.REFRESH_LISTVIEW);
                    AddressMonitor.getInstance().issueMonitorCallback(AddressCartEnum.QUERY_ALL_ADDRESSES, null);
                    UserMonitor.getInstance().issueMonitor(this.userEntity);
                }
            }
        }
    }
}
