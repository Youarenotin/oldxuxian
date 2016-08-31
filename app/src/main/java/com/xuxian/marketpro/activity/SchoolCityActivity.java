package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ab.util.AbCharacterParser;
import com.ab.util.AbStrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.libraries.util.AppJsonFileReader;
import com.xuxian.marketpro.presentation.View.adapter.CityListAdapter;
import com.xuxian.marketpro.presentation.entity.CityEntity;
import com.xuxian.marketpro.presentation.entity.SchoolEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by youarenotin on 16/8/30.
 */
public class SchoolCityActivity extends SuperSherlockActivity {
    private List<CityEntity.DataEntity.CityInfoEntity> cityInfoEntities;
    Handler dataHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCityListAdapter.setData(filledData(SchoolCityActivity.this.cityInfoEntities));
            dismissLoadingDialog();
        }
    };

    private List<CityEntity.DataEntity.CityInfoEntity> filledData(List<CityEntity.DataEntity.CityInfoEntity> list) {
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    AbCharacterParser characterParser = AbCharacterParser.getInstance();
                    int length = list.size();
                    for (int i = 0; i < length; i++) {
                        CityEntity.DataEntity.CityInfoEntity cityInfoEntity = list.get(i);
                        String pinyin = characterParser.getSelling(cityInfoEntity.getCity_name());
                        String sortString = pinyin.substring(0, 1).toUpperCase();
                        if (sortString.matches("[A-Z]")) {
                            cityInfoEntity.setFirstLetter(sortString.toUpperCase());
                            cityInfoEntity.setPinYinName(pinyin.toLowerCase());
                        } else {
                            cityInfoEntity.setFirstLetter("#");
                        }
                    }
                    Collections.sort(list);
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    private CityListAdapter mCityListAdapter;
    private ListView mListView;
    private Map<String, List<SchoolEntity>> mapSchool;

    public SchoolCityActivity() {
        this.cityInfoEntities = new ArrayList();
        this.mapSchool = new HashMap();
    }

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
        mCityListAdapter = new CityListAdapter(getActivity());
        mListView.setAdapter(mCityListAdapter);
        showLoadingDialog(null);
        new AsyncTask<Void, Void, Void>() {//只需要在后台解析文件即可
            @Override
            protected Void doInBackground(Void... params) {
                JSONObject object = JSON.parseObject(AppJsonFileReader.getJson(SchoolCityActivity.this.getBaseContext(), "school.json"));
                for (String str : object.keySet()) {
                    String key = str.toString();
                    if (!AbStrUtil.isEmpty(key)) {
                        mapSchool.put(key, JSON.parseArray(object.getString(key), SchoolEntity.class));
                        CityEntity.DataEntity.CityInfoEntity cityInfoEntity = new CityEntity.DataEntity.CityInfoEntity();
                        cityInfoEntity.setCity_name(key);
                        SchoolCityActivity.this.cityInfoEntities.add(cityInfoEntity);
                    }
                }
                //解析完毕后 通过handler发送通知数据准备好
                dataHandler.sendMessage(Message.obtain());
                return null;
            }
        }.execute(new Void[2]);
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
                try {//没有数据不响应点击
                    if (mapSchool != null && !mapSchool.isEmpty() && cityInfoEntities != null && !cityInfoEntities.isEmpty()) {
                        List<SchoolEntity> schoolEntities = mapSchool.get(cityInfoEntities.get((int) id).getCity_name());
                        int length = schoolEntities.size();
                        String[] schools = new String[length];
                        for (int i = 0; i < length; i++) {
                            schools[i] = schoolEntities.get(i).getSchool();
                        }
                        schoolDialog(schools);
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void schoolDialog(String[] schools) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("学校选择");
        builder.setItems(schools, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @Override
    protected void setListener() {

    }
}
