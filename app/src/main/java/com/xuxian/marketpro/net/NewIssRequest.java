package com.xuxian.marketpro.net;

import android.content.Context;

import com.ab.http.AbHttpClient;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.xuxian.marketpro.net.httpclient.HttpRequestException;
import com.xuxian.marketpro.net.httpclient.NormalHttpClient;
import com.xuxian.marketpro.net.httpclient.ParameterList;

/**
 * Created by youarenotin on 16/7/26.
 */
public class NewIssRequest {
    public static String ACCOUNTACTIVITY = null;
    public static final String ADDRESS = "http://www.xuxian.com/index.php?controller=point&action=address";
    public static final String ALIPAY;
    public static String APPLYQUITORDER = null;
    public static final String BBS_XUXIAN_COM = "http://bbs.xuxian.com";
    public static String CALLCENTER = null;
    public static String CONSUMPTION = null;
    public static String CREATE = null;
    public static String CREATEORDER = null;
    public static String DELADDRESS = null;
    public static final String DOADD = "http://bbs.xuxian.com/index.php?m=api&c=post&a=doadd";
    public static final String DOREPLY = "http://bbs.xuxian.com/index.php?m=api&c=post&a=doreply";
    public static String EVALUATIONORDER = null;
    public static String EXCHANGECOLLECTIONGIFT = null;
    public static String EXCLUSIVE_GOOD = null;
    public static String FINDPASSWORD = null;
    public static final String FLASH_SALE_TIME;
    public static final String FORUM_HOME_PAGE = "http://bbs.xuxian.com/?m=api&c=index&a=run";
    public static final String FORUM_LIST = "http://bbs.xuxian.com/?m=api&c=thread";
    public static final String FORUM_LIST_DETAILS = "http://bbs.xuxian.com/?m=api&c=read&a=run";
    public static final String FORUM_LIST_DETAILS_DESC = "http://bbs.xuxian.com/?m=api&c=read&a=dataRun";
    public static String GETADDRESS = null;
    public static String GETAREAS = null;
    public static String GETCODE = null;
    public static final String GETCOUPON = "http://www.xuxian.com/index.php?controller=simple&action=getcoupon";
    public static String GETFREIGHT = null;
    public static String GETFREIGHTINFO = null;
    public static String GETGOODSINFO = null;
    public static String GETGOODSKILLREMIND = null;
    public static String GETGOODSLIST = null;
    public static String GETHISTORYINFO = null;
    public static String GETHONGBAO = null;
    public static String GETLISTCOUPON = null;
    public static String GETORDERDETAIL = null;
    public static String GETORDERS = null;
    public static final String GETPHONECODE;
    public static String GETPTLIST = null;
    public static String GETSTORE = null;
    public static String GETSTORECOMINFO = null;
    public static String GETSTOREINFO = null;
    public static String GETUSERCARDSINFO = null;
    public static String GETUSERINFO = null;
    public static String GET_SHARED_CONTENT = null;
    public static String GET_SHARED_OK = null;
    public static String GOODS_FRAGMENT_HEADER = null;
    public static String INDEX = null;
    public static String LOGIN = null;
    public static final String LOGTAG = "NewIssRequest";
    public static String MEMBER = null;
    public static String MOBILE_XUXIAN_COM = null;
    public static String MODADDRESS = null;
    public static final String MODIFYPWDORUNAME;
    public static String MYPOINT = null;
    public static String NEWCARTGOODS = null;
    public static String NEWGOODSCART = null;
    public static String ORDERINFO = null;
    public static String PINTUNA_INFO = null;
    public static String POINTLIST = null;
    public static final String REGISTER;
    public static String REPLACESTORE = null;
    public static final String SECKILL_GOODS;
    public static String SECONDORDER = null;
    public static String SETCODE = null;
    public static String SETTOKEN = null;
    public static final String SET_REMIND;
    public static String SHAREORDER = null;
    public static String SUBORDER = null;
    public static String SUREMOBILE = null;
    public static String SUREORDER = null;
    public static final String TOKEN = "token";
    public static String UPDATEADDRESS = null;
    public static String UPDATEU = null;
    public static String UPINSERT = null;
    public static final String UPLOAD_IMAGE = "http://bbs.xuxian.com?m=bbs&c=upload&a=dorun&_json=1";
    public static String USERCOUPON = null;
    public static final String VAR;
    public static String VIRTUALSTORE = null;
    public static final String WEIXIN;
    public static final String WWW_XUXINA_COM = "http://www.xuxian.com/index.php";
    public static final String XIAN_QIANG_MIJI;
    private static NewIssRequest issRequest;
    private NormalHttpClient mClient;
    private ParameterList parameterList;

