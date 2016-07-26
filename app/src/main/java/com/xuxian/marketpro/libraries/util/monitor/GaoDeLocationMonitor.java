package com.xuxian.marketpro.libraries.util.monitor;

import com.amap.api.location.AMapLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youarenotin on 16/7/25.
 */
public class GaoDeLocationMonitor {
    public static final int INSTALL_KEY = 0;
    public static final int UNINSTALL_KEY = 1;
    private static GaoDeLocationMonitor mMonitor;
    private static Map<String,GaoDeLocationMonitorCallback> mMonitorMap=new HashMap();;

    public interface GaoDeLocationMonitorCallback {
        void appOperation(monitor.GaoDeLocationEnum gaoDeLocationEnum, AMapLocation aMapLocation);
    }

    public static Map<String, GaoDeLocationMonitorCallback> getmMonitorMap() {
        return mMonitorMap;
    }

    public void IssuedMonitor(monitor.GaoDeLocationEnum gaoDeLocationEnum, AMapLocation aMapLocation) {
        for (Map.Entry entry : mMonitorMap.entrySet()) {
            ((GaoDeLocationMonitorCallback) entry.getValue()).appOperation(gaoDeLocationEnum, aMapLocation);
        }
    }

    public static GaoDeLocationMonitor getInstance() {
        if (mMonitor == null) {
            mMonitor = new GaoDeLocationMonitor();
        }
        return mMonitor;
    }

    public void register( String key,GaoDeLocationMonitorCallback listener) {
        mMonitorMap.put(key, listener);
    }

    public boolean isRegister(String key) {
        if (mMonitorMap.get(key) != null) {
            return true;
        }
        return false;
    }

    public void cancelRegister(String key) {
        mMonitorMap.remove(key);
    }
}
