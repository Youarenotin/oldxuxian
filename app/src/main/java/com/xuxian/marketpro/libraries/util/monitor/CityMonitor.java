package com.xuxian.marketpro.libraries.util.monitor;

import android.util.SparseArray;

import com.xuxian.marketpro.presentation.entity.CityEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by youarenotin on 16/7/25.
 */
public class CityMonitor {
    private static CityMonitor mMonitor;
    private Map<String, CityMomitorCallback> monitorMap = new HashMap<String, CityMomitorCallback>();

    public static CityMonitor getInstance() {
        if (mMonitor == null) {
            mMonitor = new CityMonitor();
            return mMonitor;
        }
        return mMonitor;
    }

    public interface CityMomitorCallback {

        void appOpration(monitor.CityEnum cityEnum, CityEntity.DataEntity.CityInfoEntity cityEntity);
    }

    public Map<String, CityMomitorCallback> getMonitorMap() {
        return monitorMap;
    }

    public void IssueMonitors(monitor.CityEnum cityEnum, CityEntity.DataEntity.CityInfoEntity cityInfoEntity) {
        for (Entry entry : monitorMap.entrySet()) {
            ((CityMomitorCallback) entry.getValue()).appOpration(cityEnum, cityInfoEntity);
        }
    }

    public void register(String key ,CityMomitorCallback callback){
        monitorMap.put(key,callback);
    }

    public boolean isRegister(String key){
        if (monitorMap.get(key)!=null)
            return true;
        return false;
    }

    public void  cancelRegister(String key){
        if (monitorMap.containsKey(key)){
            monitorMap.remove(key);
        }
    }
}
