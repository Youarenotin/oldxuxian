package com.xuxian.marketpro.net;

import android.content.Context;

import com.ab.http.AbRequestParams;
import com.ab.util.AbMd5;
import com.ab.util.AbStrUtil;

import java.util.ArrayList;

/**
 * Created by youarenotin on 16/7/26.
 */
public class RequestParamsNet {
    private static RequestParamsNet mLib;
    protected Context mContext;

    private RequestParamsNet(Context context) {
        this.mContext = context;
    }

    public static synchronized RequestParamsNet getInstance(Context context) {
        RequestParamsNet requestParamsNet;
        synchronized (RequestParamsNet.class) {
            if (mLib == null) {
                mLib = new RequestParamsNet(context);
            }
            requestParamsNet = mLib;
        }
        return requestParamsNet;
    }

    public AbRequestParams index(int locationid, String user_id, String token) {
        AbRequestParams params = new AbRequestParams();
        params.put("locationid", locationid + "");
        if ("0".equals(user_id)) {
            user_id = "";
        }
        if (!(AbStrUtil.isEmpty(user_id) || AbStrUtil.isEmpty(token))) {
            params.put("user_id", user_id);
            params.put(NewIssRequest.TOKEN, token);
        }
        return params;
    }

    public AbRequestParams newGoodsCart(String userId, String goodsId, int goodsNum, int storeId, String token, String type) {
        AbRequestParams params = new AbRequestParams();
        params.put("userId", userId);
        params.put("goodsId", goodsId);
        params.put("goodsNum", goodsNum + "");
        params.put("storeId", storeId + "");
        params.put(NewIssRequest.TOKEN, token);
        params.put("type", type);
        return params;
    }

//    public AbRequestParams sureOrder(UserEntity userEntity, int address_id, List<ShoppingCartGoodsEntity> list, int store_id) {
//        AbRequestParams params = new AbRequestParams();
//        if (!(list == null || list.isEmpty())) {
//            PlaceAnOrdePostEntity placeAnOrdePostEntity = new PlaceAnOrdePostEntity();
//            List<GoodsOrderListEntity> goodslist = new ArrayList();
//            for (int i = 0; i < list.size(); i++) {
//                GoodsOrderListEntity listDto = new GoodsOrderListEntity();
//                listDto.setGoodsid(((ShoppingCartGoodsEntity) list.get(i)).getId().intValue());
//                listDto.setGoodsnum(((ShoppingCartGoodsEntity) list.get(i)).getCount());
//                goodslist.add(listDto);
//            }
//            if (userEntity != null) {
//                placeAnOrdePostEntity.setUserid(userEntity.getUserid());
//                placeAnOrdePostEntity.setToken(userEntity.getToken());
//                placeAnOrdePostEntity.setMobile(userEntity.getPhone());
//            }
//            if (address_id <= 0) {
//                placeAnOrdePostEntity.setAdd_id("");
//            } else {
//                placeAnOrdePostEntity.setAdd_id(address_id + "");
//            }
//            placeAnOrdePostEntity.setStoreid(store_id);
//            placeAnOrdePostEntity.setGoodslist(goodslist);
//            try {
//                params.put("order", URLEncoder.encode(JSON.toJSONString(placeAnOrdePostEntity), RequestHandler.UTF8));
//            } catch (UnsupportedEncodingException e) {
//            }
//        }
//        return params;
//    }
//
//    public AbRequestParams suborder(UserEntity userEntity, List<ShoppingCartGoodsEntity> lis, int type, CouponEntity coupon, int freight_id, int address_id, String sendtime) {
//        PlaceAnOrdePostEntity placeAnOrdePostEntity = new PlaceAnOrdePostEntity();
//        List<GoodsOrderListEntity> goodslist = new ArrayList();
//        if (!(lis == null || lis.isEmpty())) {
//            int length = lis.size();
//            for (int i = 0; i < length; i++) {
//                GoodsOrderListEntity listDto = new GoodsOrderListEntity();
//                listDto.setGoodsid(((ShoppingCartGoodsEntity) lis.get(i)).getId().intValue());
//                listDto.setSelltype(((ShoppingCartGoodsEntity) lis.get(i)).getSelltype());
//                listDto.setGoodsnum(((ShoppingCartGoodsEntity) lis.get(i)).getCount());
//                goodslist.add(listDto);
//            }
//        }
//        if (userEntity != null) {
//            placeAnOrdePostEntity.setUserid(userEntity.getUserid());
//            placeAnOrdePostEntity.setToken(userEntity.getToken());
//            placeAnOrdePostEntity.setMobile(userEntity.getPhone());
//        }
//        placeAnOrdePostEntity.setPay_type(type);
//        placeAnOrdePostEntity.setStoreid(AbPreferencesUtils.loadPrefInt(this.mContext, StoreFragmentActivity.SITE_ID, 0));
//        if (coupon != null) {
//            String sCoupon = "";
//            if (coupon.getGoods_id() > 0) {
//                sCoupon = coupon.getId() + "|" + coupon.getGoods_id();
//            } else {
//                sCoupon = coupon.getId() + "";
//            }
//            placeAnOrdePostEntity.setCoupon(sCoupon);
//        }
//        placeAnOrdePostEntity.setGoodslist(goodslist);
//        if (freight_id > 0) {
//            placeAnOrdePostEntity.setDis_id(freight_id);
//        } else {
//            placeAnOrdePostEntity.setDis_id(0);
//        }
//        if (address_id > 0) {
//            placeAnOrdePostEntity.setAdd_id(address_id + "");
//        } else {
//            placeAnOrdePostEntity.setAdd_id("");
//        }
//        if (AbStrUtil.isEmpty(sendtime)) {
//            placeAnOrdePostEntity.setSendtime("");
//        } else {
//            placeAnOrdePostEntity.setSendtime(sendtime);
//        }
//        String order = JSON.toJSONString(placeAnOrdePostEntity);
//        String encode = "";
//        try {
//            encode = URLEncoder.encode(order, RequestHandler.UTF8);
//        } catch (UnsupportedEncodingException e) {
//        }
//        AbRequestParams params = new AbRequestParams();
//        params.put("order", encode);
//        return params;
//    }

