package com.xuxian.marketpro.activity.tab.goods;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Region;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.IHttpResponseCallBack;
import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbScreenUtils;
import com.ab.util.AbViewUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.activity.tab.TabMainFragmentActivity;
import com.xuxian.marketpro.appbase.view.MGridView;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.libraries.util.monitor.GoodsMonitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor;
import com.xuxian.marketpro.net.NewIssRequest;
import com.xuxian.marketpro.net.RequestParamsNet;
import com.xuxian.marketpro.presentation.View.adapter.HeadViewPagerAdapter;
import com.xuxian.marketpro.presentation.View.adapter.SimpleAdapterListView02;
import com.xuxian.marketpro.presentation.View.listview.PinnedSectionListView;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianNormalRefreshViewHolder;
import com.xuxian.marketpro.presentation.View.refreshlayout.XuXianRefreshLayout;
import com.xuxian.marketpro.presentation.View.viewpage.BannerViewPager;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.adapter.GoodsFragmentHeaderAdapter;
import com.xuxian.marketpro.presentation.db.GoodsListDb;
import com.xuxian.marketpro.presentation.entity.GoodsFragmentHeaderEntity;
import com.xuxian.marketpro.presentation.entity.GoodsFragmentHeaderEntity.DataEntity.AppEntity;
import com.xuxian.marketpro.presentation.entity.GoodsFragmentHeaderEntity.DataEntity.BannerEntity;
import com.xuxian.marketpro.presentation.entity.GoodsFragmentHeaderEntity.DataEntity.BlocksEntity;
import com.xuxian.marketpro.presentation.entity.GoodsListEntity;
import com.xuxian.marketpro.presentation.entity.IndexEntity;
import com.xuxian.marketpro.presentation.entity.IndexEntity.GoodsEntity;
import com.xuxian.marketpro.presentation.entity.LocalConstant;

