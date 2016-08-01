package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbStrUtil;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.presentation.db.ShoppingCartGoodsDb;
import com.xuxian.marketpro.presentation.db.StoreDb;
import com.xuxian.marketpro.presentation.entity.StoreEntity;

/**
 * 作者：lubo on 8/1 0001 09:49
 * 邮箱：lubo_wen@126.com
 */
public class StoreDetailsActivity extends SuperSherlockActivity implements View.OnClickListener{
    private ImageView iv_shop_details_state;
    private ImageView iv_shop_type;
    private LinearLayout ll_business;
    private Button ll_jinrudianmian;
    private LinearLayout ll_opening;
    private LinearLayout ll_shop_details_map;
    private LinearLayout ll_shop_telephone_num;
    private ShoppingCartGoodsDb shoppingCartGoodsDb;
    private StoreDb storeDb;
    private StoreEntity storeEntity;
    private TextView tv_shop_details_business_date;
    private TextView tv_shop_details_business_time;
    private TextView tv_shop_details_delivery_address;
    private TextView tv_shop_details_delivery_time;
    private TextView tv_shop_details_distance;
    private TextView tv_shop_details_message;
    private TextView tv_shop_details_name;
    private TextView tv_shop_details_opening_date;
    private TextView tv_shop_details_opening_time;
    private TextView tv_shop_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_site_details_layout);
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
        this.shoppingCartGoodsDb=new ShoppingCartGoodsDb(getActivity());
        this.storeDb=new StoreDb(getActivity());
        this.storeEntity= (StoreEntity) getIntent().getBundleExtra(SuperSherlockActivity.INTENT_BUNDLE).get(SuperSherlockActivity.INTENT_OBJECT);
        if (storeEntity!=null){
            this.tv_shop_details_name.setText(storeEntity.getTitle());
            if (storeEntity.getStore_status()==1){//即将开业
                this.iv_shop_details_state.setVisibility(View.VISIBLE);
                 this.iv_shop_details_state.setBackgroundResource(R.drawable.jijiangkaiye_icon);
            }else if (storeEntity.getStore_status()==2){
                this.iv_shop_details_state.setVisibility(View.VISIBLE);
                this.iv_shop_details_state.setBackgroundResource(R.drawable.zhuangxiu_icon);
            }else if (storeEntity.getStore_status()==3){
                this.iv_shop_details_state.setVisibility(View.VISIBLE);
                this.iv_shop_details_state.setBackgroundResource(R.drawable.xindian_icon);
            }else {
                this.iv_shop_details_state.setVisibility(View.GONE);
            }
            String[] bdate = storeEntity.getBdate().split(",");
            String data = "";
            if (bdate[0].equals("1") && bdate[1].equals("0")){
                data="周一到周五";
                this.tv_shop_details_business_date.setText(data);
                if (!AbStrUtil.isEmpty(storeEntity.getBstarttime())){
                    this.tv_shop_details_business_time.setText(storeEntity.getBstarttime());
                }
                if (AbStrUtil.isEmpty(storeEntity.getStarttime())){
                    this.tv_shop_details_opening_time.setVisibility(View.GONE);
                    this.tv_shop_details_delivery_time.setVisibility(View.GONE);
                } else if (){

                }
            }
        }
    }

    @Override
    protected void initTitleBar() {
        setTitle("店面详情");
    }

    @Override
    protected void initFindViewById() {
        this.tv_shop_details_name = (TextView) findViewById(R.id.tv_shop_details_name);
        this.iv_shop_details_state = (ImageView) findViewById(R.id.iv_shop_details_state);
        this.tv_shop_details_business_date = (TextView) findViewById(R.id.tv_shop_details_business_date);
        this.tv_shop_details_business_time = (TextView) findViewById(R.id.tv_shop_details_business_time);
        this.tv_shop_details_opening_date = (TextView) findViewById(R.id.tv_shop_details_opening_date);
        this.tv_shop_details_delivery_time = (TextView) findViewById(R.id.tv_shop_details_delivery_time);
        this.tv_shop_details_delivery_address = (TextView) findViewById(R.id.tv_shop_details_delivery_address);
        this.tv_shop_details_distance = (TextView) findViewById(R.id.tv_shop_telephone_num);
        this.ll_shop_telephone_num = (LinearLayout) findViewById(R.id.ll_shop_telephone_num);
        this.tv_shop_details_message = (TextView) findViewById(R.id.tv_shop_details_message);
        this.ll_shop_details_map = (LinearLayout) findViewById(R.id.ll_shop_details_map);
        this.tv_shop_details_opening_time = (TextView) findViewById(R.id.tv_shop_details_opening_time);
        this.ll_jinrudianmian = (Button) findViewById(R.id.ll_jinrudianmian);
        this.ll_business = (LinearLayout) findViewById(R.id.ll_business);
        this.ll_opening = (LinearLayout) findViewById(R.id.ll_opening);
        this.iv_shop_type = (ImageView) findViewById(R.id.iv_shop_type);
        this.tv_shop_type = (TextView) findViewById(R.id.tv_shop_type);
    }

    @Override
    protected void setListener() {
        this.ll_jinrudianmian.setOnClickListener(this);
        this.ll_shop_telephone_num.setOnClickListener(this);
        this.ll_shop_details_map.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
