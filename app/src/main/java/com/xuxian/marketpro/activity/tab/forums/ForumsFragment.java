package com.xuxian.marketpro.activity.tab.forums;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ab.http.AbHttpUtil;
import com.ab.http.IHttpResponseCallBack;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.alibaba.fastjson.JSON;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.net.NewIssRequest;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianNormalRefreshViewHolder;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianRefreshLayout;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.adapter.ForumsAdapter;
import com.xuxian.marketpro.presentation.db.UserDb;
import com.xuxian.marketpro.presentation.entity.ForumsInfoEntity;

import static android.view.View.VISIBLE;

/**
 * Created by youarenotin on 16/8/2.
 */
public class ForumsFragment extends SuperFragment implements XuXianRefreshLayout.XuXianRefreshLayoutDelegate {
    private XuXianRefreshLayout bla_forums;
    private ActivityStateView emptyview_state;
    private ForumsAdapter forumsAdapter;
    private ListView lv_forums;
    private final String mPageName = "ForumsFragment";
    private int screenWidth;
    private UserDb userDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forums_layout, null);

        initTitleBar();
        initFindViewById(view);
        setListener();
        init();
        return view;
    }

    @Override
    protected void init() {
        this.forumsAdapter = new ForumsAdapter(getActivity());
        this.lv_forums.setAdapter(this.forumsAdapter);
        this.userDb = new UserDb(getActivity());
        this.forumsAdapter.setUserDb(this.userDb);
        request(111);
    }

    private void request(int i) {
        setLoadingStatus(i);
        AbHttpUtil.getInstance(getActivity()).postAndParse(
                NewIssRequest.FORUM_HOME_PAGE,
                String.class,
                new IHttpResponseCallBack<String>() {
                    @Override
                    public void EndToParse() {

                    }

                    @Override
                    public void FailedParseBean(String str) {
                        emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_NETWORK_ERROR);
                        emptyview_state.setState(VISIBLE);
                    }

                    @Override
                    public void StartToParse() {
                        emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
                        emptyview_state.setVisibility(VISIBLE);
                    }

                    @Override
                    public void SucceedParseBean(String result) {
                        if (!AbStrUtil.isEmpty(result)) {//result不为空
                            ForumsInfoEntity forumsInfoEntity = new ForumsInfoEntity();
                            forumsInfoEntity = JSON.parseObject(result, ForumsInfoEntity.class);
                            emptyview_state.setVisibility(View.GONE);
                            if (forumsInfoEntity.getRet() != 0) {
                                AbDialogUtil.showDialog(ForumsFragment.this.getActivity(), forumsInfoEntity.getMsg(), true);
                                return;
                            }
                            forumsAdapter.setData(forumsInfoEntity.getData().getForums());
                        } else if (loadingStatus == 111) {//result为空
                            emptyview_state.setVisibility(VISIBLE);
                            emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_NODATA);
                            emptyview_state.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    request(111);
                                }
                            });
                        } else {
                            AbToastUtil.showToast(ForumsFragment.this.getActivity(), "刷新失败");
                            ForumsFragment.this.onLoad();
                        }
                    }
                }
        );
    }

    private void onLoad() {
        this.bla_forums.endLoadingMore();
        this.bla_forums.endRefreshing();
    }

    @Override
    protected void initTitleBar() {

        if (getTitle_bar() == null) {
            titleBar();
            setTitle("\u9644\u8fd1");
            setTitleLeftViewShow(false);
            setTitleRightViewShow(false);
            getSherlockActivity().getSupportActionBar().setCustomView(getTitle_bar());
            return;
        }
        setCustomView(getTitle_bar());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initTitleBar();
        }
    }

    @Override
    protected void initFindViewById(View view) {
        this.lv_forums = (ListView) view.findViewById(R.id.lv_forums);
        this.emptyview_state = (ActivityStateView) view.findViewById(R.id.emptyview_state);
        this.bla_forums = (XuXianRefreshLayout) view.findViewById(R.id.bla_forums);
    }

    @Override
    protected void setListener() {
        this.bla_forums.setDelegate(this);
        this.bla_forums.setRefreshViewHolder(new XuXianNormalRefreshViewHolder(getActivity(), false));
    }

    @Override
    public void onXuXianRefreshLayoutBeginRefreshing(XuXianRefreshLayout refreshLayout) {
        request(111);
    }

    @Override
    public boolean onXuXianRefreshLayoutBeginLoadingMore(XuXianRefreshLayout refreshLayout) {
        return false;
    }
}
