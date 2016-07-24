package com.xuxian.market.libraries.gaodemap;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.xuxian.market.libraries.util.monitor;

import java.security.KeyStore;

/**
 * Created by youarenotin on 16/7/24.
 */
public class GaoDeLocationLibraries implements AMapLocationListener,Runnable{
    private static GaoDeLocationLibraries gaoDeLocationLibraries;
//    private AMapLocationClient aMapLocationClient;
    private LocationManagerProxy aMapLocManager;
    private AMapLocation aMapLocation = null;//定位信息
    private monitor.GaoDeLocationEnum gaoDeLocationEnum;
    private Handler handler;
    private Context mContext;


    @Override
    public void run() {

    }

    public GaoDeLocationLibraries(Context mContext) {
        this.mContext = mContext;
    }

    public static GaoDeLocationLibraries getInstance(Context ct){
        if (gaoDeLocationLibraries==null){
            gaoDeLocationLibraries = new GaoDeLocationLibraries(ct);
        }
        return gaoDeLocationLibraries;
    }

    public void startLocation(boolean isGps , monitor.GaoDeLocationEnum gaoDeLocationEnum){
           this.gaoDeLocationEnum=gaoDeLocationEnum;
////        AMapLocationClient.setApiKey("4f96859a7490d32877f657433b5521ad");//设置api key
//        aMapLocationClient= new AMapLocationClient(mContext);
//        if (aMapLocationClient.isStarted()){
//            aMapLocationClient.stopLocation();
//        }
//        AMapLocationClientOption option = new AMapLocationClientOption();
//        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        option.setNeedAddress(true);
////        option.setGpsFirst(true);
//        aMapLocationClient.setLocationOption(option);
//        aMapLocationClient.setLocationListener(this);
//        aMapLocationClient.startLocation();

        aMapLocManager =LocationManagerProxy.getInstance(mContext);
        aMapLocManager.setGpsEnable(true);
        aMapLocManager.requestLocationData("lbs",2000,10.0f,this);

    }


    @Override
    public void onLocationChanged(Location location) {
        Log.d("gaode","11");
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        Log.d("gaode","11");
    }
}
