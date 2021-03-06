package com.xuxian.marketpro.libraries.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.CityListActivity;
import com.xuxian.marketpro.activity.ClassifyActivity;
import com.xuxian.marketpro.activity.ClassifyDetailsActivity;
import com.xuxian.marketpro.activity.FindActivity;
import com.xuxian.marketpro.activity.LoginActivity;
import com.xuxian.marketpro.activity.MessageWebViewActivity;
import com.xuxian.marketpro.activity.MyOrderActivity;
import com.xuxian.marketpro.activity.PersonalInformationActivity;
import com.xuxian.marketpro.activity.RegisteredPhoneCodeActivity;
import com.xuxian.marketpro.activity.SchoolCityActivity;
import com.xuxian.marketpro.activity.SetActivity;
import com.xuxian.marketpro.activity.StoreDetailsActivity;
import com.xuxian.marketpro.activity.UserCouponsActivity;
import com.xuxian.marketpro.activity.XianPinPopupActivity;
import com.xuxian.marketpro.activity.store.StoreFragmentActivity;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.activity.tab.TabMainFragmentActivity;
import com.xuxian.marketpro.activity.tab.forums.activity.ForumListActivity;
import com.xuxian.marketpro.presentation.entity.CityEntity;
import com.xuxian.marketpro.presentation.entity.ForumsInfoEntity;
import com.xuxian.marketpro.presentation.entity.ForumsInfoEntity.DataEntity.ForumsEntity;
import com.xuxian.marketpro.presentation.entity.OrderEntity;
import com.xuxian.marketpro.presentation.entity.StoreEntity;
import com.xuxian.marketpro.presentation.entity.UserEntity;

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
     * 附近Activity
     * @param mContext
     * @param forums
     */
    public static void startForumListActivity(Context mContext, ForumsEntity forums) {
        Intent in = new Intent(mContext, ForumListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SuperSherlockActivity.INTENT_OBJECT, forums);
        in.putExtra(SuperSherlockActivity.INTENT_BUNDLE, bundle);
        mContext.startActivity(in);
    }

    /**
     * 登录Activity
     * @param mContext
     */
    public static void startLoginActivity(Context mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

    /**
     * 找回Activity
     * @param mContext
     */
    public static void startFindActivity(Context mContext) {
        mContext.startActivity(new Intent(mContext,FindActivity.class));
    }

    /**
     * 注册Activity
     * @param mContext
     */
    public static void startRegisterActivity(Context mContext) {
        mContext.startActivity(new Intent(mContext,RegisteredPhoneCodeActivity.class));
    }

    /**
     * 查看注册时服务条款webview
     * @param mContext
     * @param url
     * @param title
     * @param share
     */
    public static void startMessageWebViewActivity(Context mContext, String url, String title, boolean share) {
        Intent intent = new Intent(mContext,MessageWebViewActivity.class);
        intent.putExtra(MessageWebViewActivity.INTENT_SHARE,share);
        intent.putExtra(MessageWebViewActivity.INTENT_TITLE,title);
        intent.putExtra(MessageWebViewActivity.INTENT_URL,url);
        mContext.startActivity(intent);
    }

    /**
     * 浏览器
     * @param mContext
     * @param message
     */
    public static void startActionView(Context mContext, String message) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(message));
        mContext.startActivity(intent);
    }

    /**
     * 启动个人信息Activity
     * @param mContext
     * @param userEntity
     */
    public static void startPersonalInformationActivity(Context mContext, UserEntity userEntity) {
        Intent intent = new Intent(mContext,PersonalInformationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SuperSherlockActivity.INTENT_OBJECT,userEntity);
        intent.putExtra(SuperSherlockActivity.INTENT_BUNDLE,bundle);
        mContext.startActivity(intent);
    }

    /**
     * 启动爱好选择Activity
     * @param mContext
     * @param s
     */
    public static void startLoveActivity(Context mContext, String s) {
    }

    /**
     * 启动学校选择Activity
     * @param mContext
     */
    public static void startSchoolCityActivity(Context mContext) {
        ((Activity)mContext).startActivityForResult(new Intent(mContext,SchoolCityActivity.class),PersonalInformationActivity.CITY);
    }

    public static void startVerificationActivity(Context mContext) {

    }

    /**
     * 鲜拼 引导Activity
     * @param mContext
     */
    public static void StartXianPinPopupActivity(Context mContext) {
        mContext.startActivity(new Intent(mContext,XianPinPopupActivity.class));
    }

    /**
     * 系统相册图片选择
     * @param mContext
     */
    public static void startSystemImageActivity(Context mContext) {

    }

    public static void startForumListDetailsActivity(Context mContext, String tid, String fid) {

    }

    /**
     * 我的订单
     * @param context
     */
    public static void startMyOrderActivity(Context context) {
        context.startActivity(new Intent(context,MyOrderActivity.class));
    }

    /**
     * 会员
     * @param context
     */
    public static void startXuXianMemberActivity(Context context) {

    }

    /**
     * 优惠券
     * @param context
     */
    public static void startMyCouponsActivity(Context context) {
        context.startActivity(new Intent(context,UserCouponsActivity.class));
    }

    /**
     * 订单详情
     * @param mContext
     * @param orderEntity
     */
    public static void startOrderDetailsActivity(Context mContext, OrderEntity orderEntity) {

    }

    public static void startMessagerActivity(Context mContext) {
    }

    /**
     * 设置
     * @param context
     */
    public static void startSetActivity(Context context) {
        context.startActivity(new Intent(context,SetActivity.class));
    }
}
