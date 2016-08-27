package com.xuxian.marketpro.activity.tab.forums.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianRefreshLayout;
import com.xuxian.marketpro.presentation.View.viewpage.BannerViewPager;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.db.UserDb;

/**
 * Created by youarenotin on 16/8/27.
 */
public class FragmentLoad extends SuperFragment implements XuXianRefreshLayout.XuXianRefreshLayoutDelegate{
    private XuXianRefreshLayout bla_forum_list;
    private int currentPage;
    private ActivityStateView emptyview_state;
    private String fid;
    private ForumListActivity.NetworkAsyncTask forumListAsyncTask;
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

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onXuXianRefreshLayoutBeginRefreshing(XuXianRefreshLayout refreshLayout) {

    }

    @Override
    public boolean onXuXianRefreshLayoutBeginLoadingMore(XuXianRefreshLayout refreshLayout) {
        this.currentPage++;
        if (this.currentPage<=this.totalpage){
            setLoadingStatus();
        }
    }
}
