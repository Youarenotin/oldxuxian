package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.presentation.View.adapter.CityListAdapter;
import com.xuxian.marketpro.presentation.entity.CityEntity;
import com.xuxian.marketpro.presentation.entity.SchoolEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by youarenotin on 16/8/30.
 */
public class SchoolCityActivity extends SuperSherlockActivity{
    private List<CityEntity.DataEntity.CityInfoEntity> cityInfoEntities;
    Handler dataHandler;
    private CityListAdapter mCityListAdapter;
    private ListView mListView;
    private Map<String, List<SchoolEntity>> mapSchool;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.city_list);
        initTitleBar();
        initFindViewById();
        setListener();
        init();
    }

    @Override
    protected Activity getActivity() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTitleBar() {
        setTitle("城市");
    }

    @Override
    protected void initFindViewById() {
        this.mListView = (ListView) findViewById(R.id.listView);
        this.mListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.city_header_1, null));
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                try {
                    if (mapSchool != null && !mapSchool.isEmpty() && cityInfoEntities != null && !cityInfoEntities.isEmpty()) {
                        List<SchoolEntity> schoolEntities = (List) mapSchool.get(cityInfoEntities.get((int) id).getCity_name());
                        int length = schoolEntities.size();
                        String[] schools = new String[length];
                        for (int i = 0; i < length; i++) {
                            schools[i] = ((SchoolEntity) schoolEntities.get(i)).getSchool();
                        }
                        schoolDialog(schools);
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    protected void setListener() {

    }
}
