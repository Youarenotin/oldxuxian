package com.xuxian.marketpro.presentation.db;

import android.content.Context;

import com.ab.db.orm.AbDBHelper;
import com.xuxian.marketpro.presentation.entity.AddressEntity;
import com.xuxian.marketpro.presentation.entity.GoodsListEntity;
import com.xuxian.marketpro.presentation.entity.ShoppingCartGoodsEntity;
import com.xuxian.marketpro.presentation.entity.StoreEntity;
import com.xuxian.marketpro.presentation.entity.UserEntity;

/**
 * Created by youarenotin on 16/7/29.
 */
public class DBInsideHelper extends AbDBHelper {
    private static final String DBNAME="xuxian.db";
    private static final int VERSION=25;
    private static final Class<?>[] clazzs = new Class[]{
            GoodsListEntity.class,
            UserEntity.class,
            ShoppingCartGoodsEntity.class,
//            CouponEntity.class,
            StoreEntity.class,
//            CompaniesEntity.class,
//            ImageItemEntity.class,
            AddressEntity.class
    };


    /**
     * 初始化一个AbSDDBHelper.
     *
     * @param context      应用context
     * @param name         数据库名
     * @param factory      数据库查询的游标工厂
     * @param version      数据库的新版本号
     * @param modelClasses 要初始化的表的对象
     */
    public DBInsideHelper(Context context) {
        super(context, DBNAME, null, VERSION, clazzs);
    }
}