    public AbRequestParams newCartGoods(String city_id, int store_id, String user_id) {
        AbRequestParams params = new AbRequestParams();
        params.put("city_id", city_id);
        params.put("store_id", store_id + "");
        params.put("user_id", user_id);
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getGoodsInfo(int goodsId, int storeId) {
        AbRequestParams params = new AbRequestParams();
        params.put("goodsId", goodsId + "");
        params.put("storeId", storeId + "");
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams replaceStore(String userId, String token, int storeId) {
        AbRequestParams params = new AbRequestParams();
        params.put("userId", userId);
        params.put("storeId", storeId + "");
        params.put(NewIssRequest.TOKEN, token);
        return params;
    }

    public AbRequestParams getGoodsKillRemind(String userId, int storeid) {
        AbRequestParams params = new AbRequestParams();
        params.put("userId", userId);
        params.put("storeId", storeid + "");
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getStoreInfo(String lat, String lng, String city_id) {
        AbRequestParams params = new AbRequestParams();
        params.put("lat", lat);
        params.put("lng", lng);
        params.put("city_id", city_id);
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams setRemind(String seckId, String userId, int goodsId, String diffType, int storeId) {
        AbRequestParams params = new AbRequestParams();
        params.put("seckId", seckId);
        params.put("userId", userId);
        params.put("goodsId", goodsId + "");
        params.put("diffType", diffType);
        params.put("storeId", storeId + "");
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams setGoodsFragmentHeader(String cityId, int storeId) {
        AbRequestParams params = new AbRequestParams();
        params.put("cityId", cityId);
        params.put("storeId", storeId + "");
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getFreightInfo(int add_id) {
        AbRequestParams params = new AbRequestParams();
        params.put("add_id", add_id + "");
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getfreight(int store_id, int pt_id, int add_id, String user_id) {
        AbRequestParams params = new AbRequestParams();
        params.put("store_id", store_id + "");
        params.put("pt_id", pt_id + "");
        params.put("add_id", add_id + "");
        params.put("user_id", user_id);
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams createorder(String user_id, int pt_id, int store_id, int pay_type, int add_id, int dis_id, String send_time) {
        AbRequestParams params = new AbRequestParams();
        params.put("user_id", user_id);
        params.put("pt_id", pt_id + "");
        params.put("store_id", store_id + "");
        params.put("pay_type", pay_type + "");
        if (add_id <= 0) {
            params.put("add_id", "");
        } else {
            params.put("add_id", add_id + "");
        }
        if (dis_id <= 0) {
            params.put("dis_id", "");
        } else {
            params.put("dis_id", dis_id + "");
        }
        if (AbStrUtil.isEmpty(send_time)) {
            params.put("send_time", "");
        } else {
            params.put("send_time", send_time);
        }
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams pintuaninfo(int pt_id, String user_id) {
        AbRequestParams params = new AbRequestParams();
        params.put("pt_id", pt_id + "");
        params.put("user_id", user_id);
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams updateaddress(int add_id, String city, String area, double lat, double lng, String mobile, String userid, String accept_name, String l_address, String d_address) {
        AbRequestParams params = new AbRequestParams();
        if (add_id != 0) {
            params.put("add_id", add_id + "");
        } else {
            params.put("add_id", "");
        }
        params.put("city_id", city);
        params.put("area_id", area);
        params.put("lat", lat + "");
        params.put("lng", lng + "");
        params.put("mobile", mobile);
        params.put("userid", userid);
        params.put("accept_name", accept_name);
        params.put("l_address", l_address);
        params.put("d_address", d_address);
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getXianpinList(int storeid, int page) {
        AbRequestParams params = new AbRequestParams();
        params.put("store_id", storeid + "");
        params.put("page", page + "");
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getPinTuanOrder(String user_id, int page) {
        AbRequestParams params = new AbRequestParams();
        params.put("user_id", user_id);
        params.put("page", page + "");
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getSharedTitleAndContent(String url) {
        AbRequestParams params = new AbRequestParams();
        params.put("url", url);
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getlistCoupon(int store_id, String userid, String token, int page) {
        AbRequestParams params = new AbRequestParams();
        params.put("store_id", store_id + "");
        params.put("userid", userid);
        params.put(NewIssRequest.TOKEN, token);
        params.put("page", page + "");
        return params;
    }

    public AbRequestParams getcoupon(String user_id, String couponcode) {
        AbRequestParams params = new AbRequestParams();
        params.put("user_id", user_id);
        params.put("couponcode", couponcode);
        return params;
    }

    public AbRequestParams getareas(String city, String userid) {
        AbRequestParams params = new AbRequestParams();
        if (AbStrUtil.isEmpty(city)) {
            params.put("city", "");
        } else {
            params.put("city", city);
        }
        params.put("userid", userid);
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getuserinfo(String userid, String token) {
        AbRequestParams params = new AbRequestParams();
        params.put("userid", userid);
        params.put(NewIssRequest.TOKEN, token);
        return params;
    }

    public AbRequestParams getShafredOk(String url, String user_id, int store_id, String open_id, String union_id) {
        AbRequestParams params = new AbRequestParams();
        params.put("url", url);
        params.put("user_id", user_id);
        params.put("store_id", store_id + "");
        params.put("open_id", open_id + "");
        params.put("union_id", union_id + "");
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getHistoryInfo(String userid, String token, int page) {
        AbRequestParams params = new AbRequestParams();
        params.put("userid", userid);
        params.put(NewIssRequest.TOKEN, token);
        params.put("page", page + "");
        return params;
    }

    public AbRequestParams virtualStore(String city_id, String city_name) {
        AbRequestParams params = new AbRequestParams();
        if (!AbStrUtil.isEmpty(city_id)) {
            params.put("city_id", city_id);
        }
        if (!AbStrUtil.isEmpty(city_name)) {
            params.put("city_name", city_name);
        }
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getaddress(String userid) {
        AbRequestParams params = new AbRequestParams();
        params.put("userid", userid);
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams deladdress(int add_id, String userid) {
        AbRequestParams params = new AbRequestParams();
        params.put("add_id", add_id + "");
        params.put("userid", userid);
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams modaddress(int add_id, String user_id) {
        AbRequestParams params = new AbRequestParams();
        params.put("add_id", add_id + "");
        params.put("user_id", user_id);
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams login(String user_name, String password) {
        AbRequestParams params = new AbRequestParams();
        String pwd = AbMd5.MD5(password);
        params.put("user_name", user_name);
        params.put("password", pwd);
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams suremobile(String userid, String phone) {
        AbRequestParams params = new AbRequestParams();
        params.put("userid", userid);
        params.put("phone", phone);
        return params;
    }

    public String userload(String userid, int utype) {
        return NewIssRequest.userload(userid, utype);
    }

    public AbRequestParams weiXinPay(String order_no, String pay_amount, String order_id) {
        AbRequestParams params = new AbRequestParams();
        params.put("order_no", order_no);
        params.put("pay_amount", pay_amount);
        params.put("order_id", order_id);
        params.put("postmobile", "app");
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }

    public AbRequestParams getOrderDetail(String userid, String orderid, String token) {
        AbRequestParams params = new AbRequestParams();
        params.put("userid", userid);
        params.put("orderid", orderid);
        params.put(NewIssRequest.TOKEN, token);
        return params;
    }

    public AbRequestParams secondorder(String userid, String orderid, String token) {
        AbRequestParams params = new AbRequestParams();
        params.put("userid", userid);
        params.put("orderid", orderid);
        params.put(NewIssRequest.TOKEN, token);
        return params;
    }

    public AbRequestParams applyQuitOrder(String userid, String orderid, String token) {
        AbRequestParams params = new AbRequestParams();
        params.put("userid", userid);
        params.put("orderid", orderid);
        params.put(NewIssRequest.TOKEN, token);
        return params;
    }

    public AbRequestParams callcenter(String user_id, long time) {
        AbRequestParams params = new AbRequestParams();
        params.put("user_id", user_id);
        params.put("time", time + "");
        params.put(NewIssRequest.TOKEN, params.getMd5());
        return params;
    }
}
