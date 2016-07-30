package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.presentation.entity.GetStoreEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/7/27.
 */
public class AreaAdapter extends BaseAdapter {
    private String city_area;
    ViewHolder holder;
    private int initPosition;
    private LayoutInflater mInflater;
    private List<GetStoreEntity.DataBean.StoreInfoBean> mListData;

    class ViewHolder {
        TextView tv_area_name;

        ViewHolder() {
        }
    }

    public AreaAdapter(Context ct) {
        mInflater= LayoutInflater.from(ct);
    }

    public void setCity_area(String city_area) {
        this.city_area = city_area;
        notifyDataSetChanged();
    }

    public int getInitPosition() {
        return this.initPosition;
    }

    public void setInitPosition(int initPosition) {
        this.initPosition = initPosition;
        notifyDataSetChanged();
    }


    public String getCity_area() {
        return this.city_area;
    }

    public void setData(List<GetStoreEntity.DataBean.StoreInfoBean> list){
        this.mListData=list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mListData==null || mListData.isEmpty())
            return 0;
        return mListData.size();
    }

    @Override
    public GetStoreEntity.DataBean.StoreInfoBean getItem(int i) {
        return mListData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        GetStoreEntity.DataBean.StoreInfoBean storeInfoEntity = mListData.get(i);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.area_item, null);
            holder = new ViewHolder();
            holder.tv_area_name = (TextView) convertView.findViewById(R.id.tv_area_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_area_name.setText(storeInfoEntity.getArea_name());
        if (initPosition==i){
            holder.tv_area_name.setTextColor(Color.parseColor("#4ea661"));
            convertView.setBackgroundColor(Color.parseColor("FFFFFF"));
        }
        else{
            holder.tv_area_name.setTextColor(Color.parseColor("#b5b6b8"));
            convertView.setBackgroundColor(Color.parseColor("f3f3f3"));
        }
        return convertView;
    }
}
