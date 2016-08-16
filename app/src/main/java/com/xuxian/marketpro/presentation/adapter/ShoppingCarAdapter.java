package com.xuxian.marketpro.presentation.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.xuxian.marketpro.presentation.View.adapter.SectionedBaseAdapter;
import com.xuxian.marketpro.presentation.View.listview.ShoppingCarListView;

/**
 * Created by youarenotin on 16/8/16.
 */
public class ShoppingCarAdapter extends SectionedBaseAdapter implements ShoppingCarListView.PinnedSectionListAdapter{


    /**
     * 是否为被固定的item or section
     * @param viewType
     * @return
     */
    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType==1;
    }

    /**
     * 该section子目中的数量
     * @param i
     * @return
     */
    @Override
    public int getCountForSection(int i) {
        return 0;
    }

    @Override
    public Object getItem(int section, int postion) {
        return null;
    }

    @Override
    public long getItemId(int section, int postion) {
        return 0;
    }

    /**
     * section 数量
     * @return
     */
    @Override
    public int getSectionCount() {
        return 0;
    }

    /**
     * section view
     * @param section
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getSectionHeaderView(int section, View view, ViewGroup viewGroup) {
        return null;
    }

    /**
     * item view
     * @param section
     * @param postion
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getItemView(int section, int postion, View view, ViewGroup viewGroup) {
        return null;
    }
}
