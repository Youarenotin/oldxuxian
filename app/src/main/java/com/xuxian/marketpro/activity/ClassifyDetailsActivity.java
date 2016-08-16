package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.presentation.View.adapter.PublicListAdapter;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;

/**
 * Created by youarenotin on 16/8/15.
 */
public class ClassifyDetailsActivity extends SuperSherlockActivity {
    public static final String INTENT_ACTION_TITLE = "action_title";
    public static final String INTENT_CATEGORYID = "categoryid";
    private ActivityStateView emptyview_state;
    private GridView gridView;
    private PublicListAdapter mAdapter;
    private String mCategoryid;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_details_list_layout);
        this.title = getIntent().getStringExtra(ClassifyDetailsActivity.INTENT_ACTION_TITLE);
        this.mCategoryid = getIntent().getStringExtra(ClassifyDetailsActivity.INTENT_CATEGORYID);
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
        mAdapter = new PublicListAdapter(getActivity());
        gridView.setAdapter(mAdapter);
        mAdapter.setData();
    }

    @Override
    protected void initTitleBar() {
        setTitle(title);
    }

    @Override
    protected void initFindViewById() {
        emptyview_state = (ActivityStateView) findViewById(R.id.emptyview_state);
        gridView = (GridView) findViewById(R.id.classify_detail_list);
    }

    @Override
    protected void setListener() {

    }
}
