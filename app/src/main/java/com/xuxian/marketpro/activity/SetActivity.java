package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.presentation.db.CouponDb;
import com.xuxian.marketpro.presentation.db.UserDb;
import com.xuxian.marketpro.presentation.entity.UserEntity;

/**
 * Created by youarenotin on 16/9/7.
 */
public class SetActivity extends SuperSherlockActivity{
    private SampleAdapter adapter;
    private CouponDb couponDb;
    private Button log_out;
    private ListView lv_set;
    private UserDb userDb;
    private UserEntity userDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_layout);
        initTitleBar();
        initFindViewById();
        setListener();
        initMenu();
        init();
    }

    private void initMenu() {
        View view = View.inflate(getActivity(), R.layout.login_and_registration, null);
        adapter=new SampleAdapter(getActivity());
        adapter.add(new SampleItem(R.drawable.zhifu,"我的支付方式"));
        adapter.add(new SampleItem(R.drawable.xiaofeizhuangtai,"我的消费状态"));
        adapter.add(new SampleItem(R.drawable.cache_icon,"清空缓存"));
        adapter.add(new SampleItem(R.drawable.sliding_help,"帮助"));
        adapter.add(new SampleItem(R.drawable.invitation_icon,"邀请好友"));
        adapter.add(new SampleItem(R.drawable.guanyu,"关于"));
        lv_set.setAdapter(adapter);
        lv_set.addFooterView(view);
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void init() {
        userDb=new UserDb(getActivity());
        couponDb=new CouponDb(getActivity());
        userDto=userDb.queryData();
        if (userDto!=null)
            log_out.setText("注销当前登录");
    }

    @Override
    protected void initTitleBar() {
        setTitle("设置");
    }

    @Override
    protected void initFindViewById() {
        lv_set= (ListView) findViewById(R.id.lv_set);
    }

    @Override
    protected void setListener() {

    }

    private class SampleAdapter extends ArrayAdapter<SampleItem>{
        public SampleAdapter(Context context) {
            super(context, 0);
        }
    }
    private class SampleItem {
        public int iconRes;
        public String title;

        public SampleItem(int iconRes, String title) {
            this.iconRes = iconRes;
            this.title = title;
        }
    }

}
