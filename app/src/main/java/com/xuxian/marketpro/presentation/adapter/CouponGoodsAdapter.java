package com.xuxian.marketpro.presentation.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.xuxian.marketpro.presentation.entity.CouponEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/9/6.
 */
public class CouponGoodsAdapter extends BaseAdapter {

    public CouponGoodsAdapter(List<CouponEntity.GoodsEntity> goods, Activity activity) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
