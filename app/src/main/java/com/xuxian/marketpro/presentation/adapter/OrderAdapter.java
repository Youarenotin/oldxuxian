package com.xuxian.marketpro.presentation.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.ab.util.AbStrUtil;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.presentation.entity.OrderEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/9/1.
 */
public class OrderAdapter extends BaseAdapter {
    List<OrderEntity> mListData;
    Context mContext;

    public OrderAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        if (mListData == null || mListData.isEmpty())
            return 0;
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_order_layout, null);
            holder.order_number_title = (TextView) convertView.findViewById(R.id.order_number_title);
            holder.phone_number_title = (TextView) convertView.findViewById(R.id.phone_number_title);
            holder.time_title = (TextView) convertView.findViewById(R.id.time_title);
            holder.llOlderStatus = (LinearLayout) convertView.findViewById(R.id.llayout_order_pay_status);
            holder.indent_price_text = (TextView) convertView.findViewById(R.id.indent_price_text);
            holder.indent_price_time = (ImageView) convertView.findViewById(R.id.indent_price_time);
            holder.tv_order_status = (TextView) convertView.findViewById(R.id.tv_order_status);
            holder.rl_orderdetail_express = (RelativeLayout) convertView.findViewById(R.id.rl_orderdetail_express);
            holder.ll_orderDetail_unDistribution = (LinearLayout) convertView.findViewById(R.id.ll_orderDetail_unDistribution);
            holder.ll_orderDetail_pickUp = (LinearLayout) convertView.findViewById(R.id.ll_orderDetail_pickUp);
            holder.ll_orderDetail_expressing = (LinearLayout) convertView.findViewById(R.id.ll_orderDetail_expressing);
            holder.ll_orderDetail_arrive = (LinearLayout) convertView.findViewById(R.id.ll_orderDetail_arrive);
            holder.view_orderDetail_unDistribution = convertView.findViewById(R.id.view_orderDetail_unDistribution);
            holder.view_orderDetail_pickUp1 = convertView.findViewById(R.id.view_orderDetail_pickUp1);
            holder.view_orderDetail_pickUp = convertView.findViewById(R.id.view_orderDetail_pickUp);
            holder.view_orderDetail_expressing1 = convertView.findViewById(R.id.view_orderDetail_expressing1);
            holder.view_orderDetail_expressing = convertView.findViewById(R.id.view_orderDetail_expressing);
            holder.view_orderDetail_arrive = convertView.findViewById(R.id.view_orderDetail_arrive);
            holder.tv_orderDetail_unDistribution = (TextView) convertView.findViewById(R.id.tv_orderDetail_unDistribution);
            holder.tv_orderDetail_pickUp = (TextView) convertView.findViewById(R.id.tv_orderDetail_pickUp);
            holder.tv_orderDetail_expressing = (TextView) convertView.findViewById(R.id.tv_orderDetail_expressing);
            holder.tv_orderDetail_arrive = (TextView) convertView.findViewById(R.id.tv_orderDetail_arrive);
            holder.tv_distribution_status = (TextView) convertView.findViewById(R.id.tv_distribution_status);
            holder.linearLayout1 = (LinearLayout) convertView.findViewById(R.id.linearLayout1);
            holder.ll_orderAdapter_express = (LinearLayout) convertView.findViewById(R.id.ll_orderAdapter_express);
            holder.rl_orderDetail_expressStatus = (RelativeLayout) convertView.findViewById(R.id.rl_orderDetail_expressStatus);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        OrderEntity orderEntity = this.mListData.get(position);//取出当前position数据
        if (AbStrUtil.isEmpty(orderEntity.getCode())) {//如果取货码为空
            holder.order_number_title.setText("提货码: 暂未支付");
        } else {
            holder.order_number_title.setText(this.mContext.getString(R.string.order_code_title, new Object[]{orderEntity.getCode()}));
        }
        if (orderEntity.getDistribution().equals("0")) {//自提--------------------------------------
            holder.phone_number_title.setText("配送方式:自提");
            holder.ll_orderAdapter_express.setVisibility(View.GONE);//整个配送那个ll不可见
            holder.tv_distribution_status.setVisibility(View.VISIBLE);
            holder.indent_price_text.setBackgroundResource(R.drawable.shopping_button);
            if (orderEntity.getDistribution_status() == 0) {//分发状态
                holder.tv_distribution_status.setText("提货状态: 未提货");
            } else {
                holder.tv_distribution_status.setText("提货状态: 已提货");
            }
            holder.order_number_title.setTextColor(this.mContext.getResources().getColorStateList(R.color.green));
        } else {//配送------------------------------------------------------
            if (orderEntity.getPay_status() == 1) {//如果支付了的
                holder.rl_orderDetail_expressStatus.setVisibility(0);//配送rl进度条显示
            } else if (orderEntity.getPay_status() == 2) {
                holder.rl_orderDetail_expressStatus.setVisibility(8);//配送rl进度条不显示
            } else {
                holder.rl_orderDetail_expressStatus.setVisibility(8);//配送rl进度条不显示
            }
            holder.phone_number_title.setText("配送方式:" + orderEntity.getDistribution_company());
            if (AbStrUtil.isEmpty(orderEntity.getCode())) {
                holder.tv_order_status.setText("配送状态: 未支付");
            } else {
                holder.tv_order_status.setText("配送状态: ");
            }
            holder.ll_orderAdapter_express.setVisibility(0);//整个配送ll可见
            holder.tv_distribution_status.setVisibility(8);
            holder.tv_order_status.setVisibility(0);
            holder.indent_price_text.setBackgroundResource(R.drawable.anniu);
            holder.order_number_title.setTextColor(this.mContext.getResources().getColorStateList(R.color.price_text_color));
            int send_status = orderEntity.getSend_status();
            if (send_status != 0) {
                if (send_status == 1) {//配送状态-->配货
                    holder.view_orderDetail_unDistribution.setBackgroundResource(R.color.green);//横线
                    holder.ll_orderDetail_unDistribution.setBackgroundResource(R.drawable.filled_circle_express_green);//文字
                    holder.tv_orderDetail_unDistribution.setTextColor(Color.parseColor(this.mContext.getString(R.color.green)));
                } else if (send_status == 2) {//配送状态-->取货
                    holder.view_orderDetail_unDistribution.setBackgroundResource(R.color.green);
                    holder.view_orderDetail_pickUp.setBackgroundResource(R.color.green);
                    holder.view_orderDetail_pickUp1.setBackgroundResource(R.color.green);
                    holder.ll_orderDetail_unDistribution.setBackgroundResource(R.drawable.filled_circle_express_green);
                    holder.tv_orderDetail_unDistribution.setTextColor(Color.parseColor(this.mContext.getString(R.color.green)));
                    holder.ll_orderDetail_pickUp.setBackgroundResource(R.drawable.filled_circle_express_green);
                    holder.tv_orderDetail_pickUp.setTextColor(Color.parseColor(this.mContext.getString(R.color.green)));
                } else if (send_status == 3) {//配货状态-->配送中
                    holder.view_orderDetail_unDistribution.setBackgroundResource(R.color.green);
                    holder.view_orderDetail_pickUp.setBackgroundResource(R.color.green);
                    holder.view_orderDetail_pickUp1.setBackgroundResource(R.color.green);
                    holder.view_orderDetail_expressing.setBackgroundResource(R.color.green);
                    holder.view_orderDetail_expressing1.setBackgroundResource(R.color.green);
                    holder.ll_orderDetail_unDistribution.setBackgroundResource(R.drawable.filled_circle_express_green);
                    holder.tv_orderDetail_unDistribution.setTextColor(Color.parseColor(this.mContext.getString(R.color.green)));
                    holder.ll_orderDetail_pickUp.setBackgroundResource(R.drawable.filled_circle_express_green);
                    holder.tv_orderDetail_pickUp.setTextColor(Color.parseColor(this.mContext.getString(R.color.green)));
                    holder.ll_orderDetail_expressing.setBackgroundResource(R.drawable.filled_circle_express_green);
                    holder.tv_orderDetail_expressing.setTextColor(Color.parseColor(this.mContext.getString(R.color.green)));
                } else if (send_status == 4) {//配送状态-->送达
                    holder.view_orderDetail_unDistribution.setBackgroundResource(R.color.green);
                    holder.view_orderDetail_pickUp.setBackgroundResource(R.color.green);
                    holder.view_orderDetail_pickUp1.setBackgroundResource(R.color.green);
                    holder.view_orderDetail_expressing.setBackgroundResource(R.color.green);
                    holder.view_orderDetail_expressing1.setBackgroundResource(R.color.green);
                    holder.view_orderDetail_arrive.setBackgroundResource(R.color.green);
                    holder.ll_orderDetail_unDistribution.setBackgroundResource(R.drawable.filled_circle_express_green);
                    holder.tv_orderDetail_unDistribution.setTextColor(Color.parseColor(this.mContext.getString(R.color.green)));
                    holder.ll_orderDetail_pickUp.setBackgroundResource(R.drawable.filled_circle_express_green);
                    holder.tv_orderDetail_pickUp.setTextColor(Color.parseColor(this.mContext.getString(R.color.green)));
                    holder.ll_orderDetail_expressing.setBackgroundResource(R.drawable.filled_circle_express_green);
                    holder.tv_orderDetail_expressing.setTextColor(Color.parseColor(this.mContext.getString(R.color.green)));
                    holder.ll_orderDetail_arrive.setBackgroundResource(R.drawable.filled_circle_express_green);
                    holder.tv_orderDetail_arrive.setTextColor(Color.parseColor(this.mContext.getString(R.color.green)));
                } else if (send_status == 5) {
                    holder.rl_orderDetail_expressStatus.setVisibility(8);//配送进度条不可见
                }
            }
        }
        holder.time_title.setText(orderEntity.getCreate_time());//订单生成时间
        //订单价格 ¥
        holder.indent_price_text.setText(this.mContext.getString(R.string.indent_price_text, new Object[]{orderEntity.getReal_amount()}));
        try {
            boolean isOutOfDate = false;
            isOutOfDate = AbDateUtil.isOlderOutOfDate(AbDateUtil.orderTime(orderEntity.getCreate_time()));//当时时间是否比订单生成日期+一天 大
            if (orderEntity.getPay_status() > 0) {
                if (orderEntity.getPay_status() == 1 && orderEntity.getOrderstatus() == 5 && !isOutOfDate) {
                    holder.indent_price_text.setAlpha(1.0f);
                } else if (orderEntity.getPay_status() == 2 && orderEntity.getOrderstatus() == 4 && !isOutOfDate) {
                    holder.indent_price_text.setAlpha(1.0f);
                } else {
                    holder.indent_price_text.setAlpha(0.5f);
                }
            } else if (orderEntity.getPay_status() != 0 || isOutOfDate) {
                holder.indent_price_text.setAlpha(0.5f);
            } else {
                holder.indent_price_text.setAlpha(1.0f);
            }
        } catch (Exception e) {
        }
        convertView.setId(position);
        convertView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityUtil.startOrderDetailsActivity(OrderAdapter.this.mContext, mListData.get(v.getId()));
            }
        });
        convertView.setBackgroundResource(R.drawable.light_yellow_selector);
        return convertView;
    }

    public void setData(List<OrderEntity> orderEntities) {
        mListData = orderEntities;
        notifyDataSetChanged();
    }

    public void addData(List<OrderEntity> orderEntities) {
        mListData.addAll(orderEntities);
        notifyDataSetChanged();

    }

    class ViewHolder {
        /**
         * 订单价格
         */
        TextView indent_price_text;
        /**
         * 订单
         */
        ImageView indent_price_time;
        LinearLayout linearLayout1;
        LinearLayout llOlderStatus;

        LinearLayout ll_orderAdapter_express;
        /**
         * 已送达点
         */
        LinearLayout ll_orderDetail_arrive;
        /**
         * 配送中点
         */
        LinearLayout ll_orderDetail_expressing;
        /**
         * 取货点
         */
        LinearLayout ll_orderDetail_pickUp;
        /**
         * 配货中点
         */
        LinearLayout ll_orderDetail_unDistribution;
        /**
         * 订单号
         */
        TextView order_number_title;
        /**
         * 电话号
         */
        TextView phone_number_title;
        RelativeLayout rl_orderDetail_expressStatus;
        RelativeLayout rl_orderdetail_express;
        TextView time_title;
        TextView title;
        TextView tv_distribution_status;
        TextView tv_orderDetail_arrive;
        TextView tv_orderDetail_expressing;
        TextView tv_orderDetail_pickUp;
        TextView tv_orderDetail_unDistribution;
        /**
         * 配送状态
         */
        TextView tv_order_status;
        /**
         * 送货横线
         */
        View view_orderDetail_arrive;
        /**
         * 配送中横线
         */
        View view_orderDetail_expressing;
        /**
         * 配送中横线
         */
        View view_orderDetail_expressing1;
        /**
         * 取货横线
         */
        View view_orderDetail_pickUp;
        /**
         * 取货横线
         */
        View view_orderDetail_pickUp1;
        /**
         * 配货横线
         */
        View view_orderDetail_unDistribution;
    }
}
