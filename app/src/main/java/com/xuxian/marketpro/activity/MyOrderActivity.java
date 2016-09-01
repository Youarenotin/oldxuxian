package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.ab.http.IHttpResponseCallBack;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.libraries.util.monitor.OrderMonitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor.OrderEnum;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianNormalRefreshViewHolder;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianRefreshLayout;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.adapter.OrderAdapter;
import com.xuxian.marketpro.presentation.entity.GetHistoryInfoEntity;

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
        OrderMonitor.getInstance().register(new OrderMonitorCallback() {
            public void appOperation(OrderEnum orderEnum) {
                MyOrderActivity.this.currentPage = 1;
                MyOrderActivity.this.getHistoryInfo(MyOrderActivity.this.currentPage);
            }
        }, MyOrderActivity.class.getName());
    }

    @Override
    public void onXuXianRefreshLayoutBeginRefreshing(XuXianRefreshLayout refreshLayout) {

    }

    @Override
    public boolean onXuXianRefreshLayoutBeginLoadingMore(XuXianRefreshLayout refreshLayout) {
        return false;
    }
}