    static {
//        VAR = "?ver=2015121401&__t=" + System.currentTimeMillis();
        VAR = "?ver=2015112001&__t=" + System.currentTimeMillis();
        MOBILE_XUXIAN_COM = "http://mobile.xuxian.com";
        ALIPAY = MOBILE_XUXIAN_COM + "/alipay/notifyAlipay";
        WEIXIN = MOBILE_XUXIAN_COM + "/wxpay/logicHandel/" + VAR;
        GOODS_FRAGMENT_HEADER = MOBILE_XUXIAN_COM + "/index/getList/" + VAR;
        LOGIN = MOBILE_XUXIAN_COM + "/auth/login/" + VAR;
        SETCODE = MOBILE_XUXIAN_COM + "/user/setCode/" + VAR;
        GETPHONECODE = MOBILE_XUXIAN_COM + "/user/getPhoneCode/" + VAR;
        REGISTER = MOBILE_XUXIAN_COM + "/user/register/" + VAR;
        MODIFYPWDORUNAME = MOBILE_XUXIAN_COM + "/user/modifyPwdOrUname/" + VAR;
        SUREMOBILE = MOBILE_XUXIAN_COM + "/user/suremobile/" + VAR;
        FINDPASSWORD = MOBILE_XUXIAN_COM + "/user/findPassword/" + VAR;
        UPDATEU = MOBILE_XUXIAN_COM + "/user/updateu/" + VAR;
        SETTOKEN = MOBILE_XUXIAN_COM + "/user/settoken/" + VAR;
        USERCOUPON = MOBILE_XUXIAN_COM + "/user/usercoupon/" + VAR;
        GETUSERINFO = MOBILE_XUXIAN_COM + "/user/getuserinfo/" + VAR;
        GETSTOREINFO = MOBILE_XUXIAN_COM + "/store/getStoreInfo/" + VAR;
        GETSTORE = MOBILE_XUXIAN_COM + "/store/getStore/" + VAR;
        POINTLIST = MOBILE_XUXIAN_COM + "/point/pointlist/" + VAR;
        MYPOINT = MOBILE_XUXIAN_COM + "/point/mypoint/" + VAR;
        GETCODE = MOBILE_XUXIAN_COM + "/phonecode/getCode/" + VAR;
        INDEX = MOBILE_XUXIAN_COM + "/index/" + VAR;
        GETGOODSLIST = MOBILE_XUXIAN_COM + "/goods/getGoodsList/" + VAR;
        GETHISTORYINFO = MOBILE_XUXIAN_COM + "/order/getHistoryInfo/" + VAR;
        GETORDERDETAIL = MOBILE_XUXIAN_COM + "/order/getOrderDetail/" + VAR;
        EVALUATIONORDER = MOBILE_XUXIAN_COM + "/order/evaluationOrder/" + VAR;
        SUBORDER = MOBILE_XUXIAN_COM + "/order/suborder/" + VAR;
        NEWCARTGOODS = MOBILE_XUXIAN_COM + "/order/newCartGoods/" + VAR;
        NEWGOODSCART = MOBILE_XUXIAN_COM + "/order/newGoodsCart/" + VAR;
        SUREORDER = MOBILE_XUXIAN_COM + "/order/sureOrder/" + VAR;
        APPLYQUITORDER = MOBILE_XUXIAN_COM + "/order/applyQuitOrder/" + VAR;
        SECONDORDER = MOBILE_XUXIAN_COM + "/order/secondorder/" + VAR;
        GETLISTCOUPON = MOBILE_XUXIAN_COM + "/coupon/getlistCoupon/" + VAR;
        SHAREORDER = MOBILE_XUXIAN_COM + "/coupon/shareorder/" + VAR;
        EXCHANGECOLLECTIONGIFT = MOBILE_XUXIAN_COM + "/cards/exchangeCollectionGift/" + VAR;
        GETUSERCARDSINFO = MOBILE_XUXIAN_COM + "/cards/getUserCardsInfo/" + VAR;
        GETSTORECOMINFO = MOBILE_XUXIAN_COM + "/comment/getStoreComInfo/" + VAR;
        UPINSERT = MOBILE_XUXIAN_COM + "/comment/upInsert/" + VAR;
        CALLCENTER = MOBILE_XUXIAN_COM + "/coupon/callcenter/" + VAR;
        GETHONGBAO = MOBILE_XUXIAN_COM + "/envelope/gethongbao/" + VAR;
        GETADDRESS = MOBILE_XUXIAN_COM + "/user/getaddress/" + VAR;
        UPDATEADDRESS = MOBILE_XUXIAN_COM + "/user/updateaddress/" + VAR;
        DELADDRESS = MOBILE_XUXIAN_COM + "/user/deladdress/" + VAR;
        GETAREAS = MOBILE_XUXIAN_COM + "/user/getareas/" + VAR;
        MODADDRESS = MOBILE_XUXIAN_COM + "//user/modaddress/" + VAR;
        CREATE = MOBILE_XUXIAN_COM + "/charge/create/" + VAR;
        ACCOUNTACTIVITY = MOBILE_XUXIAN_COM + "/charge/accountActivity/" + VAR;
        CONSUMPTION = MOBILE_XUXIAN_COM + "/user/consumption/" + VAR;
        GETPTLIST = MOBILE_XUXIAN_COM + "/pt_goods/getptlist/" + VAR;
        CREATEORDER = MOBILE_XUXIAN_COM + "/pt_goods/createorder/" + VAR;
        GETORDERS = MOBILE_XUXIAN_COM + "/pt_goods/getorders/" + VAR;
        ORDERINFO = MOBILE_XUXIAN_COM + "/pt_goods/orderinfo/" + VAR;
        PINTUNA_INFO = MOBILE_XUXIAN_COM + "/pt_goods/pintinfo/" + VAR;
        GETFREIGHT = MOBILE_XUXIAN_COM + "/pt_goods/getfreight/" + VAR;
        VIRTUALSTORE = MOBILE_XUXIAN_COM + "/store/virtualStore/" + VAR;
        GETFREIGHTINFO = MOBILE_XUXIAN_COM + "/order/getFreightInfo/" + VAR;
        MEMBER = MOBILE_XUXIAN_COM + "/user/usergroup/" + VAR;
        EXCLUSIVE_GOOD = MOBILE_XUXIAN_COM + "/user/groupshop/" + VAR;
        GETGOODSINFO = MOBILE_XUXIAN_COM + "/goods/getGoodsInfo/" + VAR;
        REPLACESTORE = MOBILE_XUXIAN_COM + "/store/replaceStore/" + VAR;
        FLASH_SALE_TIME = MOBILE_XUXIAN_COM + "/goods/getSeckillList/" + VAR;
        SECKILL_GOODS = MOBILE_XUXIAN_COM + "/goods/getSeckillGoods/" + VAR;
        SET_REMIND = MOBILE_XUXIAN_COM + "/goods/remindOperation/" + VAR;
        XIAN_QIANG_MIJI = MOBILE_XUXIAN_COM + "/goods/getSeckillUrl" + VAR;
        GETGOODSKILLREMIND = MOBILE_XUXIAN_COM + "/goods/getRemindInfo/" + VAR;
        GET_SHARED_CONTENT = MOBILE_XUXIAN_COM + "/coupon/getShare/" + VAR;
        GET_SHARED_OK = MOBILE_XUXIAN_COM + "/share/shareok/" + VAR;
    }

