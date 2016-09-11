package com.xuxian.marketpro.libraries.util.monitor;

/**
 * Created by youarenotin on 16/9/6.
 */
public class UseCouponMonitor {
    private static UseCouponMonitor monitor;

    public static UseCouponMonitor getInstance() {
        if (monitor==null)
            monitor=new UseCouponMonitor();
        return monitor;
    }

    public void IssuedMonitor(Object o) {

    }
}
