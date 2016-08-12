package com.xuxian.marketpro.presentation.adapter;

import android.content.Context;

import com.ab.view.adapter.AbBaseAdapter;
import com.ab.view.holder.AbBaseHolder;
import com.xuxian.marketpro.activity.tab.TabMainFragmentActivity;
import com.xuxian.marketpro.presentation.entity.GoodsFragmentHeaderEntity;
import com.xuxian.marketpro.presentation.entity.GoodsFragmentHeaderEntity.DataEntity.AppEntity;
import com.xuxian.marketpro.presentation.holer.GoodsFragmentHeaderHolder;

import java.util.List;

/**
 * Created by youarenotin on 16/8/11.
 */
public class GoodsFragmentHeaderAdapter extends AbBaseAdapter<AppEntity>{
    private TabMainFragmentActivity tabMainFragmentActivity;


    public GoodsFragmentHeaderAdapter(Context context, List<AppEntity> mDatas,TabMainFragmentActivity tabMainFragmentActivity) {
        super(context, mDatas);
        this.tabMainFragmentActivity=tabMainFragmentActivity;
    }


    @Override
    public AbBaseHolder getHolder() {

        return new GoodsFragmentHeaderHolder(context,tabMainFragmentActivity);
    }

}
