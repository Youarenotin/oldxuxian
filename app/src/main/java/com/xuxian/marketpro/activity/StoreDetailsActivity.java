package com.xuxian.marketpro.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.ab.util.AbStrUtil;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.presentation.db.ShoppingCartGoodsDb;
import com.xuxian.marketpro.presentation.db.StoreDb;
import com.xuxian.marketpro.presentation.entity.StoreEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 作者：lubo on 8/1 0001 09:49
 * 邮箱：lubo_wen@126.com
 */
public class StoreDetailsActivity extends SuperSherlockActivity implements View.OnClickListener {
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

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void init() {
        try {
            this.shoppingCartGoodsDb = new ShoppingCartGoodsDb(getActivity());
            this.storeDb = new StoreDb(getActivity());
            this.storeEntity = (StoreEntity) getIntent().getBundleExtra(SuperSherlockActivity.INTENT_BUNDLE).get(SuperSherlockActivity.INTENT_OBJECT);
            if (storeEntity != null) {
                this.tv_shop_details_name.setText(storeEntity.getTitle());
                if (storeEntity.getStore_status() == 1) {//即将开业
                    this.iv_shop_details_state.setVisibility(View.VISIBLE);
                    this.iv_shop_details_state.setBackgroundResource(R.drawable.jijiangkaiye_icon);
                } else if (storeEntity.getStore_status() == 2) {
                    this.iv_shop_details_state.setVisibility(View.VISIBLE);
                    this.iv_shop_details_state.setBackgroundResource(R.drawable.zhuangxiu_icon);
                } else if (storeEntity.getStore_status() == 3) {
                    this.iv_shop_details_state.setVisibility(View.VISIBLE);
                    this.iv_shop_details_state.setBackgroundResource(R.drawable.xindian_icon);
                } else {
                    this.iv_shop_details_state.setVisibility(View.GONE);
                }
                String[] bdate = storeEntity.getBdate().split(",");
                String data = "";
                if (bdate[0].equals("1") && bdate[1].equals("0")) {
                    data = "周一到周五";
                    this.tv_shop_details_business_date.setText(data);
                    if (!AbStrUtil.isEmpty(storeEntity.getBstarttime())) {
                        this.tv_shop_details_business_time.setText(storeEntity.getBstarttime());
                    }
                    if (AbStrUtil.isEmpty(storeEntity.getStarttime())) {
                        this.tv_shop_details_opening_time.setVisibility(View.GONE);
                        this.tv_shop_details_delivery_time.setVisibility(View.GONE);
                    } else if (new SimpleDateFormat(AbDateUtil.dateFormatYMD).parse(storeEntity.getStarttime()).getTime() < System.currentTimeMillis()) {
                        this.tv_shop_details_opening_time.setVisibility(View.GONE);
                        this.tv_shop_details_delivery_time.setVisibility(View.GONE);
                    } else {
                        this.tv_shop_details_opening_time.setVisibility(View.VISIBLE);
                        this.tv_shop_details_opening_date.setText(storeEntity.getStarttime() + "开业");
                    }

                    if (!AbStrUtil.isEmpty(storeEntity.getFristtime())) {
                        this.tv_shop_details_delivery_time.setVisibility(View.VISIBLE);
                        this.tv_shop_details_delivery_time.setText(storeEntity.getFristtime() + "可提");
                    }
                    if (!AbStrUtil.isEmpty(storeEntity.getArea())) {
                        this.tv_shop_details_delivery_address.setText(storeEntity.getArea());
                    }
                    if (!AbStrUtil.isEmpty(storeEntity.getDistance())) {
                        this.tv_shop_details_distance.setText(storeEntity.getDistance());
                    }
                    if (!AbStrUtil.isEmpty(storeEntity.getMessage_alert())) {
                        this.tv_shop_details_message.setText(storeEntity.getMessage_alert());
                    }
                    this.ll_business.setVisibility(View.VISIBLE);
                    this.ll_opening.setVisibility(View.GONE);
                    this.tv_shop_details_business_date.setTextColor(Color.parseColor(getString(R.color.red)));
                    this.tv_shop_details_business_time.setTextColor(Color.parseColor(getString(R.color.red)));
                    this.iv_shop_type.setImageResource(R.drawable.xue_xiao);
                    this.tv_shop_type.setText("学校");
                } else if (bdate[0].equals("1") && bdate[1].equals("2")) {
                    String date = "周一到周二";
                    this.tv_shop_details_business_date.setText(date);
                    if (AbStrUtil.isEmpty(this.storeEntity.getBstarttime())) {
                        this.tv_shop_details_business_time.setText(this.storeEntity.getBstarttime());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getStarttime())) {
                        this.tv_shop_details_opening_time.setVisibility(8);
                        this.tv_shop_details_delivery_time.setVisibility(8);
                    } else if (new SimpleDateFormat(AbDateUtil.dateFormatYMD).parse(this.storeEntity.getStarttime()).getTime() < System.currentTimeMillis()) {
                        this.tv_shop_details_opening_time.setVisibility(0);
                        this.tv_shop_details_opening_date.setText(this.storeEntity.getStarttime() + "开业");
                    } else {
                        this.tv_shop_details_opening_time.setVisibility(8);
                        this.tv_shop_details_delivery_time.setVisibility(8);
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getFristtime())) {
                        this.tv_shop_details_delivery_time.setVisibility(0);
                        this.tv_shop_details_delivery_time.setText(this.storeEntity.getFristtime() + "可提");
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getArea())) {
                        this.tv_shop_details_delivery_address.setText(this.storeEntity.getArea());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getDistance())) {
                        this.tv_shop_details_distance.setText(this.storeEntity.getTelphone());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getMessage_alert())) {
                        this.tv_shop_details_message.setText(this.storeEntity.getMessage_alert());
                    }
                    this.ll_business.setVisibility(0);
                    this.ll_opening.setVisibility(8);
                    this.tv_shop_details_business_date.setTextColor(Color.parseColor(getString(R.color.red)));
                    this.tv_shop_details_business_time.setTextColor(Color.parseColor(getString(R.color.red)));
                    if (this.storeEntity.getType() != 0) {
                    }
                    this.iv_shop_type.setImageResource(R.drawable.xue_xiao);
                    this.tv_shop_type.setText("学校");
                } else if (bdate[0].equals("1") && bdate[1].equals("r3")) {
                    String date = "周一到周三";
                    this.tv_shop_details_business_date.setText(date);
                    if (AbStrUtil.isEmpty(this.storeEntity.getBstarttime())) {
                        this.tv_shop_details_business_time.setText(this.storeEntity.getBstarttime());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getStarttime())) {
                        this.tv_shop_details_opening_time.setVisibility(8);
                        this.tv_shop_details_delivery_time.setVisibility(8);
                    } else if (new SimpleDateFormat(AbDateUtil.dateFormatYMD).parse(this.storeEntity.getStarttime()).getTime() < System.currentTimeMillis()) {
                        this.tv_shop_details_opening_time.setVisibility(8);
                        this.tv_shop_details_delivery_time.setVisibility(8);
                    } else {
                        this.tv_shop_details_opening_time.setVisibility(0);
                        this.tv_shop_details_opening_date.setText(this.storeEntity.getStarttime() + "开业");
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getFristtime())) {
                        this.tv_shop_details_delivery_time.setVisibility(0);
                        this.tv_shop_details_delivery_time.setText(this.storeEntity.getFristtime() + "可提");
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getArea())) {
                        this.tv_shop_details_delivery_address.setText(this.storeEntity.getArea());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getDistance())) {
                        this.tv_shop_details_distance.setText(this.storeEntity.getTelphone());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getMessage_alert())) {
                        this.tv_shop_details_message.setText(this.storeEntity.getMessage_alert());
                    }
                    this.ll_business.setVisibility(0);
                    this.ll_opening.setVisibility(8);
                    this.tv_shop_details_business_date.setTextColor(Color.parseColor(getString(R.color.red)));
                    this.tv_shop_details_business_time.setTextColor(Color.parseColor(getString(R.color.red)));
                    if (this.storeEntity.getType() != 0) {
                    }
                    this.iv_shop_type.setImageResource(R.drawable.xue_xiao);
                    this.tv_shop_type.setText("学校");
                } else if (bdate[0].equals("1") && bdate[1].equals("4")) {
                    String date = "周一到周四";
                    this.tv_shop_details_business_date.setText(date);
                    if (AbStrUtil.isEmpty(this.storeEntity.getBstarttime())) {
                        this.tv_shop_details_business_time.setText(this.storeEntity.getBstarttime());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getStarttime())) {
                        this.tv_shop_details_opening_time.setVisibility(8);
                        this.tv_shop_details_delivery_time.setVisibility(8);
                    } else if (new SimpleDateFormat(AbDateUtil.dateFormatYMD).parse(this.storeEntity.getStarttime()).getTime() < System.currentTimeMillis()) {
                        this.tv_shop_details_opening_time.setVisibility(0);
                        this.tv_shop_details_opening_date.setText(this.storeEntity.getStarttime() + "开业");
                    } else {
                        this.tv_shop_details_opening_time.setVisibility(8);
                        this.tv_shop_details_delivery_time.setVisibility(8);
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getFristtime())) {
                        this.tv_shop_details_delivery_time.setVisibility(0);
                        this.tv_shop_details_delivery_time.setText(this.storeEntity.getFristtime() + "可提");
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getArea())) {
                        this.tv_shop_details_delivery_address.setText(this.storeEntity.getArea());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getDistance())) {
                        this.tv_shop_details_distance.setText(this.storeEntity.getTelphone());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getMessage_alert())) {
                        this.tv_shop_details_message.setText(this.storeEntity.getMessage_alert());
                    }
                    this.ll_business.setVisibility(0);
                    this.ll_opening.setVisibility(8);
                    this.tv_shop_details_business_date.setTextColor(Color.parseColor(getString(R.color.red)));
                    this.tv_shop_details_business_time.setTextColor(Color.parseColor(getString(R.color.red)));
                    if (this.storeEntity.getType() != 0) {
                    }
                    this.iv_shop_type.setImageResource(R.drawable.xue_xiao);
                    this.tv_shop_type.setText("学校");
                } else if (bdate[0].equals("1") && bdate[1].equals("5")) {
                    String date = "周一到周五";
                    this.tv_shop_details_business_date.setText(date);
                    if (AbStrUtil.isEmpty(this.storeEntity.getBstarttime())) {
                        this.tv_shop_details_business_time.setText(this.storeEntity.getBstarttime());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getStarttime())) {
                        this.tv_shop_details_opening_time.setVisibility(8);
                        this.tv_shop_details_delivery_time.setVisibility(8);
                    } else if (new SimpleDateFormat(AbDateUtil.dateFormatYMD).parse(this.storeEntity.getStarttime()).getTime() < System.currentTimeMillis()) {
                        this.tv_shop_details_opening_time.setVisibility(8);
                        this.tv_shop_details_delivery_time.setVisibility(8);
                    } else {
                        this.tv_shop_details_opening_time.setVisibility(0);
                        this.tv_shop_details_opening_date.setText(this.storeEntity.getStarttime() + "开业");
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getFristtime())) {
                        this.tv_shop_details_delivery_time.setVisibility(0);
                        this.tv_shop_details_delivery_time.setText(this.storeEntity.getFristtime() + "可提");
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getArea())) {
                        this.tv_shop_details_delivery_address.setText(this.storeEntity.getArea());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getDistance())) {
                        this.tv_shop_details_distance.setText(this.storeEntity.getTelphone());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getMessage_alert())) {
                        this.tv_shop_details_message.setText(this.storeEntity.getMessage_alert());
                    }
                    this.ll_business.setVisibility(0);
                    this.ll_opening.setVisibility(8);
                    this.tv_shop_details_business_date.setTextColor(Color.parseColor(getString(R.color.red)));
                    this.tv_shop_details_business_time.setTextColor(Color.parseColor(getString(R.color.red)));
                    if (this.storeEntity.getType() != 0) {
                    }
                    this.iv_shop_type.setImageResource(R.drawable.xue_xiao);
                    this.tv_shop_type.setText("\u5b66\u6821");
                } else {
                    String date = null;
                    if (bdate[0].equals("1") && bdate[1].equals("6")) {
                        date = "周一到周六";
                    }
                    this.tv_shop_details_business_date.setText(date);
                    if (AbStrUtil.isEmpty(this.storeEntity.getBstarttime())) {
                        this.tv_shop_details_business_time.setText(this.storeEntity.getBstarttime());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getStarttime())) {
                        try {
                            if (new SimpleDateFormat(AbDateUtil.dateFormatYMD).parse(this.storeEntity.getStarttime()).getTime() < System.currentTimeMillis()) {
                                this.tv_shop_details_opening_time.setVisibility(0);
                                this.tv_shop_details_opening_date.setText(this.storeEntity.getStarttime() + "开业");
                            } else {
                                this.tv_shop_details_opening_time.setVisibility(8);
                                this.tv_shop_details_delivery_time.setVisibility(8);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        this.tv_shop_details_opening_time.setVisibility(8);
                        this.tv_shop_details_delivery_time.setVisibility(8);
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getFristtime())) {
                        this.tv_shop_details_delivery_time.setVisibility(0);
                        this.tv_shop_details_delivery_time.setText(this.storeEntity.getFristtime() + "可提");
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getArea())) {
                        this.tv_shop_details_delivery_address.setText(this.storeEntity.getArea());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getDistance())) {
                        this.tv_shop_details_distance.setText(this.storeEntity.getTelphone());
                    }
                    if (AbStrUtil.isEmpty(this.storeEntity.getMessage_alert())) {
                        this.tv_shop_details_message.setText(this.storeEntity.getMessage_alert());
                    }
                    if (this.storeEntity.getStore_status() == 0 || this.storeEntity.getStore_status() == 3) {
                        this.ll_business.setVisibility(0);
                        this.ll_opening.setVisibility(8);
                        this.tv_shop_details_business_date.setTextColor(Color.parseColor(getString(R.color.red)));
                        this.tv_shop_details_business_time.setTextColor(Color.parseColor(getString(R.color.red)));
                    }
                    if (this.storeEntity.getType() != 0 || this.storeEntity.getType() == 1) {
                        this.iv_shop_type.setImageResource(R.drawable.xue_xiao);
                        this.tv_shop_type.setText("学校");
                    } else if (this.storeEntity.getType() == 2) {
                        this.iv_shop_type.setImageResource(R.drawable.shang_quan);
                        this.tv_shop_type.setText("商圈");
                    }

                }
            } else{//storeEntity为空

            }
        } catch (ParseException e) {
            e.printStackTrace();
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
