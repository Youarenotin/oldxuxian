package com.xuxian.marketpro.activity.tab.goods;

import android.view.View;

import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.alibaba.fastjson.JSON;
import com.xuxian.marketpro.activity.store.StoreFragmentActivity;
import com.xuxian.marketpro.activity.tab.TabMainFragmentActivity;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.presentation.db.UserDb;

/**
 * Created by youarenotin on 16/8/10.
 */
public class BannerTpyeOnClickListener implements View.OnClickListener {
    private String _url;
    private String aft_url_and;
    private String aft_url_ask;
    private int bannerTpye;
    private String message;
    private int store_id;
    private TabMainFragmentActivity tabMainFragmentActivity;
    private String target;
    private UserDb userDb;
    private String user_id;

    public BannerTpyeOnClickListener(TabMainFragmentActivity tabMainFragmentActivity, int bannerTpye, String message) {
        this.aft_url_and = "&kill=%s&user_id=%s&store_id=%s";
        this.aft_url_ask = "?kill=%s&user_id=%s&store_id=%s";
        this.bannerTpye = bannerTpye;
        this.message = message;
        this.tabMainFragmentActivity = tabMainFragmentActivity;
        this.userDb = new UserDb(tabMainFragmentActivity);
    }

    @Override
    public void onClick(View v) {
        try {
            switch (this.bannerTpye) {
                case 1 /*1*/:
                    int goods_id = 0;
                    try {
                        if (!AbStrUtil.isEmpty(this.message)) {
                            goods_id = Integer.valueOf(this.message);
                        }
//                        ActivityUtil.startGoodsDetailsActivity(this.tabMainFragmentActivity, goods_id);
                        return;
                    } catch (Exception e) {
                        return;
                    }
                case 2 /*2*/:
//                    ClassifyEntity classifyEntity = (ClassifyEntity) JSON.parseObject(this.message, ClassifyEntity.class);
//                    ActivityUtil.startClassifyDetailsActivity(this.tabMainFragmentActivity, classifyEntity.getClassifyname(), classifyEntity.getClassifyid());
//                    return;
                case 3 /*3*/:
                    this.store_id = AbPreferenceUtils.loadPrefInt(this.tabMainFragmentActivity, StoreFragmentActivity.SITE_ID, 0);
                    this.user_id = AbPreferenceUtils.loadPrefString(this.tabMainFragmentActivity,"USER_ID", "0");
                    if (this.message.contains("?")) {
                        this._url = String.format(this.aft_url_and, "1", this.user_id, this.store_id);
                    } else {
                        this._url = String.format(this.aft_url_ask, "1", this.user_id, this.store_id);
                    }
                    this.target = this.message + this._url;
//                    ActivityUtil.startMessageWebViewActivity(this.tabMainFragmentActivity, this.target, CoinPacketExtension.NAMESPACE, false);
                    return;
                case 4 /*4*/:
//                    ActivityUtil.startActionView(this.tabMainFragmentActivity, this.message);
                    return;
                case 6 /*6*/:
                    if (this.tabMainFragmentActivity != null) {
//                        this.tabMainFragmentActivity.personalCenterFregment();
                        return;
                    }
                    return;
                case 7 /*7*/:
                    if (this.userDb.isLogin()) {
//                        ActivityUtil.startMyOrderActivity(this.tabMainFragmentActivity);
                        return;
                    } else {
//                        ActivityUtil.startLoginActivity(this.tabMainFragmentActivity);
                        return;
                    }
                case 8 /*8*/:
                    this.store_id = AbPreferenceUtils.loadPrefInt(this.tabMainFragmentActivity, StoreFragmentActivity.SITE_ID, 0);
                    this.user_id = AbPreferenceUtils.loadPrefString(this.tabMainFragmentActivity, "USER_ID", "0");
                    if (this.message.contains("?")) {
                        this._url = String.format(this.aft_url_and, "1", this.user_id, this.store_id);
                    } else {
                        this._url = String.format(this.aft_url_ask, "1", this.user_id, this.store_id);
                    }
                    this.target = this.message + this._url;
//                    ActivityUtil.startMessageWebViewActivity(this.tabMainFragmentActivity, this.target, CoinPacketExtension.NAMESPACE, true);
                    return;
                case 9 /*9*/:
                    if (this.userDb.isLogin()) {
//                        ActivityUtil.startXianPinActivity(this.tabMainFragmentActivity);
                        return;
                    } else {
//                        ActivityUtil.startLoginActivity(this.tabMainFragmentActivity);
                        return;
                    }
                case 10 /*10*/:
                    if (this.userDb.isLogin()) {
//                        ActivityUtil.startXuXianMemberActivity(this.tabMainFragmentActivity);
                        return;
                    } else {
//                        ActivityUtil.startLoginActivity(this.tabMainFragmentActivity);
                        return;
                    }
                case 13 /*13*/:
                    if (this.userDb.isLogin()) {
//                        ActivityUtil.startFlashSaleFragmentActivity(this.tabMainFragmentActivity);
                        return;
                    } else {
//                        ActivityUtil.startLoginActivity(this.tabMainFragmentActivity);
                        return;
                    }
                case 14 /*14*/:
                    if (this.userDb.isLogin()) {
//                        ActivityUtil.startUserCardsActivity(this.tabMainFragmentActivity);
                        return;
                    } else {
//                        ActivityUtil.startLoginActivity(this.tabMainFragmentActivity);
                        return;
                    }
                case 15 /*15*/:
                    if (this.userDb.isLogin()) {
//                        ActivityUtil.startXuXianMallActivity(this.tabMainFragmentActivity);
                        return;
                    } else {
//                        ActivityUtil.startLoginActivity(this.tabMainFragmentActivity);
                        return;
                    }
                default:
                    return;
            }
        } catch (Exception e2) {
            AbToastUtil.showToast(this.tabMainFragmentActivity, this.message + "==" + this.bannerTpye);
        }
        AbToastUtil.showToast(this.tabMainFragmentActivity, this.message + "==" + this.bannerTpye);
    }
}
