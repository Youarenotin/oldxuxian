package com.xuxian.marketpro.libraries.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.CityListActivity;
import com.xuxian.marketpro.activity.store.StoreFragmentActivity;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.presentation.entity.CityEntity;

/**
 * Created by youarenotin on 16/7/27.
 */
public class ActivityUtil {




    public static void startCityListActivity(Context context) {
        context.startActivity(new Intent(context, CityListActivity.class));
    }




    public static void startStoreFragmentActivity(Context context, CityEntity.DataEntity.CityInfoEntity cityInfoEntity) {
        Intent in = new Intent(context, StoreFragmentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SuperSherlockActivity.INTENT_OBJECT, cityInfoEntity);
        in.putExtras(bundle);
        context.startActivity(in);
        ((Activity) context).overridePendingTransition(R.anim.fade, R.anim.hold);
    }
}
