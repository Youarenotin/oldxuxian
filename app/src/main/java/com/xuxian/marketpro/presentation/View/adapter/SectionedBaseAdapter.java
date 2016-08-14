package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xuxian.marketpro.presentation.View.listview.PinnedSectionListView;
import com.xuxian.marketpro.presentation.View.listview.PinnedSectionListView.PinnedSectionListAdapter;

/**
 * Created by youarenotin on 16/8/9.
 */
public abstract class SectionedBaseAdapter extends BaseAdapter implements HeaderBaseAdapter {
    private Context mContext;
    private static int HEADER_VIEW_TYPE = 0;
    private static int ITEM_VIEW_TYPE = 0;
    int mCount = -1;
    private SparseArray<Integer> mSectionCache = new SparseArray<>();
    private int mSectionCount = -1;
    private SparseArray<Integer> mSectionCountCache = new SparseArray<>();
    private SparseArray<Integer> mSectionPostionCache = new SparseArray<>();

    public abstract int getCountForSection(int i);

    public abstract Object getItem(int section, int postion);

    public abstract long getItemId(int section, int postion);

    public abstract View getItemView(int section, int postion, View view, ViewGroup viewGroup);

    public abstract int getSectionCount();

    public abstract View getSectionHeaderView(int section, View view, ViewGroup viewGroup);


    @Override
    public int getCount() {
        if (mCount>=0){
            return mCount;
        }
        int count=0;
        for (int i=0; i<internalGetSectionCount();i++){
            count+=internalGetCountForSection(i)+1;
        }
        mCount=count;
        return mCount;
    }

    @Override
    public Object getItem(int position) {
        return getItem(getSectionForPositon(position),getPositonInSectionForPosition(position));
    }

    @Override
    public long getItemId(int position) {
        return getItemId(getSectionForPositon(position),getPositonInSectionForPosition(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("position",position+"");
       if (isSectionHeader(position)){
           return getSectionHeaderView(getSectionForPositon(position),convertView,parent);
       }
        return getItemView(getSectionForPositon(position),getPositonInSectionForPosition(position),convertView,parent);
    }

    @Override
    public void notifyDataSetChanged() {
        mSectionCache.clear();
        mSectionCountCache.clear();
        mSectionPostionCache.clear();
        mCount = -1;
        mSectionCount = -1;
        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetInvalidated() {
        mSectionCache.clear();
        mSectionCountCache.clear();
        mSectionPostionCache.clear();
        mCount = -1;
        mSectionCount = -1;
        super.notifyDataSetInvalidated();
    }

    @Override
    public int getSectionForPositon(int position) {
        Integer cachedSection = mSectionCache.get(position);
        if (cachedSection != null) {
            return cachedSection;
        }
        int sectionStart = 0, i = 0;
        while (i < internalGetSectionCount()) {
            int sectionEnd = sectionStart + internalGetCountForSection(i) + 1;
            if (position < sectionStart || position >= sectionEnd) {
                sectionStart = sectionEnd;
                i++;
            }else {
                mSectionCache.put(position,i);
                return i;
            }
        }
        return 0;
    }

    public int getPositonInSectionForPosition(int position) {
        Integer cachedPosition = mSectionPostionCache.get(position);
        if (cachedPosition != null)
            return cachedPosition;
        int sectionStart = 0, i = 0;
        while (i < internalGetSectionCount()) {
            int sectionEnd = (sectionStart + internalGetCountForSection(i)) + 1;//
            if (position < sectionStart || position >= sectionEnd) {
                sectionStart = sectionEnd;
                i++;
            } else {
                int postionInSection = (position - sectionStart) - 1;
                mSectionPostionCache.put(position, postionInSection);
                return postionInSection;
            }
        }
        return 0;
    }

    public boolean isSectionHeader(int position) {
        int sectionStart = 0;
        for (int i = 0; i < internalGetSectionCount(); i++) {
            if (position == sectionStart)
                return true;
            if (position < sectionStart)
                return false;
            sectionStart += internalGetCountForSection(i) + 1;
        }
        return false;
    }

    public int getItemViewTypeCount() {
        return 1;
    }

    public int getItemViewType(int position){
        if (isSectionHeader(position)){//返回1 pinned
            return getItemViewTypeCount()+getSectionHeaderViewType(getSectionForPositon(position));
        }
        //返回0 不pinned
        return  getItemViewType(getSectionForPositon(position),getPositonInSectionForPosition(position));
    }

    public int getItemViewType(int  section ,int position) {
        return ITEM_VIEW_TYPE;
    }

    public int getSectionHeaderViewType(int section) {
        return HEADER_VIEW_TYPE;
    }

    public int getSectionHeaderViewTypeCount() {
        return 1;
    }

    public int getViewTypeCount(){
        return getItemViewTypeCount()+getSectionHeaderViewTypeCount();
    }

    /**
     * 根据section查询该section中item的数量
     * @param section
     * @return
     */
    private int internalGetCountForSection(int section) {
        Integer cachedSectionCount = mSectionCountCache.get(section);
        if (cachedSectionCount != null) {
            return cachedSectionCount.intValue();
        }
        int sectionCount = getCountForSection(section);
        mSectionCountCache.put(section, Integer.valueOf(sectionCount));
        return sectionCount;
    }

    /**
     * section种类的数量
     * @return
     */
    private int internalGetSectionCount() {
        if (mSectionCount >= 0) {
            return mSectionCount;
        }
        mSectionCount = getSectionCount();
        return mSectionCount;
    }

}
