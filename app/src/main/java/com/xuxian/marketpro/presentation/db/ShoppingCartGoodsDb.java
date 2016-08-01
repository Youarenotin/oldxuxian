package com.xuxian.marketpro.presentation.db;

import android.content.Context;

import com.ab.db.orm.dao.AbDBDaoImpl;
import com.xuxian.marketpro.presentation.entity.ShoppingCartGoodsEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/7/27.
 */
public class ShoppingCartGoodsDb extends AbDBDaoImpl<ShoppingCartGoodsEntity> {
    public ShoppingCartGoodsDb(Context ct) {
        super(new DBInsideHelper(ct), ShoppingCartGoodsEntity.class);
    }

    public void savaData(ShoppingCartGoodsEntity vo){
        startReadableDatabase();
        try{
            insert(vo,true);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDatabase();
        }
    }

    public void deleteAllData(){
        startWritableDatabase(false);
        try
        {
            deleteAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDatabase();
        }
    }

    public void deleteByIdData(int id){
        startWritableDatabase(false);
        try{
            deleteByIdData(id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDatabase();
        }
    }

    public void deleteByIdAndUserIdData(int id , String user_id){
        startWritableDatabase(false);
        try{
            delete(" id=? and userid = ? ",new String[]{String.valueOf(id),user_id});
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDatabase();
        }
    }

    public void updateData(ShoppingCartGoodsEntity vo){
        startReadableDatabase();
        try{
            update(vo);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDatabase();
        }
    }

    public List<ShoppingCartGoodsEntity> queryAllData(String uid){
        startReadableDatabase();
        try{
            List<ShoppingCartGoodsEntity> list = queryList(" userid=? ", new String[]{uid});
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            closeDatabase();
        }
    }

    public boolean isExit(String uid , int id ){
        if (queryOneData(uid,id)!=null){
            return  true;
        }
        return false;
    }

    public ShoppingCartGoodsEntity queryOneData(String uid , int id ){
        startReadableDatabase();
        try{
            List<ShoppingCartGoodsEntity> shoppingCartGoodsEntities = queryList(" userid = ? and id = ? ", new String[]{uid, String.valueOf(id)});
            return shoppingCartGoodsEntities.get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            closeDatabase();
        }
    }

    public void savaShoppingCarData()
}
