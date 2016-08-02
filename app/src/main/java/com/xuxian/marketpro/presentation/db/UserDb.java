package com.xuxian.marketpro.presentation.db;

import android.content.Context;

import com.ab.db.orm.dao.AbDBDaoImpl;
import com.xuxian.marketpro.presentation.entity.UserEntity;

import java.util.List;

/**
 * 作者：lubo on 8/2 0002 13:22
 * 邮箱：lubo_wen@126.com
 */
public class UserDb extends AbDBDaoImpl<UserEntity>{
    public UserDb(Context ct){
        super(new DBInsideHelper(ct),UserEntity.class);
    }

    public void savaData(UserEntity entity){
        startReadableDatabase();
        try{
            insert(entity,false);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDatabase();
        }
    }

    public void deleteAllData(){
        startWritableDatabase(false);
        try{
            deleteAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDatabase();
        }
    }

    public int  updateData(UserEntity entity){
        startReadableDatabase();
        int update;
        try {
            update=update(entity);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDatabase();
        }
    }

    public UserEntity queryData(){
        startReadableDatabase();
        try {
            List<UserEntity> list =queryList();
            if (list==null || list.size()==0){
                closeDatabase();
                return null;
            }
            return list.get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            closeDatabase();
        }
    }

}
