package com.xuxian.marketpro.presentation.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ab.db.orm.dao.AbDBDaoImpl;
import com.xuxian.marketpro.presentation.entity.ShoppingCartGoodsEntity;

/**
 * Created by youarenotin on 16/7/27.
 */
public class ShoppingCartGoodsDb extends AbDBDaoImpl<ShoppingCartGoodsEntity> {
    public ShoppingCartGoodsDb(Context ct) {
        super(new DBInsideHelper(ct), ShoppingCartGoodsEntity.class);
    }

}
