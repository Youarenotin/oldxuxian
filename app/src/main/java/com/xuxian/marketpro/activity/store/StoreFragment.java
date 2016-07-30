package com.xuxian.marketpro.activity.store;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.IHttpResponseCallBack;
import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.libraries.gaodemap.GaoDeLocationLibraries;
import com.xuxian.marketpro.libraries.util.monitor.CityMonitor;
import com.xuxian.marketpro.libraries.util.monitor.GaoDeLocationMonitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor.GaoDeLocationEnum;
import com.xuxian.marketpro.net.NewIssRequest;
import com.xuxian.marketpro.net.RequestParamsNet;
import com.xuxian.marketpro.presentation.View.adapter.AreaAdapter;
import com.xuxian.marketpro.presentation.View.adapter.StoreAdapter;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.db.ShoppingCartGoodsDb;
import com.xuxian.marketpro.presentation.db.StoreDb;
import com.xuxian.marketpro.presentation.entity.CityEntity;
import com.xuxian.marketpro.presentation.entity.StoreEntity;
import com.xuxian.marketpro.presentation.entity.GetStoreEntity;

import java.util.List;


/**
 * Created by youarenotin on 16/7/27.
 */
public class StoreFragment extends SuperFragment implements LocationSource {
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
    private List<GetStoreEntity.DataBean.StoreInfoBean> storeInfoEntities;
    private List<StoreEntity> storeList;
    public View view_layout;
    private List<StoreEntity> store_list;

    @Override
    protected void init() {
        if (this.cityEntity!=null){
            this.city_id=cityEntity.getCity_id();
        }else{
            this.city_id=AbPreferenceUtils.loadPrefString(getActivity(),"city_id","");
        }
        this.storeDb=new StoreDb(getActivity());
        this.shoppingCartGoodsDb=new ShoppingCartGoodsDb(getActivity());
        this.storeAdapter=new StoreAdapter(getActivity());
        shop_site_list.setAdapter(storeAdapter);
        this.areaAdapter=new AreaAdapter(getActivity());
        lv_shop_site_area.setAdapter(areaAdapter);
        this.storeAdapter.setOnShopItemClickListener(new StoreAdapter.OnShopItemListener() {
            @Override
            public void showOverLayPop(StoreEntity storeEntity) {
                LatLng latLng = new LatLng(storeEntity.getLat().doubleValue(),storeEntity.getLng().doubleValue());
                Marker marker = aMap.addMarker(new MarkerOptions()
                        .title(storeEntity.getTitle())
                        .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_gcoding_overlay)))
                        .draggable(true)
                        .position(latLng)
                        .period(50));
                marker.showInfoWindow();
                replacestore();
            }
        });
        shop_site_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        lv_shop_site_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                areaAdapter.setInitPosition(i);
                if (storeInfoEntities!=null && !storeInfoEntities.isEmpty()){
                    List<StoreEntity> store_list = storeInfoEntities.get(i).getStore_list();
                    storeAdapter.setData(store_list);
                }
                initMapOverLay(true);
            }
        });
        this.city_area=AbPreferenceUtils.loadPrefString(getActivity(),"city_area");
        gaoDeLocation();
    }

    private void replacestore() {

    }


    private void gaoDeLocation() {
        this.emptyview_state.setVisibility(View.VISIBLE);
        this.emptyview_state.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
        GaoDeLocationLibraries.getInstance(getActivity()).startLocation(false, GaoDeLocationEnum.LOCATION_LATITUDE_AND_LONGITUDE);
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
            public void appOperation(GaoDeLocationEnum gaoDeLocationEnum, AMapLocation aMapLocation) {
                switch (gaoDeLocationEnum){
                    case LOCATION_LATITUDE_AND_LONGITUDE:
                        if (aMapLocation==null){
                            emptyview_state.setVisibility(View.GONE);
                            return ;
                        }
                        else if(aMapLocation.getLongitude()<=0||aMapLocation.getLatitude()<=0){
                            emptyview_state.setVisibility(View.GONE);
                            AbToastUtil.showToast(getActivity(),"定位失败");
                        }else{
                            emptyview_state.setVisibility(View.GONE);
                            cenpt=new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                            initStore(Double.valueOf(aMapLocation.getLatitude()),Double.valueOf(aMapLocation.getLongitude()));
                        }
                }
            }


        });

        CityMonitor.getInstance().register(StoreFragment.class.getSimpleName(), new CityMonitor.CityMomitorCallback() {
            @Override
            public void appOpration(monitor.CityEnum cityEnum, CityEntity.DataEntity.CityInfoEntity cityEntity) {

            }
        });


    }

    private void initStore(Double lat, Double lng) {
        AbHttpUtil.getInstance(getActivity()).postAndParse(NewIssRequest.GETSTORE,
                RequestParamsNet.getInstance(getActivity()).getStoreInfo("" + lat, "" + lng, this.city_id),
                GetStoreEntity.class,
                new IHttpResponseCallBack<GetStoreEntity>() {

                    @Override
                    public void EndToParse() {

                    }

                    @Override
                    public void FailedParseBean(String str) {

                    }

                    @Override
                    public void StartToParse() {

                    }

                    @Override
                    public void SucceedParseBean(GetStoreEntity content) {
                        emptyview_state.setVisibility(View.GONE);
                        getStoreEntity=content;
                        if (getStoreEntity!=null){
                            if (getStoreEntity==null) {
                                ((StoreFragmentActivity)getActivity()).showStoreMessge(getStoreEntity.getStatus().getMessage());
                                ll_storeFragment_map.setVisibility(View.INVISIBLE);

                            }
                            ll_storeFragment_map.setVisibility(View.VISIBLE);
                            storeInfoEntities=getStoreEntity.getData().getStore_info();
                            if (storeInfoEntities!=null && !storeInfoEntities.isEmpty()){
                                areaAdapter.setData(storeInfoEntities);
                                if (AbStrUtil.isEmpty(city_area)){
                                    isShowPop=false;
                                    areaAdapter.setInitPosition(0);
                                    store_list = storeInfoEntities.get(0).getStore_list();

                                } else{
                                  isShowPop=true;
                                    for (int i=0 ; i<storeInfoEntities.size() ;i++){
                                        if (storeInfoEntities.get(i).getArea_name().equals(city_area) ){
                                            store_list=storeInfoEntities.get(i).getStore_list();
                                            areaAdapter.setInitPosition(i);
                                            break;
                                        }
                                    }
                                }
                                storeAdapter.setData(store_list);
                                initMapOverLay(false);
                            }
                        }
                    }

                } );
     }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
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
//            aMap.setTrafficEnabled(true);//显示交通状况
//            aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
            aMap.setLocationSource(this);
            aMap.setMyLocationEnabled(true);
            UiSettings uiSettings = aMap.getUiSettings();
            uiSettings.setZoomGesturesEnabled(true);
            uiSettings.setScaleControlsEnabled(true);
            uiSettings.setZoomControlsEnabled(false);
            uiSettings.setMyLocationButtonEnabled(true);
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
        this.cityEntity=cityInfoEntity;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView!=null)
            mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView!=null)
            mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView!=null)
            mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mapView!=null) {
            mapView.onDestroy();
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }
}
