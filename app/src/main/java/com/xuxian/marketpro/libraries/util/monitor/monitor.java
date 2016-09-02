package com.xuxian.marketpro.libraries.util.monitor;

/**
 * Created by youarenotin on 16/7/24.
 */
public class monitor {
    public enum AddressCartEnum {
        QUERY_ALL_ADDRESSES,
        MODIFY_THE_ADDRESS,
        DELETE_THE_ADDRESS,
        ADD_THE_ADDRESS,
        SELECT_THE_DEFAULT_ADDRESS,
        THE_DEFAULT_ADDRESS_SET_SUCCESSFULLY,
        CHOOSE_TO_RETRIEVE_THE_ADDRESS,
        SWITCH_ADDRESS
    }

    public enum CityEnum {
        SWITCH_CITY,
        CLOSE_PAGE
    }

    public enum GaoDeLocationEnum {
        LOCATION_LATITUDE_AND_LONGITUDE,
        LOCATING_CITY,
        LOCATION_ADDRESS
    }

    public enum GoodsEnum {
        REFRESH_GOODS,
        REFRESH_LISTVIEW,
        SWITCH_MAIN_PAGE,
        SWITCH_SHOPPING_CART,
        REFRESH_ADDRESS
    }

    public enum OrderEnum {
        REFRESH_THE_ORDER,
        PAYMENT_SUCCESS,
        SHARE_SUCCESS,
        EVALUATION_OF_SUCCESS,
        REFRESH
    }

    public enum PayCallbackEnum {
        ZHIFUBAO,
        WEIXIN
    }

    public enum PhotoEnum {
        DELETE_PICTURES_UPDATED,
        CHOOSE_ALBUM_UPDATES,
        CONFIRM
    }

    public enum PlaceOrderEnum {
        ORDER_GOODS,
        PINTUAN_ORDER,
        INTEGRAL_MALL_ORDER,
        BALANCE_ORDER
    }

    public enum ShoppingCartEnum {
        EMPTY_THE_SHOPPING_CART,
        UPDATE_THE_SHOPPING_CART,
        MODIFY_SHOPPING_CART,
        DELETE_SHOPPING_CART_GOODS
    }

    public enum StoreEnum {
        CLOSE_ENTER,
        REPLACESTORE
    }

}
