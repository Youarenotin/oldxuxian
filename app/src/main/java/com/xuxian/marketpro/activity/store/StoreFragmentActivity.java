package com.xuxian.marketpro.activity.store;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbScreenUtils;
import com.ab.util.AbViewUtil;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockFragmentActivity;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.libraries.util.monitor.CityMonitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor;
import com.xuxian.marketpro.presentation.entity.CityEntity;

/**
 * Created by youarenotin on 16/7/27.
 */
public class StoreFragmentActivity extends SuperSherlockFragmentActivity {
    public static final String ADDRESS_ID = "address_id";
    public static final String AREA_ID = "area_id";
    public static final String CITY_AREA = "city_area";
    public static final String CITY_ID = "city_id";
    public static final String CITY_NAME = "city_name";
    public static final String CITY_STATUS = "city_status";
    public static final String INTENT_BUNDLE_CITYINFOENTITY = "cityInfoEntity";
    public static final String SITE_ID = "site_id";
    public static final String SITE_NAME = "site_name";
    public static final String STORE_ADDRESS = "store_address";
    public static final String STORE_TYPE = "store_type";
    private Button btn_store_fragment_courier;
    private Button btn_store_fragment_store;
//    private CourierFragment courierFragment;
    private int currentTabIndex;
    private int index;
    private boolean isShowStoreDialog;
    private ImageView iv_title_bar_left_icon;
    private ImageView iv_title_bar_right_icon;
    private LinearLayout ll_title_bar_left_click;
    private LinearLayout ll_title_bar_right_click;
    private Fragment mContent;
    private Button[] mTabs;
    private int screenWidth;
    private StoreFragment storeFragment;
    private int titleBarHeight;
    private TextView tv_title_bar_center_title;
    private TextView tv_title_bar_left_text;
    private TextView tv_title_bar_right_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       // getWindow().setFormat(PixelFormat.TRANSLUCENT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_fragment_layout);
        initTitleBar();
        initfindViewById();
        setListener();
        init();
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void init() {
        int w = this.screenWidth / 5;
        int h = (this.titleBarHeight / 4) * 3;
        AbViewUtil.setViewWH(this.btn_store_fragment_courier, w, h);
        AbViewUtil.setViewWH(this.btn_store_fragment_store, w, h);
    }

    @Override
    protected void initTitleBar() {
        View view = View.inflate(getActivity(),R.layout.title_bar_address_layout,null);
        this.titleBarHeight= AbViewUtil.getViewHeight(view.findViewById(R.id.rl_title_bar));
        this.screenWidth= AbScreenUtils.getScreenWidth(getActivity());
        getActionBar().setCustomView(view);
        this.ll_title_bar_left_click = (LinearLayout) view.findViewById(R.id.ll_title_bar_left_click);
        this.iv_title_bar_left_icon = (ImageView) view.findViewById(R.id.iv_title_bar_left_icon);
        this.tv_title_bar_left_text = (TextView) view.findViewById(R.id.tv_title_bar_left_text);
        this.tv_title_bar_center_title = (TextView) view.findViewById(R.id.tv_title_bar_center_title);
        this.ll_title_bar_right_click = (LinearLayout) view.findViewById(R.id.ll_title_bar_right_click);
        this.iv_title_bar_right_icon = (ImageView) view.findViewById(R.id.iv_title_bar_right_icon);
        this.tv_title_bar_right_text = (TextView) view.findViewById(R.id.tv_title_bar_right_text);
        ll_title_bar_left_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initfindViewById() {
        this.mTabs = new Button[2];
        this.btn_store_fragment_courier = (Button) findViewById(R.id.btn_store_fragment_courier);
        this.btn_store_fragment_store = (Button) findViewById(R.id.btn_store_fragment_store);
        this.mTabs[0] = this.btn_store_fragment_courier;
        this.mTabs[1] = this.btn_store_fragment_store;
    }

    @Override
    protected void setListener() {
        ll_title_bar_right_click.setOnClickListener(new BarOnClickListener());//选择城市
        CityMonitor.getInstance().register(StoreFragmentActivity.class.getSimpleName(), new CityMonitor.CityMomitorCallback() {
            @Override
            public void appOpration(monitor.CityEnum cityEnum, CityEntity.DataEntity.CityInfoEntity cityEntity) {

            }
        });

    }

    class BarOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ActivityUtil.startCityListActivity(getActivity());
        }
    }
}
