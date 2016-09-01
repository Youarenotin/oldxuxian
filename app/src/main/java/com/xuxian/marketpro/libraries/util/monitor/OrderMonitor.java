package com.xuxian.marketpro.libraries.util.monitor;


import com.xuxian.marketpro.libraries.util.monitor.OrderMonitormonitor.OrderEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by youarenotin on 16/9/1.
 */
public class OrderMonitor {
    private static OrderMonitor monitor;
    private Map<String,OrderMonitorCallback> map=new HashMap();

    public interface OrderMonitorCallback{
        void appOperation(OrderEnum orderEnum);
    }

    public static OrderMonitor getInstance(){
        if (monitor==null){
            monitor=new OrderMonitor();
        }
        return monitor;
    }
    
    public void registerMonitor(String key,OrderMonitorCallback callback){
        if (map!=null){
            map.put(key,callback);
        }
    }
    
    public void issueOrderCallback(OrderEnum orderEnum){
        for (Entry entry: map.entrySet()
             ) {
            ((OrderMonitorCallback)entry.getValue()).appOperation(orderEnum);
        }
    }

    public void unRegisterMonitor(String key){
        if (map.containsKey(key)){
            map.remove(key);
        }
    }
}
