package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.presentation.View.adapter.ClassifyAdapter;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.entity.ClassifyEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/14.
 */
public class ClassifyActivity extends SuperSherlockActivity{
    private NetworkAsyncTask calssifyAsyncTask;
    private ActivityStateView emptyview_state;
    private ClassifyAdapter mAdapter;
    private ListView mClassifyList;
    private List<ClassifyEntity> mDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_content);
        initTitleBar();
        initFindViewById();
        setListener();
        init();
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTitleBar() {
        setTitle("分类");
    }

    @Override
    protected void initFindViewById() {

    }

    @Override
    protected void setListener() {

    }

    private class NetworkAsyncTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }
    }
}