import java.util.List;


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
    private TextView tv_bar_main_city_name;//title中城市选择按钮
    private TextView tv_bar_main_store_name;//title中店面选择按钮
    private ActivityStateView emptyview_state;
    private PinnedSectionListView pinnedSectionListView;//listview
    private XuXianRefreshLayout bla_goods;
    private LinearLayout headerview_main;
    private RelativeLayout rl_headerview_goods_banner;
    private BannerViewPager vp_headerview_goods_banner;
    private LinearLayout ll_headerview_goods_group;
    private MGridView gv_headerview_goods_app;
    private LinearLayout ll_headerview_goods_postion;
    private GoodsListDb goodsListDb;
    private SimpleAdapterListView02 mAdapter;//listview的适配器
    private IHttpResponseCallBack<GoodsFragmentHeaderEntity> goodsFragmentHeaderHttpResponseCallBack;
    private boolean isCacheGoods;
    private HeadViewPagerAdapter headViewPagerAdapter;//banner的适配器
    private GoodsFragmentHeaderAdapter goodsFragmentHeaderAdapter;//GridView的适配器
    private TabMainFragmentActivity tabMainFragmentActivity;


    public GoodsFragment(TabMainFragmentActivity activity) {

        goodsFragmentHeaderHttpResponseCallBack=new IHttpResponseCallBack<GoodsFragmentHeaderEntity>() {
            @Override
            public void EndToParse() {

            }

            @Override
            public void FailedParseBean(String str) {
                if (getLoadingStatus()==333){
                    bla_goods.endRefreshing();
                    return;
                }
                if (AbPreferenceUtils.loadPrefInt(getActivity(), "site_id", 0) > 0) {
                    emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_NODATA);
                } else {
                    emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_NODATA, "点击屏幕,选择提货点");
                }
                emptyview_state.setVisibility(View.VISIBLE);
                emptyview_state.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (AbPreferenceUtils.loadPrefInt(getActivity(), "site_id", 0) > 0) {
                            emptyview_state.setVisibility(View.VISIBLE);
                            emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
                            request(111);
                            return;
                        }
                        ActivityUtil.startStoreFragmentActivity(getActivity(), null);
                    }
                });
            }

            @Override
            public void StartToParse() {

            }

            @Override
            public void SucceedParseBean(GoodsFragmentHeaderEntity goodsFragmentHeaderEntity) {
                int code=0;
                if (goodsFragmentHeaderEntity==null || goodsFragmentHeaderEntity.getData()==null){//header数据为空
                    rl_headerview_goods_banner.setVisibility(View.GONE);
                    gv_headerview_goods_app.setVisibility(View.GONE);
                    ll_headerview_goods_postion.setVisibility(View.GONE);
                    return;
                }
                if (goodsFragmentHeaderEntity!=null && goodsFragmentHeaderEntity.getData()!=null){//header数据不为空
                    code = goodsFragmentHeaderEntity.getStatus().getCode();//获取状态代码
                }
                initIndexData(code);//获取listview数据
                headerview_main.setBackgroundColor(Color.parseColor("#EEEEEE"));
                List<BannerEntity> bannerEntities = goodsFragmentHeaderEntity.getData().getBanner();
                List<AppEntity> appEntities = goodsFragmentHeaderEntity.getData().getApp();
                List<List<BlocksEntity>> postionEntitys = goodsFragmentHeaderEntity.getData().getBlocks();
                if (bannerEntities==null || bannerEntities.isEmpty()){
                    rl_headerview_goods_banner.setVisibility(View.GONE);
                    ll_headerview_goods_group.removeAllViews();
                    vp_headerview_goods_banner.removeAllViews();
                    if (headViewPagerAdapter != null) {
                        headViewPagerAdapter.setData(null);
                    }
                }
                else{
                    rl_headerview_goods_banner.setVisibility(View.VISIBLE);
                    ll_headerview_goods_group.removeAllViews();
                    vp_headerview_goods_banner.addImage(bannerEntities.size(), ll_headerview_goods_group);
                    headViewPagerAdapter.setData(bannerEntities);
                    headViewPagerAdapter.setTabMainFragmentActivity(tabMainFragmentActivity);
                    vp_headerview_goods_banner.setCurrentItem(1);
                    if (!AbPreferenceUtils.loadPrefBoolean(getActivity(), LocalConstant.SHOW_XIAN_PIN_POPUP, false)) {
//                        ActivityUtil.StartXianPinPopupActivity(getActivity());
                    }
                }
                if (appEntities == null || appEntities.isEmpty()) {
                    gv_headerview_goods_app.setVisibility(View.GONE);
                    if (goodsFragmentHeaderAdapter != null) {
                        goodsFragmentHeaderAdapter.setData(null);
                    }
                } else {
                    gv_headerview_goods_app.setVisibility(View.VISIBLE);
                    if (goodsFragmentHeaderAdapter == null) {
                        goodsFragmentHeaderAdapter = new GoodsFragmentHeaderAdapter(getActivity(),appEntities,tabMainFragmentActivity);
                        gv_headerview_goods_app.setAdapter(goodsFragmentHeaderAdapter);
                    } else {
                        goodsFragmentHeaderAdapter.setData(appEntities);
                    }
                }
                if (postionEntitys == null || postionEntitys.isEmpty()) {
                    ll_headerview_goods_postion.setVisibility(View.GONE);
                    ll_headerview_goods_postion.removeAllViews();
                    return;
                }
                ll_headerview_goods_postion.setVisibility(View.VISIBLE);
                ll_headerview_goods_postion.removeAllViews();
//                for (int i = 0; i < postionEntitys.size(); i ++) {
//                    List<BlocksEntity> rangeList = (List) postionEntitys.get(i);
//                    View relativeLayout = new RelativeLayout(getActivity());
//                    relativeLayout.setBackgroundColor(-1);
//                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    layoutParams.bottomMargin = 20;
//                    if (!(rangeList == null || rangeList.isEmpty())) {
//                        for (int j = 0; j < rangeList.size(); j += 1) {
////                            BlocksEntity range = (BlocksEntity) rangeList.get(j);
////                            relativeLayout = new RelativeLayout(tabMainFragmentActivity);
////                            String[] a_postion = range.getA_position().split("_");
////                            int x = (int) (((double) screenWidth) * Double.valueOf(a_postion[0]).doubleValue());
////                            int y = (int) (((double) screenWidth) * Double.valueOf(a_postion[1]).doubleValue());
////                            RelativeLayout.LayoutParams linearLayout_params = new RelativeLayout.LayoutParams((int) (((double) screenWidth) * Double.valueOf(range.getWidth()).doubleValue()), (int) (((double) screenWidth) * Double.valueOf(range.getHeight()).doubleValue()));
////                            linearLayout_params.topMargin = x;
////                            linearLayout_params.leftMargin = y;
////                            relativeLayout.setLayoutParams(linearLayout_params);
////                            String show_type = range.getShow_type();
////                            RelativeLayout.LayoutParams bvpaLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
////                            RangeViewPagerAdapter bvpaAdapter = new RangeViewPagerAdapter(tabMainFragmentActivity);
////                            BannerViewPager bvp = new BannerViewPager(tabMainFragmentActivity);
////                            bvp.setAdapter(bvpaAdapter);
////                            relativeLayout.addView(bvp, bvpaLayoutParams);
////                            if (!TextUtils.equals(show_type, ShowType.SINGLEIMG.getValue() + CoinPacketExtension.NAMESPACE)) {
////                                RelativeLayout.LayoutParams dotLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
////                                dotLayoutParams.addRule(12);
////                                dotLayoutParams.addRule(14);
////                                dotLayoutParams.bottomMargin = 30;
////                                LinearLayout dotLayout = new LinearLayout(tabMainFragmentActivity);
////                                dotLayout.setOrientation(LinearLayout.HORIZONTAL);
////                                dotLayout.setLayoutParams(dotLayoutParams);
////                                bvp.addImage(range.getSale_as().size(), dotLayout);
////                                relativeLayout.addView(dotLayout);
////                                if (TextUtils.equals(show_type, ShowType.MULTIPLAY.getValue() + CoinPacketExtension.NAMESPACE)) {
////                                    bvp.startPlay();
////                                } else {
////                                    if (TextUtils.equals(show_type, ShowType.MULTISLIDING.getValue() + CoinPacketExtension.NAMESPACE)) {
////                                    }
////                                }
////                            }
////                            bvp.setCurrentItem(1);
////                            bvpaAdapter.setData(range);
////                            bvpaAdapter.setTabMainFragmentActivity(tabMainFragmentActivity);
////                            relativeLayout.addView(relativeLayout);
//                        }
//                    }
//                    ll_headerview_goods_postion.addView(relativeLayout, i, layoutParams);
//                }
            }
        };
    }

    private void initIndexData(int code) {
        AbHttpUtil.getInstance(getActivity()).postAndParse(
                NewIssRequest.INDEX
                ,RequestParamsNet.getInstance(getActivity()).index( AbPreferenceUtils.loadPrefInt(getActivity(),"site_id",0),AbPreferenceUtils.loadPrefString(getActivity(),"USRE_ID","0"),AbPreferenceUtils.loadPrefString(getActivity(),"USER_TOKEN"))
                , IndexEntity.class
                ,new IndexHttpResponseCallBack(code,null)
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.screenWidth = AbScreenUtils.getScreenWidth(getActivity());
        View view = View.inflate(getActivity(), R.layout.fragment_goods, null);
        initTitleBar();
        initFindViewById(view);
        initHeaderView();
        setListener();
        init();
        return view;
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.headerview_goods_fragment, null);
        headerview_main = (LinearLayout) headerView.findViewById(R.id.headview_main);
//        headerview_main.setBackgroundColor(Color.TRANSPARENT);
        rl_headerview_goods_banner = (RelativeLayout) headerView.findViewById(R.id.rl_headerview_goods_banner);
        vp_headerview_goods_banner = (BannerViewPager) headerView.findViewById(R.id.vp_headerview_goods_banner);
        AbViewUtil.setViewWH(vp_headerview_goods_banner, screenWidth, screenWidth / 2);
        ll_headerview_goods_group = (LinearLayout) headerView.findViewById(R.id.ll_headerview_goods_group);
        gv_headerview_goods_app = (MGridView) headerView.findViewById(R.id.gv_headerview_goods_app);
        ll_headerview_goods_postion = (LinearLayout) headerView.findViewById(R.id.ll_headerview_goods_postion);
        this.headViewPagerAdapter = new HeadViewPagerAdapter(getActivity());
        this.vp_headerview_goods_banner.setAdapter(this.headViewPagerAdapter);
        this.vp_headerview_goods_banner.startPlay();
        this.pinnedSectionListView.addHeaderView(headerView);
        this.mAdapter = new SimpleAdapterListView02(getActivity());
        this.pinnedSectionListView.setAdapter(this.mAdapter);
    }

    @Override
    protected void init() {
        emptyview_state.setVisibility(View.VISIBLE);
        emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
        request(111);
    }

    private void request(int loadingstatus) {
        showStore();
        setLoadingStatus(loadingstatus);
        initHeaderViewData();
    }

    private void initHeaderViewData() {
        int store_id = AbPreferenceUtils.loadPrefInt(getActivity(), "site_id", 0);
        AbHttpUtil.getInstance(getActivity()).postAndParse(
                NewIssRequest.GOODS_FRAGMENT_HEADER
                , RequestParamsNet.getInstance(getActivity()).setGoodsFragmentHeader(AbPreferenceUtils.loadPrefString(getActivity(), "city_id"), store_id)
                , GoodsFragmentHeaderEntity.class
                ,goodsFragmentHeaderHttpResponseCallBack);
    }

    private void showStore() {
        if ("配送".equals(AbPreferenceUtils.loadPrefString(getActivity(), "store_type", "自提"))) {
            this.iv_bar_main_store_icon.setBackgroundResource(R.drawable.bar_peisong_icon);
        } else {
            this.iv_bar_main_store_icon.setBackgroundResource(R.drawable.bar_store_icon);
        }
        String store_name = AbPreferenceUtils.loadPrefString(getActivity(), "site_name", "");
        this.tv_bar_main_city_name.setText(AbPreferenceUtils.loadPrefString(getActivity(), "city_name", ""));
        this.tv_bar_main_store_name.setText(store_name);
    }

    @Override
    protected void initTitleBar() {
        if (this.bar_view_layout == null) {
            bar_view_layout = LayoutInflater.from(getActivity()).inflate(R.layout.bar_main_layout, null);
            getSherlockActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.green));
            getSherlockActivity().getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            iv_bar_main_store_icon = (ImageView) bar_view_layout.findViewById(R.id.iv_bar_main_store_icon);
            ll_bar_main_store = (LinearLayout) bar_view_layout.findViewById(R.id.ll_bar_main_store);
            ll_bar_main_classification = (LinearLayout) bar_view_layout.findViewById(R.id.ll_bar_main_classification);
            tv_bar_main_city_name = (TextView) bar_view_layout.findViewById(R.id.tv_bar_main_city_name);
            tv_bar_main_store_name = (TextView) bar_view_layout.findViewById(R.id.tv_bar_main_store_name);
            ll_bar_main_store.setBackgroundResource(R.drawable.btn_store_selector);
            ll_bar_main_classification.setBackgroundResource(R.drawable.btn_store_selector);
            getSherlockActivity().getSupportActionBar().setCustomView(bar_view_layout);
            return;
        }
        getSherlockActivity().getSupportActionBar().setCustomView(bar_view_layout);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){//由hidden到show
            initTitleBar();
        }
    }

    @Override
    protected void initFindViewById(View view) {
        emptyview_state = (ActivityStateView) view.findViewById(R.id.emptyview_state);
        pinnedSectionListView = (PinnedSectionListView) view.findViewById(R.id.pslv_goods);
        bla_goods = (XuXianRefreshLayout) view.findViewById(R.id.bla_goods);
        pinnedSectionListView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true));
    }

    @Override
    protected void setListener() {
        bla_goods.setDelegate(this);
        bla_goods.setRefreshViewHolder(new XuXianNormalRefreshViewHolder(getActivity(), false));
        BarOnClickListener barOnClickListener = new BarOnClickListener();
        ll_bar_main_classification.setOnClickListener(barOnClickListener);
        ll_bar_main_store.setOnClickListener(barOnClickListener);
        goodsListDb = new GoodsListDb(getActivity(), GoodsListEntity.class);
        GoodsMonitor.getInstance().registerGoodsMonitor(GoodsFragment.class.getSimpleName(), new GoodsMonitor.GoodsMonitorCallback() {
            @Override
            public void appOprate(monitor.GoodsEnum goodsEnum) {
                switch (goodsEnum) {
                    case REFRESH_GOODS://1

                        break;
                    case REFRESH_LISTVIEW://2

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

    private class BarOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_bar_main_store:
                    //TODO 选店面
                    break;
                case R.id.ll_bar_main_classification :
                    //// TODO: 16/8/9 选择分类
                    ActivityUtil.startClassifyActivity(GoodsFragment.this.getActivity());
                    break;
            }
        }
    }

    private class IndexHttpResponseCallBack implements IHttpResponseCallBack<IndexEntity> {
        private  int  code;
        public IndexHttpResponseCallBack(int code, Object o) {
            this.code=code;
        }

        @Override
        public void EndToParse() {

        }

        @Override
        public void FailedParseBean(String str) {
          emptyview_state.setVisibility(View.GONE);
          bla_goods.endRefreshing();
        }

        @Override
        public void StartToParse() {

        }

        @Override
        public void SucceedParseBean(IndexEntity t) {
            bla_goods.endRefreshing();
            emptyview_state.setVisibility(View.GONE); //这是整个goods fragment数据全部加载完成后
            if (t != null) {
                if (this.code == 1 && t.getStatus() != null && t.getStatus().getCode() == 1) {
                   emptyview_state.setVisibility(View.VISIBLE);
                   emptyview_state.setNodataText(t.getStatus().getMessage());
                   emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_NETWORK_ERROR);
                }
                if (t.getData() == null || t.getData().isEmpty()) {
                   mAdapter.setData(null);
                    return;
                }
               mAdapter.setData(t.getData());
                if (getLoadingStatus() == 111) {
                    int goods_id = AbPreferenceUtils.loadPrefInt(getActivity(), "goods_id", 0);
                    if (goods_id != 0) {
                        setGoodsDetails(goods_id);
                    }
                }
                if (isCacheGoods) {
                    GoodsAsyncTask goodsAsyncTask = new GoodsAsyncTask();
                    Object[] objArr = new Object[0];
                    objArr[0] = t.getData();
                    goodsAsyncTask.execute(objArr);
                    return;
                }
                return;
            }
            //如果t为空
            if (getLoadingStatus() == 333) {
                bla_goods.endRefreshing();
            } else {
                emptyview_state.setVisibility(View.GONE);
            }
            mAdapter.setData(null);
        }
    }

    public void setGoodsDetails(int goods_id) {
        if (this.goodsListDb.queryOneData(goods_id) != null) {
//            ActivityUtil.startGoodsDetailsActivity(getActivity(), goods_id);
        }
        AbPreferenceUtils.savePrefInt(getActivity(), "goods_id", 0);
    }

    private class GoodsAsyncTask extends AsyncTask<Object, Void, String> {
        private GoodsAsyncTask() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            isCacheGoods = false;
        }

        protected String doInBackground(Object... params) {
            saveGoodsList((List<GoodsEntity>) params[0]);
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            isCacheGoods = true;
        }
    }

    public void saveGoodsList(List<GoodsEntity> goodsList) {
        if (goodsList != null && !goodsList.isEmpty()) {
            int length = goodsList.size();
            int i = 0;
            while (i < length) {
                if (!(goodsList.get(i).getGoods() == null || goodsList.get(i).getGoods().isEmpty())) {
                    int goodsLength = goodsList.get(i).getGoods().size();
                    for (int j = 0; j < goodsLength; j += 1) {
                        GoodsListEntity goodsListEntity = goodsList.get(i).getGoods().get(j);
                        if (this.goodsListDb.isExist(goodsListEntity.getId())) {
                            this.goodsListDb.updateData(goodsListEntity);
                        } else {
                            this.goodsListDb.saveData(goodsListEntity);
                        }
                    }
                }
                i += 1;
            }
        }
    }
}
