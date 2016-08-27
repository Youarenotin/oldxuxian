package com.xuxian.marketpro.libraries.util.monitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youarenotin on 16/8/27.
 */
public class ForumListMonitor {
    public static final int INSTALL_KEY = 0;
    public static final int UNINSTALL_KEY = 1;
    private static ForumListMonitor mMonitor;
    private static Map<String, ForumListMonitorCallback> mMonitorMap;

    static {
        mMonitorMap = new HashMap();
    }

    public static Map<String, ForumListMonitorCallback> getmMonitorMap() {
        return mMonitorMap;
    }

    public void IssuedMonitor(boolean isforumList) {
        for (Map.Entry entry : mMonitorMap.entrySet()) {
            ((ForumListMonitorCallback) entry.getValue()).appOperation(isforumList);
        }
    }

    public static ForumListMonitor getInstance() {
        if (mMonitor == null) {
            mMonitor = new ForumListMonitor();
        }
        return mMonitor;
    }

    public void register(ForumListMonitorCallback listener, String key) {
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

    public interface ForumListMonitorCallback{
        void appOperation(boolean isforumList);
    }
}
