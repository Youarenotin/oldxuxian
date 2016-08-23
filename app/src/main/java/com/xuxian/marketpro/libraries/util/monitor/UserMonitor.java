package com.xuxian.marketpro.libraries.util.monitor;

import com.xuxian.marketpro.presentation.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youarenotin on 16/8/23.
 */
public class UserMonitor {
    private static UserMonitor monitor;
    private Map<String,UserMonitorCallback> maps = new HashMap<>();

    public interface UserMonitorCallback{
        void appOperation(UserEntity userEntity);
    }

    public static UserMonitor getInstance(){
        if (monitor==null){
            monitor=new UserMonitor();
        }
        return monitor;
    }
}
