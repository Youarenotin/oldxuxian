package com.xuxian.marketpro.libraries.util.monitor;

import com.xuxian.marketpro.presentation.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youarenotin on 16/8/23.
 */
public class UserMonitor {
    private static UserMonitor monitor;
    private Map<String, UserMonitorCallback> maps = new HashMap<>();

    public interface UserMonitorCallback {
        void appOperation(UserEntity userEntity);
    }

    public static UserMonitor getInstance() {
        if (monitor == null) {
            monitor = new UserMonitor();
        }
        return monitor;
    }

    public void issueMonitor(UserEntity userEntity) {
        for (Map.Entry entry : maps.entrySet()
                ) {
            ((UserMonitorCallback) entry.getValue()).appOperation(userEntity);
        }
    }

    public void registerUserMonitorCallback(String name, UserMonitorCallback callback) {
        maps.put(name,callback);
    }

    public boolean isRegister(String key) {
        if (maps != null)
            return maps.containsKey(key);
        return false;
    }

    public void cancleRegisterMonitorCallback(String name){
        if (maps.containsKey(name)){
            maps.remove(name);
        }
    }
}
