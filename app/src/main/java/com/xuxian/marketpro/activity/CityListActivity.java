package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.IHttpResponseCallBack;
import com.ab.util.AbAppUtil;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.amap.api.location.AMapLocation;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.libraries.gaodemap.GaoDeLocationLibraries;
import com.xuxian.marketpro.libraries.util.monitor.CityMonitor;
import com.xuxian.marketpro.libraries.util.monitor.GaoDeLocationMonitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor;
import com.xuxian.marketpro.net.NewIssRequest;
import com.xuxian.marketpro.net.RequestParamsNet;
import com.xuxian.marketpro.presentation.View.adapter.CityListAdapter;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.application.MyApplication;
import com.xuxian.marketpro.presentation.entity.CityEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/7/24.
 */
public class CityListActivity extends SuperSherlockActivity implements CityMonitor.CityMomitorCallback, GaoDeLocationMonitor.GaoDeLocationMonitorCallback {
    private List<CityEntity.DataEntity.CityInfoEntity> cityList;
    public ActivityStateView emptyview_state;
    private CityListAdapter mCityListAdapter;
    private ListView mListView;
    private ProgressBar pb_load;
    private TextView tv_location_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);
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
        getCity();
    }

    @Override
    protected void initTitleBar() {
        setTitle("选择城市");
    }

    @Override
    protected void initFindViewById() {
        this.emptyview_state = (ActivityStateView) findViewById(R.id.emptyview_state);
        View headerView = LayoutInflater.from(this).inflate(R.layout.city_header, null);
        this.mListView = (ListView) findViewById(R.id.listView);
        this.tv_location_city = (TextView) headerView.findViewById(R.id.tv_location_city);
        this.mListView.addHeaderView(headerView);
        this.pb_load = (ProgressBar) headerView.findViewById(R.id.pb_load);
    }

    @Override
    protected void setListener() {
        CityMonitor.getInstance().register(CityListActivity.class.getSimpleName(), this);
        GaoDeLocationMonitor.getInstance().register(CityListActivity.class.getSimpleName(), this);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                OnItemClick((int) id);
            }
        });
    }

    private void OnItemClick(int position) {
        CityEntity.DataEntity.CityInfoEntity cityInfoEntity=null;
        if (position == -1) {
            if (this.tv_location_city.getText().toString().trim().equals("正在定位...")) {
                AbToastUtil.showToast(getActivity(), "正在定位...");
            } else if (this.tv_location_city.getText().toString().trim().equals("定位失败,请点击重试")) {
                this.pb_load.setVisibility(View.VISIBLE);
                GaoDeLocationLibraries.getInstance(getActivity()).startLocation(true, monitor.GaoDeLocationEnum.LOCATING_CITY);
            }else if (cityList!=null && !cityList.isEmpty()){
                boolean boo=false;
                for (int i=0;i<cityList.size();i++){
                    if (cityList.get(i).getCity_name().equals(this.tv_location_city.getText().toString().trim())){
                        boo=true;
                        cityInfoEntity=cityList.get(i);
                        break;
                    }
                }
                if (!boo){
                    AbToastUtil.showToast(getActivity(),"当前城市没有店面");
                }else {
                    //// TODO: 16/7/27  
                }
            }
        } else {

        }
    }

    @Override
    public void appOpration(monitor.CityEnum cityEnum, CityEntity.DataEntity.CityInfoEntity cityEntity) {
        switch (cityEnum) {
            case SWITCH_CITY:

                break;
            case CLOSE_PAGE:
                break;
        }
    }

    /**
     * 定位
     *
     * @param gaoDeLocationEnum
     * @param aMapLocation
     */
    @Override
    public void appOperation(monitor.GaoDeLocationEnum gaoDeLocationEnum, AMapLocation aMapLocation) {
        switch (gaoDeLocationEnum) {
            case LOCATING_CITY:

                break;
            case LOCATION_ADDRESS:
                if (aMapLocation != null) {
                    if (!AbStrUtil.isEmpty(aMapLocation.getCity())) {
                        tv_location_city.setText(aMapLocation.getCity());
                        break;
                    } else {
                        tv_location_city.setText("定位失败,请点击重试");
                        break;
                    }
                }
                pb_load.setVisibility(View.GONE);
                tv_location_city.setText("定位失败,请点击重试");
                break;
            case LOCATION_LATITUDE_AND_LONGITUDE:

                break;
        }
        pb_load.setVisibility(View.GONE);
    }

    public void getCity() {
        AbHttpUtil.getInstance(getActivity()).postAndParse(
                NewIssRequest.GETSTORE,
                RequestParamsNet.getInstance(getActivity()).getStoreInfo("", "", ""),
                CityEntity.class,
                new IHttpResponseCallBack<CityEntity>() {

                    @Override
                    public void EndToParse() {

                    }

                    @Override
                    public void FailedParseBean(String str) {
                        emptyview_state.setVisibility(View.VISIBLE);
                        emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_NODATA);
                        emptyview_state.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getCity();
                            }
                        });
                    }

                    @Override
                    public void StartToParse() {
                        emptyview_state.setVisibility(View.VISIBLE);
                        emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
                    }

                    @Override
                    public void SucceedParseBean(CityEntity cityEntity) {
                        emptyview_state.setVisibility(View.GONE);
                        if (cityEntity != null && cityEntity.getData() != null) {
                            GaoDeLocationLibraries.getInstance(getActivity()).startLocation(true, monitor.GaoDeLocationEnum.LOCATION_ADDRESS);
                            pb_load.setVisibility(View.VISIBLE);
                            tv_location_city.setText("正在定位...");
                            List<CityEntity.DataEntity.CityInfoEntity> cityList = cityEntity.getData().getCity_info();
                            if (cityList != null && !cityList.isEmpty()) {
                                mCityListAdapter = new CityListAdapter(getActivity());
                                mListView.setAdapter(mCityListAdapter);
                                mCityListAdapter.setData(cityList);
                            }
                        }
                    }
                }
        );
    }
}
