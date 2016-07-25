package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.presentation.entity.CityEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/7/24.
 */
public class CityListActivity extends SuperSherlockActivity
{
    private List<CityEntity.DataEntity.CityInfoEntity> cityList;
    public ActivityStateView emptyview_state;
    private CityListAdapter mCityListAdapter;
    private ListView mListView;
    private ProgressBar pb_load;
    private TextView tv_location_city;

    @Override
    protected Activity getActivity() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initFindViewById() {

    }

    @Override
    protected void setListener() {

    }
}
