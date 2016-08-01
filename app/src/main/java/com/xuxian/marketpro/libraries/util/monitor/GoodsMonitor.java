package com.xuxian.marketpro.libraries.util.monitor;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：lubo on 8/1 0001 17:17
 * 邮箱：lubo_wen@126.com
 */
public class GoodsMonitor {
    private static Map<String ,GoodsMonitorCallback > monitorMaps=new HashMap<>();
    private static GoodsMonitor monitor;

   public  interface GoodsMonitorCallback{
        void appOprate(monitor.GoodsEnum goodsEnum);
    }

    public static GoodsMonitor getInstance(){
        if (monitor==null){
            monitor=new GoodsMonitor();
        }
        return monitor;
    }

    public static void registerGoodsMonitor(String key , GoodsMonitorCallback callback){
        if (monitorMaps.containsKey(key)){
            return ;
        }
        monitorMaps.put(key,callback);
    }

    public static void removeAllGoodsMonitorCallback(){
        monitorMaps.clear();
    }

    public static void removeOneGoodsMonitorCallback(String key){
        if (!monitorMaps.containsKey(key)){
            return;
        }
        monitorMaps.remove(key);
    }

    public static void issueGoodsMonitorCallback(monitor.GoodsEnum goodsEnum){
        for (String key :monitorMaps.keySet()){
            monitorMaps.get(key).appOprate(goodsEnum);
        }
    }
}
