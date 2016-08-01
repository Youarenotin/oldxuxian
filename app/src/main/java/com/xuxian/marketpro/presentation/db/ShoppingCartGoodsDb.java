package com.xuxian.marketpro.presentation.db;

import android.content.Context;

import com.ab.db.orm.dao.AbDBDaoImpl;
import com.xuxian.marketpro.presentation.entity.GoodsListEntity;
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

    public void savaShoppingCarData(GoodsListEntity goodsListEntity,String uid , int count){
        int shop_car_goods_num = 0;
        if (goodsListEntity!=null){
            ShoppingCartGoodsEntity data = queryOneData(uid, goodsListEntity.getId());
            if (data != null){
                shop_car_goods_num=data.getCount();
            }
            if (isExit(uid, goodsListEntity.getId())){
                deleteByIdAndUserIdData(goodsListEntity.getId(),uid);
            }
            savaData(new ShoppingCartGoodsEntity(
                    goodsListEntity.getGoods_type()
                    ,""
                    ,""
                    ,""
                    ,goodsListEntity.getUnit()
                    ,goodsListEntity.getNewimg()
                    ,goodsListEntity.getId()
                    ,goodsListEntity.getTitle()
                    ,goodsListEntity.getPrice()
                    ,goodsListEntity.getIcon()
                    ,goodsListEntity.getStore_nums()
                    ,goodsListEntity.getSold_num()
                    ,goodsListEntity.getMarket_price()
                    ,""
                    ,0
                    ,goodsListEntity.getSelltype()
                    ,goodsListEntity.getTipsimg()
                    ,goodsListEntity.getPhonetips()
                    ,goodsListEntity.getStarttime()
                    ,goodsListEntity.getEndtime()
                    ,goodsListEntity.getShow()
                    ,count+shop_car_goods_num,
                    "",goodsListEntity.getDetails2()
                    ,goodsListEntity.getDetails2()
                    ,uid));
        }
    }
}
