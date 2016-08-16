package com.xuxian.marketpro.presentation.entity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/16.
 */
public class CouponEntity extends BaseEntity{
    private String cname;
    private String code;
    private int ctype;
    private String endtime;
    private List<GoodsEntity> goods;
    private int goods_id;
    private Integer id;
    private String is_all;
    private String order_id;
    private Double pay_value;
    private String restrictive;
    private String showname;
    private String starttime;
    private String typename;
    private int use_one;
    private String user_id;

    public static class GoodsEntity extends BaseEntity {
        private String icon;
        private Integer id;
        private String name;

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return this.icon;
        }

        public Integer getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }
    }

    public int getGoods_id() {
        return this.goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCtype(int ctype) {
        this.ctype = ctype;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIs_all(String is_all) {
        this.is_all = is_all;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setPay_value(Double pay_value) {
        this.pay_value = pay_value;
    }

    public void setRestrictive(String restrictive) {
        this.restrictive = restrictive;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public void setUse_one(int use_one) {
        this.use_one = use_one;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setGoods(List<GoodsEntity> goods) {
        this.goods = goods;
    }

    public String getCname() {
        return this.cname;
    }

    public String getCode() {
        return this.code;
    }

    public int getCtype() {
        return this.ctype;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public Integer getId() {
        return this.id;
    }

    public String getIs_all() {
        return this.is_all;
    }

    public String getOrder_id() {
        return this.order_id;
    }

    public Double getPay_value() {
        return this.pay_value;
    }

    public String getRestrictive() {
        return this.restrictive;
    }

    public String getShowname() {
        return this.showname;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public String getTypename() {
        return this.typename;
    }

    public int getUse_one() {
        return this.use_one;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public List<GoodsEntity> getGoods() {
        return this.goods;
    }
}
