package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.IHttpResponseCallBack;
import com.ab.util.AbPreferenceUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.store.StoreFragmentActivity;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.net.NewIssRequest;
import com.xuxian.marketpro.net.entity.ResultDataEntity;
import com.xuxian.marketpro.net.entity.ResultDataEntity.StatusEntity;
import com.xuxian.marketpro.presentation.View.adapter.PublicListAdapter;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.entity.GoodsListEntity;

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

        emptyview_state.setVisibility(View.VISIBLE);
        emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_LOADING);

        int mSiteId = AbPreferenceUtils.loadPrefInt(getActivity(), StoreFragmentActivity.SITE_ID, 0);
        AbRequestParams params=new AbRequestParams();
        params.put("locationid",mSiteId);
        params.put(ClassifyDetailsActivity.INTENT_CATEGORYID,mCategoryid);
        requestData(params);
    }

    private void requestData(AbRequestParams params) {
        AbHttpUtil.getInstance(getActivity()).postAndParse(
                NewIssRequest.GETGOODSLIST,
                params,
                String.class,
                new IHttpResponseCallBack<String>() {
                    @Override
                    public void EndToParse() {

                    }

                    @Override
                    public void FailedParseBean(String str) {
                        emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_NETWORK_ERROR);
                        emptyview_state.setClickable(true);
                        emptyview_state.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int mSiteId = AbPreferenceUtils.loadPrefInt(getActivity(), StoreFragmentActivity.SITE_ID, 0);
                                AbRequestParams params=new AbRequestParams();
                                params.put("locationid",mSiteId);
                                params.put(ClassifyDetailsActivity.INTENT_CATEGORYID,mCategoryid);
                                requestData(params);
                            }
                        });
                    }

                    @Override
                    public void StartToParse() {

                    }

                    @Override
                    public void SucceedParseBean(String result) {
                        ResultDataEntity<GoodsListEntity> resultDataEntity=new ResultDataEntity<GoodsListEntity>();
                        JSONObject jsonObject = JSONObject.parseObject(result);
                        if (jsonObject.containsKey("status")){
                            resultDataEntity.setStatus(JSON.parseObject(jsonObject.getString("status"),StatusEntity.class));
                        }
                        if (jsonObject.containsKey("data")){
                            resultDataEntity.setDataList(JSON.parseArray(jsonObject.getString("data"),GoodsListEntity.class));
                        }

                        if (resultDataEntity.getStatus().getCode()==0) {
                            if (resultDataEntity!=null && resultDataEntity.getDataList()!=null && !resultDataEntity.getDataList().isEmpty()){
                                mAdapter.setData(resultDataEntity.getDataList());
                                gridView.setAdapter(mAdapter);
                                emptyview_state.setVisibility(View.GONE);
                                return ;
                            }
                            emptyview_state.setNodataText("没有商品");
                            emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_NODATA);
                        }
                        else {
                            emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_NETWORK_ERROR);
                            emptyview_state.setClickable(true);
                            emptyview_state.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int mSiteId = AbPreferenceUtils.loadPrefInt(getActivity(), StoreFragmentActivity.SITE_ID, 0);
                                    AbRequestParams params=new AbRequestParams();
                                    params.put("locationid",mSiteId);
                                    params.put(ClassifyDetailsActivity.INTENT_CATEGORYID,mCategoryid);
                                    requestData(params);
                                }
                            });
                        }
                    }
                }
        );
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
