package com.xuxian.marketpro.libraries.util.monitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youarenotin on 16/8/23.
 */
public class PhoneMonitor {
    private static PhoneMonitor monitor;
    private Map<String, PhoneMonitorCallback> maps = new HashMap<>();

    public static PhoneMonitor getInstance() {
        if (monitor == null) {
            monitor = new PhoneMonitor();
        }
        return monitor;
    }

    public void register(String name, PhoneMonitorCallback callback) {
        if (maps.containsKey(name)) {
            return;
        }
        maps.put(name, callback);
    }

    public boolean isRegister(String name) {
        if (maps != null) {
            return maps.containsKey(name);
        }
        return false;
    }

    public void issueMonitors(String phone) {
        for (String key : maps.keySet()) {
            maps.get(key).appOperation(phone);
        }
    }

  public   interface PhoneMonitorCallback {
        void appOperation(String phone);
    }

    public void cancelRegister(String name) {
        if (maps.containsKey(name)) {
            maps.remove(name);
        }
    }
}
