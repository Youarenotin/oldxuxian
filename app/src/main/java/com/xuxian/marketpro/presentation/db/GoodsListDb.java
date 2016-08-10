package com.xuxian.marketpro.presentation.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ab.db.orm.dao.AbDBDaoImpl;
import com.xuxian.marketpro.presentation.entity.GoodsListEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/9.
 */
public class GoodsListDb extends AbDBDaoImpl<GoodsListEntity> {
    public GoodsListDb(Context context, Class<GoodsListEntity> clazz) {
        super(new DBInsideHelper(context), clazz);
    }

    public void saveData(GoodsListEntity goodsListEntity){
        startReadableDatabase();
        try
        {
            insert(goodsListEntity,false);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            closeDatabase();
        }
    }

    public void updateData(GoodsListEntity goodsListEntity){
        startReadableDatabase();
        try{
            update(goodsListEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            closeDatabase();
        }
    }

    public List<GoodsListEntity> queryAllData(){
        startReadableDatabase();
        try {
            return queryList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            closeDatabase();
        }
    }

    public GoodsListEntity queryOneData(int id){
        startReadableDatabase();
        try {
            List<GoodsListEntity> goodsListEntities = queryList(" id = ? ", new String[]{String.valueOf(id)});
            if (goodsListEntities==null || goodsListEntities.isEmpty()){
                closeDatabase();
                return null;
            }
            return goodsListEntities.get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            closeDatabase();
        }
    }

    public boolean isExist(int id){
        startReadableDatabase();
         return queryOneData(id) != null;
    }
}
