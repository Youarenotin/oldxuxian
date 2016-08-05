package com.xuxian.marketpro.activity.tab.goods;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.activity.tab.TabMainFragmentActivity;

/**
 * 作者：lubo on 8/1 0001 18:12
 * 邮箱：lubo_wen@126.com
 */
@SuppressLint("ValidFragment")
public class GoodsFragment extends SuperFragment {

    public GoodsFragment(TabMainFragmentActivity activity){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_goods, null);
        initTitleBar();
        initFindViewById(view);
        setListener();
        init();
        return view;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    protected void setListener() {

    }
}
