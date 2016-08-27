package com.xuxian.marketpro.activity.tab.forums.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbStrUtil;
import com.ab.util.AbTokenUtil;
import com.ab.view.slide.AbSlidingTabView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.net.AnimeAsyncTask;
import com.xuxian.marketpro.net.NewIssNetLib;
import com.xuxian.marketpro.net.NewIssRequest;
import com.xuxian.marketpro.net.httpclient.HttpRequestException;
import com.xuxian.marketpro.net.httpclient.ParameterList;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.db.UserDb;
import com.xuxian.marketpro.presentation.entity.ForumsEntity;
import com.xuxian.marketpro.presentation.entity.ForumsInfoEntity;
import com.xuxian.marketpro.presentation.entity.UserEntity;

/**
 * Created by youarenotin on 16/8/16.
 */
public class ForumListActivity extends SuperSherlockActivity{
    private ActivityStateView emptyview_state;
    private NetworkAsyncTask forumListAsyncTask;
    private ForumsEntity forums;
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
        params.put("fid",forums.getFid());

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

   public  static  class NetworkAsyncTask extends AnimeAsyncTask<Object,Void,String>{

        public NetworkAsyncTask(String loadingText, Activity mContext) {
            super(loadingText, mContext);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            emptyview_state.setVisibility(View.VISIBLE);
            emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
        }

        @Override
        protected String doInBackground(Object... params) {
            try {
               return  NewIssNetLib.getInstance(getActivity()).getForumList(params[0].toString(), (int) params[1],params[2].toString());
            } catch (HttpRequestException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (AbStrUtil.isEmpty(result)){//如果响应为空
                emptyview_state.setVisibility(View.VISIBLE);
                emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_NODATA);
                emptyview_state.setClickable(true);
                emptyview_state.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emptyview_state.setClickable(false);
                        forumListAsyncTask = new NetworkAsyncTask(null,getActivity());
                        forumListAsyncTask.execute(forums.getFid(),forums, 1, AbTokenUtil.getToken(user_id));
                    }
                });
            }
            else{
                emptyview_state.setVisibility(View.GONE);
                JSONObject object = JSON.parseObject(result);
                int ret = object.getInteger("ret");
                int sub = object.getInteger("sub");
                String msg = object.getString("msg");
                if (ret != 0) {
                    AbDialogUtil.showDialog(getActivity(), msg, true);
                    return;
                }
                JSONObject data = object.getJSONObject("data");
                int postNeedLogin = data.getIntValue("postNeedLogin");
                int page = data.getIntValue("page");
                int perpage = data.getIntValue("perpage");
                int count = data.getIntValue("count");
                int totalpage = data.getIntValue("totalpage");
                JSONArray child = data.getJSONArray("child");
                int length_child = child.size();
                if (child == null || child.isEmpty()) {
                    ForumListActivity.this.mAbSlidingTabView.gonemTabLayout();
                    if (forums != null) {
                        fragmentLoad = new FragmentLoad(ForumListActivity.this.forums.getFid(), length_child);
                        ForumListActivity.this.mAbSlidingTabView.addItemView(ForumListActivity.this.forums.getName(), fragmentLoad);
                        return;
                    }
                    return;
                }
            }
            ///


        }
    }
}
