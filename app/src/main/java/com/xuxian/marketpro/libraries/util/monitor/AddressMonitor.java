package com.xuxian.marketpro.libraries.util.monitor;

import com.xuxian.marketpro.libraries.util.monitor.monitor.AddressCartEnum;
import com.xuxian.marketpro.presentation.entity.AddressEntity;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by youarenotin on 16/8/23.
 */
public class AddressMonitor {
    private static AddressMonitor monitor;
    private Map<String,AddressMonitorCallback> maps =new HashMap<>();

    public interface AddressMonitorCallback {
        void appOperation(AddressCartEnum addressCartEnum, AddressEntity addressEntity);
    }

    public static AddressMonitor getInstance(){
        if (monitor==null){
            monitor=new AddressMonitor();
        }
        return monitor;
    }

    public void issueMonitorCallback(AddressCartEnum addressCartEnum,AddressEntity addressEntity){
        for (Map.Entry entry:maps.entrySet()
             ) {
            ((AddressMonitorCallback)entry.getValue()).appOperation(addressCartEnum,addressEntity);
        }
    }

    public void registerMonitorCallback(String name ,AddressMonitorCallback callback ){
        maps.put(name,callback);
    }

    public void cancelMonitorCallback(String key){
        if (maps!=null&&maps.containsKey(key)){
            maps.remove(key);
        }
    }
}
