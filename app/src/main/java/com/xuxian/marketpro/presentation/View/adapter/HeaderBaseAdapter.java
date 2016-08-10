package com.xuxian.marketpro.presentation.View.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by youarenotin on 16/8/9.
 */
public interface HeaderBaseAdapter {
    int getCount();
    int getSectionForPositon(int position);
    View getSectionHeaderView(int postion , View view , ViewGroup viewGroup);
    int  getSectionHeaderViewType(int position);
    boolean isSectionHeader(int position);
}
