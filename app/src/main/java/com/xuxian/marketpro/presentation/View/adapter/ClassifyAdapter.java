package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xuxian.marketpro.presentation.entity.ClassifyEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/14.
 */
public class ClassifyAdapter extends BaseAdapter {
    private Context mContext;
    private List<ClassifyEntity> mDataList;

    public ClassifyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<ClassifyEntity> data){
        this.mDataList=data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDataList == null || mDataList.isEmpty())
            return 0;
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
