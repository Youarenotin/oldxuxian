package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.presentation.entity.CityEntity;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by youarenotin on 16/7/25.
 */
public class CityListAdapter extends BaseAdapter implements SectionIndexer{
    private List<CityEntity.DataEntity.CityInfoEntity> cityList;
    private Context mContext ;
    public CityListAdapter(Context ct) {
        this.mContext=ct;
    }

    @Override
    public int getCount() {
        if (this.cityList==null || this.cityList.isEmpty())
            return 0;
        return cityList.size();
    }

    @Override
    public Object getItem(int i) {
        return cityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        CityEntity.DataEntity.CityInfoEntity cityInfoEntity = cityList.get(position);
        if (view==null){
            holder =new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.city_list_item,null);
            holder.cityLetter=(TextView)view.findViewById(R.id.city_letter);
            holder.cityName= (TextView) view.findViewById(R.id.city_name);
            holder.city_pinyin_name= (TextView) view.findViewById(R.id.city_pinyin_name);
            view.setTag(holder);
        }
        else{
            holder= (ViewHolder) view.getTag();
        }
        if (position ==getPositionForSection(getSectionForPosition(position))){
            holder.cityLetter.setVisibility(View.VISIBLE);
            holder.cityLetter.setText(cityInfoEntity.getFirstLetter());
        }
        else {
            holder.cityLetter.setVisibility(View.GONE);
        }
        holder.cityName.setText(cityInfoEntity.getCity_name());
        return view;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int i) {

        return 0;
    }

    @Override
    public int getSectionForPosition(int i) {
        return cityList.get(i).getFirstLetter().toUpperCase()
    }

    private static class ViewHolder{
        TextView cityLetter;
        TextView cityName;
        TextView city_pinyin_name;
    }
}
