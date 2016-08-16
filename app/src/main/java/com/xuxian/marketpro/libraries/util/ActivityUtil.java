package com.xuxian.marketpro.libraries.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.CityListActivity;
import com.xuxian.marketpro.activity.ClassifyActivity;
import com.xuxian.marketpro.activity.ClassifyDetailsActivity;
import com.xuxian.marketpro.activity.StoreDetailsActivity;
import com.xuxian.marketpro.activity.store.StoreFragmentActivity;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.activity.tab.TabMainFragmentActivity;
import com.xuxian.marketpro.activity.tab.forums.activity.ForumListActivity;
import com.xuxian.marketpro.presentation.entity.CityEntity;
import com.xuxian.marketpro.presentation.entity.ForumsInfoEntity;
import com.xuxian.marketpro.presentation.entity.ForumsInfoEntity.DataEntity.ForumsEntity;
import com.xuxian.marketpro.presentation.entity.StoreEntity;

/**
 * Created by youarenotin on 16/7/27.
 */
public class ActivityUtil {
    //基本上activity切换的动画都在xml里配置

    public static void startCityListActivity(Context context) {
        context.startActivity(new Intent(context, CityListActivity.class));
    }


    /**
     * 启动选择定位选择店面activity
     * @param context
     * @param cityInfoEntity
     */
    public static void startStoreFragmentActivity(Context context, CityEntity.DataEntity.CityInfoEntity cityInfoEntity) {
        Intent in = new Intent(context, StoreFragmentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SuperSherlockActivity.INTENT_OBJECT, cityInfoEntity);
        in.putExtras(bundle);
        context.startActivity(in);
        ((Activity) context).overridePendingTransition(R.anim.fade, R.anim.hold);
    }

    /**
     * 启动商店详情activity
     * @param context
     * @param storeEntity
     */
    public static void startStoreDetailsActivity(Context context, StoreEntity storeEntity) {
        Intent in = new Intent(context, StoreDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SuperSherlockActivity.INTENT_OBJECT, storeEntity);
        in.putExtra(SuperSherlockActivity.INTENT_BUNDLE, bundle);
        context.startActivity(in);
    }

    /**
     * 启动店面activity
     * @param context
     */
    public static void startTabMainActivity(Context context) {
        context.startActivity(new Intent(context, TabMainFragmentActivity.class));
    }

    /**
     * 启动分类Activity
     * @param context
     */
    public static void startClassifyActivity(Context context) {
        context.startActivity(new Intent(context, ClassifyActivity.class));
        ((Activity) context).overridePendingTransition(R.anim.fade, R.anim.hold);
    }

    /**
     * 启动分类详细Activity
     * @param context
     * @param title
     * @param id
     */
    public static void startClassifyDetailsActivity(Context context, String title, String id) {
        Intent in = new Intent(context, ClassifyDetailsActivity.class);
        in.putExtra(ClassifyDetailsActivity.INTENT_ACTION_TITLE, title);
        in.putExtra(ClassifyDetailsActivity.INTENT_CATEGORYID, id);
        context.startActivity(in);
    }

    /**
     *
     * @param mContext
     * @param entity
     */
    public static void startForumListActivity(Context mContext, ForumsEntity forums) {
        Intent in = new Intent(mContext, ForumListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SuperSherlockActivity.INTENT_OBJECT, forums);
        in.putExtra(SuperSherlockActivity.INTENT_BUNDLE, bundle);
        mContext.startActivity(in);
    }
}
