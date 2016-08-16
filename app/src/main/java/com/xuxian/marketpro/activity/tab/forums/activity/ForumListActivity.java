package com.xuxian.marketpro.activity.tab.forums.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.view.slide.AbSlidingTabView;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.net.httpclient.ParameterList;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.db.UserDb;
import com.xuxian.marketpro.presentation.entity.ForumsInfoEntity;
import com.xuxian.marketpro.presentation.entity.UserEntity;

/**
 * Created by youarenotin on 16/8/16.
 */
public class ForumListActivity extends SuperSherlockActivity{
    private ActivityStateView emptyview_state;
    private ForumsInfoEntity.DataEntity.ForumsEntity forums;
    private AbSlidingTabView mAbSlidingTabView;
    private UserDb userDb;
    private UserEntity userDto;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum);
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
        this.userDb = new UserDb(getActivity());
        this.userDto = this.userDb.queryData();
        if (this.userDto != null) {
            this.user_id = this.userDto.getUserid();
        }
        setTitle(this.forums.getName());
        AbRequestParams params=new AbRequestParams();
//        params.put("fid",);
//        AbHttpUtil.getInstance(getActivity()).postAndParse();
    }

    @Override
    protected void initTitleBar() {
        setTitleLeftViewShow(true);
        setTitleRightViewShow(true);
        setTitleRightIcon(R.drawable.fatie_icon);
    }

    @Override
    protected void initFindViewById() {
        this.mAbSlidingTabView = (AbSlidingTabView) findViewById(R.id.mAbSlidingTabView);
        this.mAbSlidingTabView.setTabTextColor(Color.BLACK);
        this.mAbSlidingTabView.setTabSelectColor(Color.rgb(30, 168, 131));
        this.mAbSlidingTabView.setTabBackgroundResource(R.drawable.tab_bg);
        this.mAbSlidingTabView.setTabLayoutBackgroundResource(R.drawable.slide_top);
        this.emptyview_state = (ActivityStateView) findViewById(R.id.emptyview_state);
    }

    @Override
    protected void setListener() {
        getTitleRightClick().setOnClickListener(new BarOnClickListener());
    }

    public class BarOnClickListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_title_bar_right_click:
                    if (ForumListActivity.this.forums != null) {
//                        ActivityUtil.startForumListReleaseActivity(ForumListActivity.this.getActivity(), ForumListActivity.this.forums.getFid(), ForumListActivity.this.forums.getName());
                    }
                default:
            }
        }
    }
}
