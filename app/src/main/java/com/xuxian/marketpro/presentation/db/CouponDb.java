package com.xuxian.marketpro.presentation.db;

import android.content.Context;

import com.ab.db.orm.dao.AbDBDaoImpl;
import com.xuxian.marketpro.presentation.entity.CouponEntity;

/**
 * Created by youarenotin on 16/9/8.
 */
public class CouponDb extends AbDBDaoImpl<CouponEntity>{
    public CouponDb(Context context) {
        super(new DBInsideHelper(context), CouponEntity.class);
    }


}
