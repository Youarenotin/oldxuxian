package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by youarenotin on 16/8/9.
 */
public abstract class SectionedBaseAdapter extends BaseAdapter implements  HeaderBaseAdapter {
    private static int HEADER_VIEW_TYPE=0;
    private static int ITEM_VIEW_TYPE=0;
    int mCount=-1;
    private SparseArray<Integer> mSectionCache = new SparseArray<>();
    private int mSectionCount=-1;
    private SparseArray<Integer> mSectionCountCache=new SparseArray<>();
    private SparseArray<Integer> mSectionPostionCache=new SparseArray<>();

    public abstract int getCountForSection(int i);
    public abstract Object getItem(int i,int i2);
    public abstract long getItemId(int i ,int i2);
    public abstract  View getItemView(int i  , int i2 ,View view,ViewGroup viewGroup);
    public abstract  int getSectionCount();
    public abstract View getSectionHeaderView(int i , View view,ViewGroup viewGroup);



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

    @Override
    public void notifyDataSetChanged() {
        mSectionCache.clear();
        mSectionCountCache.clear();;
        mSectionPostionCache.clear();
        mCount=-1;
        mSectionCount=-1;
        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetInvalidated() {
        mSectionCache.clear();
        mSectionCountCache.clear();;
        mSectionPostionCache.clear();
        mCount=-1;
        mSectionCount=-1;
        super.notifyDataSetInvalidated();
    }


    public int getPositonInSectionForPosition(int position){
        Integer cachedPosition = mSectionPostionCache.get(position);
        if (cachedPosition!=null)
            return cachedPosition;
        int sectionStart=0,i=0;
        while (i<internalGetSectionCount()){
            int sectionEnd = (sectionStart+internalGetCountForSection(i))+1;//

        }
    }

    public boolean isSectionHeader(int position){
        int sectionStart = 0;
        for (int i = 0; i<internalGetSectionCount();i++){
            if (position==sectionStart)
                return true;
            if (position<sectionStart)
                return false;
            sectionStart+=internalGetCountForSection(i)+1;
        }
        return false;
    }

    public int getItemViewTypeCount(){
        return 1;
    }

    public int getItemViewType(){
        return ITEM_VIEW_TYPE;
    }

    public int getSectionHeaderViewType(int section){
        return HEADER_VIEW_TYPE;
    }

    public int getSectionHeaderViewTypeCount(){
        return 1;
    }

    private int internalGetCountForSection(int section){
        Integer cachedSectionCount=mSectionCountCache.get(section);
        if (cachedSectionCount!=null){
            return cachedSectionCount.intValue();
        }
        int sectionCount =getCountForSection(section);
        mSectionCountCache.put(section,Integer.valueOf(sectionCount));
        return sectionCount;
    }

    private int internalGetSectionCount(){
        if (mSectionCount>=0){
            return mSectionCount;
        }
        mSectionCount=getSectionCount();
        return mSectionCount;
    }

}
