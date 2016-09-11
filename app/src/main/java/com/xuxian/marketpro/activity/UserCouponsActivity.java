package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.libraries.util.monitor.UseCouponMonitor;
import com.xuxian.marketpro.presentation.View.widght.pop.OperationPopupWindow;
import com.xuxian.marketpro.presentation.adapter.CouponAdapter;
import com.xuxian.marketpro.presentation.adapter.CouponGoodsAdapter;
import com.xuxian.marketpro.presentation.db.GoodsListDb;
import com.xuxian.marketpro.presentation.entity.CouponEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/9/5.
 */
public class UserCouponsActivity extends SuperSherlockActivity {
    public static final String COUPON_ID = "coupon_id";
    public static final String INTENT_COUPON_ID = "coupon_id";
    public static final String INTENT_COUPON_LIST = "COUPON_LIST";
    private CouponAdapter couponAdapter;
    private int coupon_id;
    private GoodsListDb goodsListDb;
    private int index;
    private ListView lv_coupons;
    private OperationPopupWindow mWindow;
    private TextView tv_using_coupons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_layout);
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
        goodsListDb = new GoodsListDb(getActivity());
        coupon_id = getIntent().getExtras().getInt(INTENT_COUPON_ID);
        couponAdapter = new CouponAdapter(getActivity());
        lv_coupons.setAdapter(couponAdapter);
        couponAdapter.setType(2);
        couponAdapter.setCoupon_id(coupon_id);
        List<CouponEntity> couponEntities = (List) getIntent().getSerializableExtra(INTENT_COUPON_LIST);
        couponAdapter.setData(couponEntities);
        if (couponEntities != null && !couponEntities.isEmpty()) {
            int length = couponEntities.size();
            for (int i = 0; i < length; i++) {
                if (coupon_id == couponEntities.get(i).getId()) {
                    index = i;
                    return;
                }
            }
        }
    }

    @Override
    protected void initTitleBar() {
        setTitle("购物券");
        setTitleRightIconShow(true);
        setTitleRightText("确定使用");
        getTitleRightClick().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initFindViewById() {
        lv_coupons = (ListView) findViewById(R.id.lv_coupons);
        tv_using_coupons = (TextView) findViewById(R.id.tv_using_coupons);
        tv_using_coupons.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {
        lv_coupons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (couponAdapter.getData() != null && !couponAdapter.getData().isEmpty()) {
                    CouponEntity couponEntity = (CouponEntity) couponAdapter.getItem(position);
                    //该购物券所包含的商品如果为空
                    if (couponAdapter.getData().get(position).getGoods() == null || couponAdapter.getData().get(position).getGoods().isEmpty()) {
                        if (couponAdapter.getCoupon_id() == couponEntity.getId()) {
                            index = -1;
                            UseCouponMonitor.getInstance().IssuedMonitor(null);
                            couponAdapter.setCoupon_id(0);
                        } else {
                            index = position;
                            couponAdapter.setCoupon_id(couponEntity.getId());
                        }
                        couponAdapter.notifyDataSetChanged();
                    } else if (couponAdapter.getCoupon_id() == couponEntity.getId()) {
                        index = -1;
                        UseCouponMonitor.getInstance().IssuedMonitor(null);
                        couponAdapter.setCoupon_id(0);
                        couponAdapter.notifyDataSetChanged();
                    } else {//查看该优惠券里包含的商品
                        initPopupWindow(couponAdapter.getData().get(position).getGoods(), position);
                        if (mWindow != null && !mWindow.isShowing()) {
                            mWindow.showAtLocation(view, Gravity.CENTER, 10, 10);
                        }
                    }
                }
            }
        });
    }

    private void initPopupWindow(List<CouponEntity.GoodsEntity> goods, int position) {
        View view = View.inflate(this, R.layout.pop_coupons_goods_layout, null);
        ListView lv_coupons_goods = (ListView) view.findViewById(R.id.lv_coupons_goods);
        lv_coupons_goods.setAdapter(new CouponGoodsAdapter(goods, getActivity()));
        lv_coupons_goods.setOnItemClickListener(new CouponsGoodsOnClickListener(goods, position));
        this.mWindow = new OperationPopupWindow(view, getActivity(), -1, -1, true);
        view.setOnTouchListener(new CouponsPopOnClickListener(view));

    }

    private class CouponsPopOnClickListener implements View.OnTouchListener {
        private View view;

        public CouponsPopOnClickListener(View view) {
            this.view = view;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int top = view.getTop();
            int bottom = view.getBottom();
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getY() < top || event.getY() > bottom)
                    mWindow.dismiss();//popupWindow消失
            }
            return true;
        }
    }

    private class CouponsGoodsOnClickListener implements AdapterView.OnItemClickListener {
        private List<CouponEntity.GoodsEntity> goods;
        private int position;

        public CouponsGoodsOnClickListener(List<CouponEntity.GoodsEntity> goods, int position) {
            this.goods = goods;
            this.position = position;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CouponEntity couponEntity = (CouponEntity) couponAdapter.getItem(this.position);//获得该优惠券实体内容
            couponEntity.setGoods_id(this.goods.get(position).getId());
            couponAdapter.setCoupon_id(couponEntity.getId());
            couponAdapter.notifyDataSetChanged();
            index = this.position;
            if (mWindow != null && mWindow.isShowing()) {
                mWindow.dismiss();
            }
        }
    }

}
