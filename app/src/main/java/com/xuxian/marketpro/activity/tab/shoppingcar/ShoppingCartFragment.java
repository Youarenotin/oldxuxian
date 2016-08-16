package com.xuxian.marketpro.activity.tab.shoppingcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbScreenUtils;
import com.ab.util.AbViewUtil;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.presentation.View.listview.ShoppingCarListView;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.db.ShoppingCartGoodsDb;
import com.xuxian.marketpro.presentation.db.UserDb;

/**
 * Created by youarenotin on 16/8/2.
 */
public class ShoppingCartFragment extends SuperFragment{
    private Button btn_shopping_cart_delete;
    private Button btn_shopping_cart_settlement;
    private CheckBox cb_all_selected;
    private ActivityStateView emptyview_state;
    private LinearLayout footerview_linearLayout;
    private boolean isLoading;
    private LinearLayout ll_default_shopping_car_img;
    private final String mPageName = "ShoppingCartFragment";
    private IHttpResponseCallBack<NewCartGoodsEntity> newCartGoodsIHttpResponseCallBack;
    private RelativeLayout rl_shopping_cart_delete;
    private RelativeLayout rl_shopping_cart_settlement;
    private RelativeLayout rl_shopping_cart_store;
    private int screenWidth;
    private ShoppingCarAdapter shoppingCarAdapter;
    private ShoppingCarListView shoppingCarListView;
    private ShoppingCartGoodsDb shoppingCartGoodsDb;
    private TextView shopping_cart_always_price;
    private IHttpResponseCallBack<SureOrderEntity> sureOrderHttpResponseCallBack;
    private TextView tv_shopping_car_massage;
    private TextView tv_shopping_cart_store;
    private UserDb userDb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_cart_layout, null);
        initTitleBar();
        initFindViewById(view);
        setListener();
        init();
        return view ;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTitleBar() {
        if (getTitle_bar() == null) {
            titleBar();
            setTitle("购物车");
            setTitleLeftViewShow(false);
            setTitleRightViewShow(true);
            setTitleRightText("编辑");
            getSherlockActivity().getSupportActionBar().setCustomView(getTitle_bar());
            return;
        }
        setCustomView(getTitle_bar());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            initTitleBar();
        }
    }

    @Override
    protected void initFindViewById(View view) {
        this.emptyview_state = (ActivityStateView) view.findViewById(R.id.emptyview_state);
        this.ll_default_shopping_car_img = (LinearLayout) view.findViewById(R.id.ll_default_shopping_car_img);
        this.tv_shopping_car_massage = (TextView) view.findViewById(R.id.tv_shopping_car_massage);
        this.shoppingCarListView = (ShoppingCarListView) view.findViewById(R.id.list);
        this.shopping_cart_always_price = (TextView) view.findViewById(R.id.shopping_cart_always_price);
        this.btn_shopping_cart_settlement = (Button) view.findViewById(R.id.btn_shopping_cart_settlement);
        this.screenWidth = AbScreenUtils.getScreenWidth(getActivity());
        AbViewUtil.setViewWH(this.btn_shopping_cart_settlement, this.screenWidth / 4, AbViewUtil.getViewHeight(this.btn_shopping_cart_settlement));
        this.rl_shopping_cart_settlement = (RelativeLayout) view.findViewById(R.id.rl_shopping_cart_settlement);
        this.rl_shopping_cart_delete = (RelativeLayout) view.findViewById(R.id.rl_shopping_cart_delete);
        this.btn_shopping_cart_delete = (Button) view.findViewById(R.id.btn_shopping_cart_delete);
        this.cb_all_selected = (CheckBox) view.findViewById(R.id.cb_all_selected);
        this.rl_shopping_cart_delete.setVisibility(4);
        AbViewUtil.setViewWH(this.btn_shopping_cart_delete, this.screenWidth / 4, AbViewUtil.getViewHeight(this.btn_shopping_cart_settlement));
        this.btn_shopping_cart_settlement.setEnabled(false);
        this.rl_shopping_cart_store = (RelativeLayout) view.findViewById(R.id.rl_shopping_cart_store);
        this.tv_shopping_cart_store = (TextView) view.findViewById(R.id.tv_shopping_cart_store);
        this.rl_shopping_cart_store.setBackgroundResource(R.drawable.btn_store_selector);
    }

    @Override
    protected void setListener() {

    }
}
