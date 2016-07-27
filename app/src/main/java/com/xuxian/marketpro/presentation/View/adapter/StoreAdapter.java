package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xuxian.marketpro.presentation.db.ShoppingCartGoodsDb;
import com.xuxian.marketpro.presentation.entity.StoreEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/7/27.
 */
public class StoreAdapter extends BaseAdapter {
    private ShoppingCartGoodsDb detailsDb;
    private Context mContext;
    private List<StoreEntity> mListData;
    private OnShopItemListener onShopItemListener;
    private int store_id;

    public interface OnShopItemListener {
        void showOverLayPop(StoreEntity storeEntity);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
