package com.xuxian.marketpro.activity.tab.forums.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ab.util.AbScreenUtils;
import com.ab.util.AbTokenUtil;
import com.ab.util.AbViewUtil;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.libraries.util.Tools;
import com.xuxian.marketpro.libraries.util.monitor.ForumListMonitor;
import com.xuxian.marketpro.net.NewIssNetLib;
import com.xuxian.marketpro.net.httpclient.HttpRequestException;
import com.xuxian.marketpro.presentation.View.adapter.ForumsListAdapter;
import com.xuxian.marketpro.presentation.View.adapter.ForumsViewPagerAdapter;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianNormalRefreshViewHolder;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianRefreshLayout;
import com.xuxian.marketpro.presentation.View.viewpage.BannerViewPager;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.db.UserDb;
import com.xuxian.marketpro.presentation.entity.ForumListEntity;
import com.xuxian.marketpro.presentation.entity.UserEntity;

import java.util.List;

import static com.xuxian.marketpro.activity.tab.forums.activity.ForumListActivity.*;

/**
 * Created by youarenotin on 16/8/27.
 */
public class FragmentLoad extends SuperFragment implements XuXianRefreshLayout.XuXianRefreshLayoutDelegate{
    private XuXianRefreshLayout bla_forum_list;
    private int currentPage;
    private ActivityStateView emptyview_state;
    private String fid;
    private NetworkAsyncTask forumListAsyncTask=null;
    private ForumsListAdapter forumsListAdapter;
    private ForumsViewPagerAdapter forumsViewPagerAdapter;
    private ViewGroup group;
    private int length;
    private ListView lv_forum_list;
    private int totalpage;
    private UserDb userDb;
    private UserEntity userDto;
    private String user_id;
    private BannerViewPager vp_headimage;
    public FragmentLoad(String fid, int length_child) {
    }

    @Override
    protected void init() {
        this.lv_forum_list.setVisibility(8);
        this.forumsListAdapter = new ForumsListAdapter(getActivity());
        this.lv_forum_list.setAdapter(this.forumsListAdapter);
        this.userDb = new UserDb(getActivity());
        this.userDto = this.userDb.queryData();
        if (this.userDto != null) {
            this.user_id = this.userDto.getUserid();
        }
        this.forumsViewPagerAdapter.setUserDto(this.userDto);
        setLoadingStatus(Tools.TYPE_IS_FROM_NET);
        this.forumListAsyncTask = new NetworkAsyncTask( null,getActivity());
        this.forumListAsyncTask.execute(this.fid, this.currentPage, AbTokenUtil.getToken(this.user_id));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_layout = inflater.inflate(R.layout.activity_forum_list_layout, null);
        initTitleBar();
        initFindViewById(view_layout);
        initViewPage();
        setListener();
        init();
        return view_layout;
    }

    private void initViewPage() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.headerview_goods_fragment, null);
        this.vp_headimage = (BannerViewPager) headView.findViewById(R.id.vp_headerview_goods_banner);
        int screenWidth = AbScreenUtils.getScreenWidth(getActivity());
        AbViewUtil.setViewWH(this.vp_headimage, screenWidth, (int) (((double) screenWidth) * 0.4d));
        this.group = (ViewGroup) headView.findViewById(R.id.ll_headerview_goods_group);
        this.forumsViewPagerAdapter = new ForumsViewPagerAdapter(getActivity());
        this.vp_headimage.setAdapter(this.forumsViewPagerAdapter);
        this.lv_forum_list.addHeaderView(headView);
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initFindViewById(View view) {
        this.lv_forum_list = (ListView) view.findViewById(R.id.lv_forum_list);
        this.bla_forum_list = (XuXianRefreshLayout) view.findViewById(R.id.bla_forum_list);
        this.emptyview_state = (ActivityStateView) view.findViewById(R.id.emptyview_state);
        this.emptyview_state.setVisibility(View.VISIBLE);
        this.emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
    }

    @Override
    protected void setListener() {
        this.bla_forum_list.setDelegate(this);
        this.bla_forum_list.setRefreshViewHolder(new XuXianNormalRefreshViewHolder(getActivity(), true));
        ForumListMonitor.getInstance().register(new ForumListMonitor.ForumListMonitorCallback() {
            @Override
            public void appOperation(boolean isforumList) {
                if (FragmentLoad.this.userDto != null) {
                    FragmentLoad.this.setLoadingStatus(Tools.TYPE_IS_FROM_PULL);
                    FragmentLoad.this.forumListAsyncTask = new NetworkAsyncTask(null,getActivity());
                    FragmentLoad.this.forumListAsyncTask.execute(new Object[]{FragmentLoad.this.fid, Integer.valueOf(FragmentLoad.this.currentPage), AbTokenUtil.getToken(FragmentLoad.this.userDto.getUserid())});
                }
            }

        }, ForumListActivity.class.getName());
    }

    @Override
    public void onXuXianRefreshLayoutBeginRefreshing(XuXianRefreshLayout refreshLayout) {
        setLoadingStatus(Tools.TYPE_IS_FROM_PULL);
        currentPage=1;
        forumListAsyncTask = new NetworkAsyncTask(null,getActivity());
        forumListAsyncTask.execute(fid,currentPage,AbTokenUtil.getToken(user_id));
    }

    @Override
    public boolean onXuXianRefreshLayoutBeginLoadingMore(XuXianRefreshLayout refreshLayout) {
        this.currentPage++;
        if (this.currentPage<=this.totalpage){//如果当前页不大于等于总页数
            setLoadingStatus(Tools.TYPE_IS_FROM_PULLUP);
            forumListAsyncTask = new NetworkAsyncTask(null,getActivity());
            forumListAsyncTask.execute(fid,currentPage, AbTokenUtil.getToken(user_id));
            return true;
        }
        endLoad();
        this.currentPage--;
        return false;
    }



    private void endLoad() {
        this.bla_forum_list.endLoadingMore();
        this.bla_forum_list.endRefreshing();
    }

    public List<ForumListEntity> setForumList(List<ForumListEntity> data){
        int length =data.size();
        String temp="";
        int i =0 ;
        while(i<length-1 && data.get(i).getTopped().equals("1") && !data.get(i).getTid().equals(temp)){
            data.get(i).setType(1);
            temp=data.get(i).getTid();
            i++;
        }
        return  data;
    }

    private class NetworkAsyncTask extends AsyncTask<Object,Void,String> {
        public NetworkAsyncTask(Object params, FragmentActivity activity) {
            super();
        }

        @Override
        protected String doInBackground(Object... params) {
            String str = null;
            try {
                str = NewIssNetLib.getInstance(getActivity()).getForumList((String) params[0],(int)params[1], (String) params[2]);
            } catch (HttpRequestException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
