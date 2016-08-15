package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
public class ClassifyActivity extends SuperSherlockActivity implements AdapterView.OnItemClickListener {
    private NetworkAsyncTask classifyAsyncTask;
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
        this.mAdapter=new ClassifyAdapter(getActivity());
        mClassifyList.setAdapter(mAdapter);
        classifyAsyncTask=new NetworkAsyncTask();
    }

    @Override
    protected void initTitleBar() {
        setTitle("分类");
    }

    @Override
    protected void initFindViewById() {
        mClassifyList = (ListView) findViewById(R.id.classify_list);
        mClassifyList.setAdapter(mAdapter);
        emptyview_state = (ActivityStateView) findViewById(R.id.emptyview_state);
    }

    @Override
    protected void setListener() {
        mClassifyList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private class NetworkAsyncTask extends AsyncTask<Object, Void, Object> {

        public NetworkAsyncTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            emptyview_state.setVisibility(View.VISIBLE);
            emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            if (result != null) {

            }
        }
    }
}
