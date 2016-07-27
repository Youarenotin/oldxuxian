package com.xuxian.marketpro.libraries.util;

import android.content.Context;
import android.content.Intent;

import com.xuxian.marketpro.activity.CityListActivity;

/**
 * Created by youarenotin on 16/7/27.
 */
public class ActivityUtil {




    public static void startCityListActivity(Context context) {
        context.startActivity(new Intent(context, CityListActivity.class));
    }
}
