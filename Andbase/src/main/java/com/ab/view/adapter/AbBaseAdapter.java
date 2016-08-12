package com.ab.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ab.view.holder.AbBaseHolder;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;

import java.util.List;

/**
 * Created by youarenotin on 16/8/11.
 */
public abstract class AbBaseAdapter<T> extends BaseAdapter {
    protected Context context;
    protected AbBaseHolder holder;
    protected List<T> mDatas;

    public AbBaseAdapter(Context context) {
        this.context = context;
    }

    public AbBaseAdapter(Context context, List<T> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    public abstract AbBaseHolder getHolder();


    public List<T> getData() {
        return mDatas;
    }

    public void setData(List<T> mDatas) {
        if (mDatas==null|| mDatas.isEmpty())
            return;
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void setAddData(List<T> Datas) {
        this.mDatas.addAll(Datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDatas == null || mDatas.isEmpty())
            return 0;
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        if (mDatas==null || mDatas.isEmpty())
            return null;
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            holder=getHolder();
        }else{
            holder= (AbBaseHolder) convertView.getTag();
        }
        holder.setPostion(position);
        holder.setData(mDatas.get(position));
        holder.setViewGroup(parent);
        return holder.getConvertView();
    }
}
