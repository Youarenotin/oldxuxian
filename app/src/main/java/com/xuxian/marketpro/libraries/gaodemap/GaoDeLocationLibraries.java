package com.xuxian.marketpro.libraries.gaodemap;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.xuxian.marketpro.libraries.util.monitor.GaoDeLocationMonitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor;

/**
 * Created by youarenotin on 16/7/24.
 */
public class GaoDeLocationLibraries implements AMapLocationListener,Runnable{
    private static GaoDeLocationLibraries gaoDeLocationLibraries;
    private AMapLocationClient aMapLocationClient;
//    private LocationManagerProxy aMapLocManager;
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
       // AMapLocationClient.setApiKey("6c67cb3c5ac7a5a84670a23557815960");//设置api key
        aMapLocationClient= new AMapLocationClient(mContext);
        if (aMapLocationClient.isStarted()){
            aMapLocationClient.stopLocation();
        }
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(false);
        option.setNeedAddress(true);
        aMapLocationClient.setLocationOption(option);
        aMapLocationClient.setLocationListener(this);
        aMapLocationClient.startLocation();
//        aMapLocManager =LocationManagerProxy.getInstance(mContext);
//        aMapLocManager.setGpsEnable(true);
//       aMapLocManager.requestLocationData(LocationManagerProxy.NETWORK_PROVIDER,2000,10.0f,this);
//        aMapLocManager.requestLocationData(LocationProviderProxy.AMapNetwork,2000,10.0f,this);
    }
    public void stopLocation(){
        if (aMapLocationClient!=null){
            aMapLocationClient.stopLocation();
            aMapLocationClient.onDestroy();
            aMapLocationClient=null;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        Log.d("gaode","11");
        if (aMapLocation!=null){
            if (aMapLocation.getErrorCode()==0){
//                aMapLocation.getAddress();
//                aMapLocation.getCityCode();
//                aMapLocation.getProvince();
//               String location = aMapLocation.getLocationDetail();
//                String str=aMapLocation.getCity();
                GaoDeLocationMonitor.getInstance().IssuedMonitor(gaoDeLocationEnum,aMapLocation);
                stopLocation();
            }

        }
    }
}
