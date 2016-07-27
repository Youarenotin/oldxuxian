package com.xuxian.marketpro.presentation.db;

import android.database.sqlite.SQLiteOpenHelper;

import com.ab.db.orm.dao.AbDBDaoImpl;
import com.xuxian.marketpro.presentation.entity.StoreEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/7/27.
 */
public class StoreDb extends AbDBDaoImpl<StoreEntity> {
    public StoreDb(SQLiteOpenHelper dbHelper, Class<StoreEntity> clazz) {
        super(dbHelper, clazz);
    }

    public void saveData(StoreEntity storeEntity){
        startReadableDatabase();
        try {
            insert(storeEntity,false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase();
        }
    }

    public  void deleteAllData(){
        startWritableDatabase(false);
        try {
            deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase();
        }
    }

    public void updateData(StoreEntity storeEntity){
        startReadableDatabase();
        try {
            update(storeEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase();
        }
    }


    public StoreEntity queryData() {
        startReadableDatabase();
        StoreEntity storeEntity;
        try {
            List<StoreEntity> list = queryList();
            if (list == null || list.size() == 0) {
                closeDatabase();
                return null;
            }
            storeEntity = (StoreEntity) list.get(0);
            return storeEntity;
        } catch (Exception e) {
            return null;
        } finally {
            closeDatabase();
        }
    }

    public List<StoreEntity> queryDataAll() {
        startReadableDatabase();
        try {
            List<StoreEntity> splashDtos = queryList();
            return splashDtos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeDatabase();
        }
    }

    public boolean isExists(int id) {
        if (queryOneData(id) != null) {
            return true;
        }
        return false;
    }

    public StoreEntity queryOneData(int id) {
        startReadableDatabase();
        StoreEntity storeEntity;
        try {
            storeEntity = (StoreEntity) queryOne(id);
            return storeEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeDatabase();
        }
    }

    public List<StoreEntity> queryConditions(String city) {
        startReadableDatabase();
        try {
            List<StoreEntity> splashDtos = queryList("city_name=?", new String[]{city});
            return splashDtos;
        } catch (Exception e) {
            return null;
        } finally {
            closeDatabase();
        }
    }
}
