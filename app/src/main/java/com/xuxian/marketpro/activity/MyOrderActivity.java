package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.ab.http.AbHttpUtil;
import com.ab.http.IHttpResponseCallBack;
import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbToastUtil;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.libraries.util.Tools;
import com.xuxian.marketpro.libraries.util.monitor.OrderMonitor;
import com.xuxian.marketpro.libraries.util.monitor.OrderMonitormonitor;
import com.xuxian.marketpro.net.NewIssRequest;
import com.xuxian.marketpro.net.RequestParamsNet;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianNormalRefreshViewHolder;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianRefreshLayout;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.adapter.OrderAdapter;
import com.xuxian.marketpro.presentation.entity.GetHistoryInfoEntity;
import com.xuxian.marketpro.presentation.entity.OrderEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/9/1.
 */
public class MyOrderActivity extends SuperSherlockActivity implements XuXianRefreshLayout.XuXianRefreshLayoutDelegate {
    private XuXianRefreshLayout bla_my_order;
    private int currentPage;
    private ActivityStateView emptyview_state;
    private IHttpResponseCallBack<GetHistoryInfoEntity> getHistoryInfoIHttpResponseCallBack;
    private ListView lv_my_order;
    private final String mPageName = null;
    private OrderAdapter orderAdapter;

    public MyOrderActivity() {
        getHistoryInfoIHttpResponseCallBack=new IHttpResponseCallBack<GetHistoryInfoEntity>() {
            @Override
            public void EndToParse() {

            }

            @Override
            public void FailedParseBean(String str) {
                onLoad();
                if (getLoadingStatus() == Tools.TYPE_IS_FROM_NET) {
                    lv_my_order.setVisibility(8);
                    emptyview_state.setVisibility(0);
                    emptyview_state.setState(3);
                    emptyview_state.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            currentPage = 1;
                            getHistoryInfo(currentPage);
                        }
                    });
                } else if (getLoadingStatus() == Tools.TYPE_IS_FROM_PULLUP) {
                    AbToastUtil.showToast(getActivity(), "加载失败");
                } else if (getLoadingStatus() == Tools.TYPE_IS_FROM_PULL) {
                    onLoad();
                    AbToastUtil.showToast(getActivity(), "刷新失败");
                }
            }

            @Override
            public void StartToParse() {
                if (getLoadingStatus() == Tools.TYPE_IS_FROM_NET) {
                    lv_my_order.setVisibility(View.GONE);
                    emptyview_state.setVisibility(View.VISIBLE);
                    emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
                }
            }

            @Override
            public void SucceedParseBean(GetHistoryInfoEntity t) {
                if (getLoadingStatus() == Tools.TYPE_IS_FROM_NET) {
                    emptyview_state.setVisibility(View.GONE);
                }
                lv_my_order.setVisibility(View.VISIBLE);
                onLoad();
                if (t == null) {
                    return;
                }
                if (t.getStatus() == null || t.getStatus().getCode() != 1) {
                    List<OrderEntity> orderEntities = t.getData();
                    if (orderEntities == null || orderEntities.isEmpty()) {
                        //新页加载失败后 页数恢复
                        currentPage = currentPage - 1;
                        return;
                    } else if (getLoadingStatus() == Tools.TYPE_IS_FROM_NET) {
                        //第一次拉取从网络
                        orderAdapter.setData(orderEntities);
                        return;
                    } else if (getLoadingStatus() == Tools.TYPE_IS_FROM_PULLUP) {
                        //上拉加载更多
                        orderAdapter.addData(orderEntities);
                        return;
                    } else if (getLoadingStatus() == Tools.TYPE_IS_FROM_PULL) {
                        //下拉刷新
                        orderAdapter.setData(orderEntities);
                        return;
                    } else {
                        return;
                    }
                }
                emptyview_state.setNodataText(t.getStatus().getMessage());
                emptyview_state.setVisibility(View.VISIBLE);
                emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_NETWORK_ERROR);
            }
        };
    }

    private void onLoad() {
        bla_my_order.endLoadingMore();
        bla_my_order.endRefreshing();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_layout);
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
        setTitle("我的订单");
    }

    @Override
    protected void initFindViewById() {
        this.bla_my_order = (XuXianRefreshLayout) findViewById(R.id.bla_my_order);
        this.lv_my_order = (ListView) findViewById(R.id.lv_my_order);
        this.emptyview_state = (ActivityStateView) findViewById(R.id.emptyview_state);
        this.lv_my_order.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {
        this.bla_my_order.setDelegate(this);
        this.bla_my_order.setRefreshViewHolder(new XuXianNormalRefreshViewHolder(getActivity(), true));
        OrderMonitor.getInstance().registerMonitor(MyOrderActivity.class.getName(),new OrderMonitor.OrderMonitorCallback() {
            @Override
            public void appOperation(OrderMonitormonitor.OrderEnum orderEnum) {
                currentPage = 1;
                getHistoryInfo(currentPage);
            }

        });
    }

    private void getHistoryInfo(int currentPage) {
        AbHttpUtil.getInstance(getActivity()).postAndParse(
                NewIssRequest.GETHISTORYINFO,
                RequestParamsNet.getInstance(getActivity()).getHistoryInfo(
                        AbPreferenceUtils.loadPrefString(getActivity(), LoginActivity.USER_ID, "0"),
                        AbPreferenceUtils.loadPrefString(getActivity(), LoginActivity.USER_TOKEN),
                        currentPage),
                GetHistoryInfoEntity.class,
                this.getHistoryInfoIHttpResponseCallBack);
    }

    @Override
    public void onXuXianRefreshLayoutBeginRefreshing(XuXianRefreshLayout refreshLayout) {
        this.currentPage = 1;
        setLoadingStatus(Tools.TYPE_IS_FROM_PULL);
        getHistoryInfo(this.currentPage);
    }

    @Override
    public boolean onXuXianRefreshLayoutBeginLoadingMore(XuXianRefreshLayout refreshLayout) {
        setLoadingStatus(Tools.TYPE_IS_FROM_PULLUP);
        this.currentPage++;
        getHistoryInfo(this.currentPage);
        return true;
    }
}
