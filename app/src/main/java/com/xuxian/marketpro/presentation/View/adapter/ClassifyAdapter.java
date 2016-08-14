package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.presentation.application.MyApplication;
import com.xuxian.marketpro.presentation.entity.ClassifyEntity;

import java.security.interfaces.ECKey;
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
        ShoppingCarHolder holder;
        if (convertView==null){
            convertView=View.inflate(mContext, R.layout.classify_item,null);
            holder=new ShoppingCarHolder();
            holder.ItemIcon= (ImageView) convertView.findViewById(R.id.classify_item_icon);
            holder.ItemTitle= (TextView) convertView.findViewById(R.id.classify_item_title);
            convertView.setTag(holder);
        }
        else {
            holder= (ShoppingCarHolder) convertView.getTag();
        }
        holder.ItemTitle.setText(mDataList.get(position).getClassifyname());
        ImageLoader.getInstance().displayImage(mDataList.get(position).getIcon(),holder.ItemIcon, MyApplication.getInstance().getSampleOptions(R.drawable.default_qita));

        return convertView;
    }


    class ShoppingCarHolder {
        ImageView ItemIcon;
        TextView ItemTitle;

        ShoppingCarHolder() {
        }
    }
}
