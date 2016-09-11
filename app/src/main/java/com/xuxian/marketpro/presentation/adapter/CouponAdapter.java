package com.xuxian.marketpro.presentation.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.ab.util.AbStrUtil;
import com.ab.view.holder.AbBaseHolder;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.libraries.util.Tools;
import com.xuxian.marketpro.presentation.entity.CouponEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/9/5.
 */
public class CouponAdapter extends BaseAdapter {
    private int coupon_id;
    private int type;

    private List<CouponEntity> datas;

    public CouponAdapter(Context context) {

    }

    @Override
    public int getCount() {
        if (datas == null || datas.isEmpty())
            return 0;
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCoupon_id() {
        return this.coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }


    public void setData(List<CouponEntity> couponEntities) {
        this.datas = couponEntities;
    }

    public List<CouponEntity> getData() {
        return datas;
    }

    class CouponsHolder extends AbBaseHolder{
        ImageView iv_coupon_state;
        LinearLayout ll_coupon_bg;
        TextView tv_conpon_typename;
        TextView tv_coupon_endtime;
        TextView tv_coupon_introduction;
        TextView tv_coupon_showname;
        TextView tv_coupon_use_one;
        TextView tv_money_icon;

        public CouponsHolder(Context context) {
            super(context);
        }

        @Override
        protected View initView() {
            View view =View.inflate(context,R.layout.item_mycoupon_layout,null);
            this.ll_coupon_bg = (LinearLayout) view.findViewById(R.id.ll_coupon_bg);
            this.tv_money_icon = (TextView) view.findViewById(R.id.tv_money_icon);
            this.tv_coupon_showname = (TextView) view.findViewById(R.id.tv_coupon_showname);
            this.tv_conpon_typename = (TextView) view.findViewById(R.id.tv_conpon_typename);
            this.tv_coupon_endtime = (TextView) view.findViewById(R.id.tv_coupon_endtime);
            this.tv_coupon_use_one = (TextView) view.findViewById(R.id.tv_coupon_use_one);
            this.iv_coupon_state = (ImageView) view.findViewById(R.id.iv_coupon_state);
//            this.iv_coupon_select = (ImageView) view.findViewById(R.id.iv_coupon_select);
//            this.tv_mycoupon_cname = (TextView) view.findViewById(R.id.tv_mycoupon_cname);
//            this.tv_mycoupon_typename = (TextView) view.findViewById(R.id.tv_mycoupon_typename);
            return view;
        }

        @Override
        protected void refreshView() {
            if (!AbStrUtil.isEmpty(((CouponEntity) this.data).getShowname())) {//如果名称不为空
                if (!Tools.isNumeric(((CouponEntity) this.data).getShowname()) || ((CouponEntity) this.data).getPay_value() <= 0.0d) {
                   //如果名称是数字或者支付金额<=0
                    this.tv_money_icon.setVisibility(4);
                    this.tv_conpon_typename.setVisibility(4);
                    this.tv_coupon_showname.setText(((CouponEntity) this.data).getShowname());
                    this.tv_coupon_showname.setTextSize(30);
                } else {
                    //正常
                    this.tv_coupon_showname.setText(((CouponEntity) this.data).getShowname());
                    this.tv_money_icon.setTextSize(10.0f);
                    this.tv_coupon_showname.setTextSize(30);
                    this.tv_money_icon.setVisibility(0);
                    this.tv_conpon_typename.setVisibility(0);
                }
            }
            //优惠券类型
            this.tv_coupon_introduction.setText(((CouponEntity) this.data).getTypename());
            if (((CouponEntity) this.data).getCtype() == 1 || ((CouponEntity) this.data).getCtype() == 5) {
                this.ll_coupon_bg.setBackgroundResource(R.drawable.coupon_yellow_bg);
            } else if (((CouponEntity) this.data).getCtype() == 4) {
                this.ll_coupon_bg.setBackgroundResource(R.drawable.coupon_orange_bg);
            } else if (((CouponEntity) this.data).getCtype() == 6 || ((CouponEntity) this.data).getCtype() == 2 || ((CouponEntity) this.data).getCtype() == 3) {
                this.ll_coupon_bg.setBackgroundResource(R.drawable.coupon_red_bg);
            } else if (((CouponEntity) this.data).getCtype() == 7) {
                this.ll_coupon_bg.setBackgroundResource(R.drawable.coupon_green_bg);
                ((CouponEntity) this.data).setPay_value(0.0d);
            }
            if (type == 2) {
                if (((CouponEntity) this.data).getId() != CouponAdapter.this.coupon_id) {
                    getConvertView().setBackgroundColor(Color.parseColor(this.context.getString(R.color.white)));
                } else if (((CouponEntity) this.data).getCtype() == 1 || ((CouponEntity) this.data).getCtype() == 5) {
                    getConvertView().setBackgroundColor(Color.parseColor("#E0E354"));
                } else if (((CouponEntity) this.data).getCtype() == 4) {
                    getConvertView().setBackgroundColor(Color.parseColor("#F9A243"));
                } else if (((CouponEntity) this.data).getCtype() == 6 || ((CouponEntity) this.data).getCtype() == 2 || ((CouponEntity) this.data).getCtype() == 3) {
                    getConvertView().setBackgroundColor(Color.parseColor("#F15D5B"));
                } else if (((CouponEntity) this.data).getCtype() == 7) {
                    getConvertView().setBackgroundColor(Color.parseColor("#1F9F86"));
                }
            }
            long time = AbDateUtil.getDateByFormat(((CouponEntity) this.data).getEndtime(), AbDateUtil.dateFormatYMDHMS).getTime();
            long current_time = System.currentTimeMillis();
            if (((CouponEntity) this.data).getUse_one() == 1) {
                this.iv_coupon_state.setVisibility(0);
                this.tv_coupon_use_one.setText("已使用");
                this.ll_coupon_bg.setBackgroundResource(R.drawable.coupon_gray_bg);
                this.iv_coupon_state.setImageResource(R.drawable.yishiyong);
            } else if (current_time > time) {
                this.iv_coupon_state.setVisibility(0);
                this.ll_coupon_bg.setBackgroundResource(R.drawable.coupon_gray_bg);
                this.iv_coupon_state.setImageResource(R.drawable.yiguoqi);
                    this.tv_coupon_use_one.setText("已过期");
            } else {
                this.iv_coupon_state.setVisibility(View.INVISIBLE);
                this.tv_coupon_use_one.setText("未使用");
            }
            this.tv_coupon_endtime.setText("有效期至 " + AbDateUtil.getStringByFormat(time, AbDateUtil.dateFormatYMD));
        }
    }
}
