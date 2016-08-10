package com.xuxian.marketpro.activity.tab.goods;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbScreenUtils;
import com.ab.util.AbViewUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.activity.tab.TabMainFragmentActivity;
import com.xuxian.marketpro.appbase.view.MGridView;
import com.xuxian.marketpro.libraries.util.monitor.GoodsMonitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor;
import com.xuxian.marketpro.presentation.View.adapter.SimpleAdapterListView02;
import com.xuxian.marketpro.presentation.View.listview.PinnedSectionListView;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianNormalRefreshViewHolder;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianRefreshLayout;
import com.xuxian.marketpro.presentation.View.viewpage.BannerViewPager;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.db.GoodsListDb;
import com.xuxian.marketpro.presentation.entity.GoodsListEntity;


/**
 * 作者：lubo on 8/1 0001 18:12
 * 邮箱：lubo_wen@126.com
 */
@SuppressLint("ValidFragment")
public class GoodsFragment extends SuperFragment implements XuXianRefreshLayout.XuXianRefreshLayoutDelegate {


    private int screenWidth;
    private View bar_view_layout;
    private ImageView iv_bar_main_store_icon;
    private LinearLayout ll_bar_main_store;
    private LinearLayout ll_bar_main_classification;
    private TextView tv_bar_main_city_name;
    private TextView tv_bar_main_store_name;
    private ActivityStateView emptyview_state;
    private PinnedSectionListView pinnedSectionListView;
    private XuXianRefreshLayout bla_goods;
    private LinearLayout headerview_main;
    private RelativeLayout rl_headerview_goods_banner;
    private View vp_headerview_goods_banner;
    private LinearLayout ll_headerview_goods_group;
    private MGridView gv_headerview_goods_app;
    private LinearLayout ll_headerview_goods_postion;
    private GoodsListDb goodsListDb;
    private SimpleAdapterListView02 mAdapter;

    public GoodsFragment(TabMainFragmentActivity activity){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.screenWidth= AbScreenUtils.getScreenWidth(getActivity());
        View view = View.inflate(getActivity(), R.layout.fragment_goods, null);
        initTitleBar();
        initFindViewById(view);
        initHeaderView();
        setListener();
        init();
        return view;
    }

    private void initHeaderView() {
        View headerView=LayoutInflater.from(getActivity()).inflate(R.layout.headerview_goods_fragment,null);
        headerview_main= (LinearLayout) headerView.findViewById(R.id.headview_main);
//        headerview_main.setBackgroundColor(Color.TRANSPARENT);
        rl_headerview_goods_banner= (RelativeLayout) headerView.findViewById(R.id.rl_headerview_goods_banner);
         vp_headerview_goods_banner = headerView.findViewById(R.id.vp_headerview_goods_banner);
        AbViewUtil.setViewWH(vp_headerview_goods_banner,screenWidth,screenWidth/2);
        ll_headerview_goods_group= (LinearLayout) headerView.findViewById(R.id.ll_headerview_goods_group);
        gv_headerview_goods_app= (MGridView) headerView.findViewById(R.id.gv_headerview_goods_app);
        ll_headerview_goods_postion= (LinearLayout) headerView.findViewById(R.id.ll_headerview_goods_postion);

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTitleBar() {
        if (this.bar_view_layout==null){
            bar_view_layout = LayoutInflater.from(getActivity()).inflate(R.layout.bar_main_layout, null);
            getSherlockActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.green));
            getSherlockActivity().getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            iv_bar_main_store_icon= (ImageView) bar_view_layout.findViewById(R.id.iv_bar_main_store_icon);
            ll_bar_main_store= (LinearLayout) bar_view_layout.findViewById(R.id.ll_bar_main_store);
            ll_bar_main_classification= (LinearLayout) bar_view_layout.findViewById(R.id.ll_bar_main_classification);
            tv_bar_main_city_name= (TextView) bar_view_layout.findViewById(R.id.tv_bar_main_city_name);
            tv_bar_main_store_name= (TextView) bar_view_layout.findViewById(R.id.tv_bar_main_store_name);
            ll_bar_main_store.setBackgroundResource(R.drawable.btn_store_selector);
            ll_bar_main_classification.setBackgroundResource(R.drawable.btn_store_selector);
            getSherlockActivity().getActionBar().setCustomView(bar_view_layout);
            return ;
        }
        getSherlockActivity().getActionBar().setCustomView(bar_view_layout);
    }

    @Override
    protected void initFindViewById(View view) {
        emptyview_state= (ActivityStateView) view.findViewById(R.id.emptyview_state);
        pinnedSectionListView= (PinnedSectionListView) view.findViewById(R.id.pslv_goods);
        bla_goods= (XuXianRefreshLayout) view.findViewById(R.id.bla_goods);
        pinnedSectionListView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(),false,true));
    }

    @Override
    protected void setListener() {
        bla_goods.setDelegate(this);
        bla_goods.setRefreshViewHolder(new XuXianNormalRefreshViewHolder(getActivity(),false));
        BarOnClickListener barOnClickListener=new BarOnClickListener();
        ll_bar_main_classification.setOnClickListener(barOnClickListener);
        ll_bar_main_store.setOnClickListener(barOnClickListener);
        goodsListDb=new GoodsListDb(getActivity(), GoodsListEntity.class);
        GoodsMonitor.getInstance().registerGoodsMonitor(GoodsFragment.class.getSimpleName(), new GoodsMonitor.GoodsMonitorCallback() {
            @Override
            public void appOprate(monitor.GoodsEnum goodsEnum) {
                switch (goodsEnum){
                    case REFRESH_GOODS://1

                        break;
                    case REFRESH_LISTVIEW://2
                        if (mAdapter)
                        break;
                }
            }
        });
    }

    @Override
    public void onXuXianRefreshLayoutBeginRefreshing(XuXianRefreshLayout refreshLayout) {

    }

    @Override
    public boolean onXuXianRefreshLayoutBeginLoadingMore(XuXianRefreshLayout refreshLayout) {
        return false;
    }

    private class BarOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_bar_main_store:
                    //TODO 选店面
                    break;
                case R.id.ll_bar_main_classification;
                    //// TODO: 16/8/9 选择分类
                    break;
            }
        }
    }
}
