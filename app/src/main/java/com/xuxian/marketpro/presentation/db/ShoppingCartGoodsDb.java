package com.xuxian.marketpro.presentation.db;

import android.database.sqlite.SQLiteOpenHelper;

import com.ab.db.orm.dao.AbDBDaoImpl;
import com.xuxian.marketpro.presentation.entity.ShoppingCartGoodsEntity;

/**
 * Created by youarenotin on 16/7/27.
 */
public class ShoppingCartGoodsDb extends AbDBDaoImpl<ShoppingCartGoodsEntity> {
    public ShoppingCartGoodsDb(SQLiteOpenHelper dbHelper, Class<ShoppingCartGoodsEntity> clazz) {
        super(dbHelper, clazz);
    }

}