    public static String getsysheader() {
        return MOBILE_XUXIAN_COM + "/user/getsysheader/" + VAR;
    }

    public static String getinterestlib() {
        return MOBILE_XUXIAN_COM + "/user/getinterestlib/" + VAR;
    }

    public static String upheader(String userid, int htype, String token) {
        return MOBILE_XUXIAN_COM + "/user/upheader/" + userid + "/" + htype + "/" + token + "/" + VAR;
}

    public static String userload(String userid, int utype) {
        return MOBILE_XUXIAN_COM + "/user/userload/" + userid + "/" + utype + "/" + VAR;
    }

    public static String getUserload(String user_id) {
        return MOBILE_XUXIAN_COM + "/user/userload/" + user_id + "/" + VAR;
    }

    public static String getMessage() {
        return MOBILE_XUXIAN_COM + "/msglist/getMessage/" + VAR;
    }

    public static String getHelpList() {
        return MOBILE_XUXIAN_COM + "/help/getHelpList/" + VAR;
    }

    public static String getBanner(String cityid) {
        return MOBILE_XUXIAN_COM + "/banner/index/" + cityid + "/" + VAR;
    }

    public static String getinfos() {
        return MOBILE_XUXIAN_COM + "/category/getinfos/" + VAR;
    }

    public static String goodsShare(int id, int storeid, String city_id, String city, String title) {
        return "http://www.xuxian.com/index.php?controller=site&action=goods&goods_id=" + id + "&storeid=" + storeid + "&city=" + city_id + "&cityname=" + city + "&title=" + title;
    }

    private NewIssRequest(Context context) {
        this.mClient = new NormalHttpClient();
        this.mClient.setConnectionTimeout(BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT);
        this.mClient.setReadTimeout(AbHttpClient.DEFAULT_SOCKET_TIMEOUT);
    }

    public static synchronized NewIssRequest getInstance(Context context) {
        NewIssRequest newIssRequest;
        synchronized (NewIssRequest.class) {
            if (issRequest == null) {
                issRequest = new NewIssRequest(context);
            }
            newIssRequest = issRequest;
        }
        return newIssRequest;
    }

    public void newParams() {
        this.parameterList = this.mClient.newParams();
    }

    public void putParams(String name, String value) {
        this.parameterList.add(new ParameterList.StringParameter(name, value));
    }

    public ParameterList getParameterList() {
        return this.parameterList;
    }

    public String get(String url) throws HttpRequestException {
        return this.mClient.get(url, this.parameterList).getBodyAsString();
    }

    public String post(String url) throws HttpRequestException {
        return this.mClient.post(url, this.parameterList).getBodyAsString();
    }
}
