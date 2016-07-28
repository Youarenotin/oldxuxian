package com.xuxian.marketpro.activity.store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ab.util.AbPreferenceUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.libraries.util.monitor.CityMonitor;
import com.xuxian.marketpro.libraries.util.monitor.GaoDeLocationMonitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor;
import com.xuxian.marketpro.presentation.View.adapter.AreaAdapter;
import com.xuxian.marketpro.presentation.View.adapter.StoreAdapter;
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
public class StoreFragment extends SuperFragment  {
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
        this.lv_shop_site_area = (ListView) view.findViewById(R.id.lv_shop_site_area);
        this.shop_site_list = (ListView) view.findViewById(R.id.shop_site_list);
        this.ll_storeFragment_map = (LinearLayout) view.findViewById(R.id.ll_storeFragment_map);
        this.emptyview_state = (ActivityStateView) view.findViewById(R.id.emptyview_state);
    }

    @Override
    protected void setListener() {
        GaoDeLocationMonitor.getInstance().register(StoreFragment.class.getSimpleName(), new GaoDeLocationMonitor.GaoDeLocationMonitorCallback() {
            @Override
            public void appOperation(monitor.GaoDeLocationEnum gaoDeLocationEnum, AMapLocation aMapLocation) {

            }
        });

        CityMonitor.getInstance().register(StoreFragment.class.getSimpleName(), new CityMonitor.CityMomitorCallback() {
            @Override
            public void appOpration(monitor.CityEnum cityEnum, CityEntity.DataEntity.CityInfoEntity cityEntity) {

            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_location_layout, null);
        initTitleBar();
        initFindViewById(view);
        setListener();
        init();
        initMap(view, savedInstanceState);
        return view;
    }

    private void initMap(View view, Bundle savedInstanceState) {
        this.mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        if (this.aMap==null){
            aMap=mapView.getMap();
            UiSettings uiSettings = aMap.getUiSettings();
            uiSettings.setZoomGesturesEnabled(true);
            uiSettings.setScaleControlsEnabled(true);
            uiSettings.setZoomControlsEnabled(false);
            initMapOverLay(false);

            aMap.setOnMarkerDragListener(new AMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {

                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {

                }
            });

            aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
                @Override
                public void onMapLoaded() {

                }
            });

            aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    return false;
                }
            });

            aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {

                }
            });

            aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    return null;
                }
            });
        }
    }

    private void initMapOverLay(boolean isSwitchArea) {
        String title= "";
        if (this.storeList!=null && !this.storeList.isEmpty()){
            this.aMap.clear();
            int store_id = AbPreferenceUtils.loadPrefInt(getActivity(), "site_id", 0);
            if (store_id>0 && !isSwitchArea && !isSwitchCity){
                //TODO
            }else{
                this.cenpt=new LatLng(storeList.get(0).getLat().doubleValue(),storeList.get(0).getLng().doubleValue());
                this.isSwitchCity=false;
            }
            changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(cenpt, 15.0f, 30.0f, 0.0f)), null);
        }

    }

    private void changeCamera(CameraUpdate cameraUpdate, AMap.CancelableCallback callback) {
        this.aMap.animateCamera(cameraUpdate,callback);
    }

    public void setCityEntity(CityEntity.DataEntity.CityInfoEntity cityInfoEntity) {

    }
}
