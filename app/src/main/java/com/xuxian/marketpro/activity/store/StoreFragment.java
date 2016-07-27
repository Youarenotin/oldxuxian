package com.xuxian.marketpro.activity.store;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.presentation.View.adapter.AreaAdapter;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.db.ShoppingCartGoodsDb;
import com.xuxian.marketpro.presentation.db.StoreDb;
import com.xuxian.marketpro.presentation.entity.CityEntity;
import com.xuxian.marketpro.presentation.entity.GetStoreEntity;
import com.xuxian.marketpro.presentation.entity.StoreEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/7/27.
 */
public class StoreFragment extends SuperFragment {
    private AMap aMap;
    private AreaAdapter areaAdapter;
    private LatLng cenpt;
    private CityEntity.DataEntity.CityInfoEntity cityEntity;
    private String city_area;
    private String city_id;
    private ActivityStateView emptyview_state;
    private GetStoreEntity getStoreEntity;
    private boolean isExpress;
    boolean isFirstLoc;
    private boolean isShowPop;
    private boolean isSwitchCity;
    private LinearLayout ll_storeFragment_map;
    private ListView lv_shop_site_area;
    private MapView mapView;
    private LatLng mylatLng;
    public TextView shop_overlay_name;
    private ListView shop_site_list;
    private ShoppingCartGoodsDb shoppingCartGoodsDb;
    private StoreAdapter storeAdapter;
    private StoreDb storeDb;
    private StoreEntity storeEntity;
    private List<GetStoreEntity.DataEntity.StoreInfoEntity> storeInfoEntities;
    private List<StoreEntity> storeList;
    public View view_layout;
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
