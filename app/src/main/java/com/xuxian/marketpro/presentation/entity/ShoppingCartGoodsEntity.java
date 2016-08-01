package com.xuxian.marketpro.presentation.entity;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Table;

import java.util.List;

/**
 * Created by youarenotin on 16/7/27.
 */
@Table(name = "ShoppingCartGoods")
public class ShoppingCartGoodsEntity extends BaseEntity {
    @Column(name = "amount")
    private String amount;
    private List<?> corver;
    @Column(name = "count")
    private int count;
    @Column(name = "details")
    private String details;
    @Column(name = "details2")
    private String details2;
    @Column(name = "down_time")
    private String down_time;
    @Column(name = "endtime")
    private String endtime;
    @Column(name = "goods_type")
    private String goods_type;
    private List<?> group_price;
    @Column(name = "icon")
    private String icon;
    @Column(name = "id")
    private Integer id;
    @Column(name = "is_del")
    private String is_del;
    @Column(name = "market_price")
    private String market_price;
    @Column(name = "newimg")
    private String newimg;
    @Column(name = "phonetips")
    private String phonetips;
    @Column(name = "price")
    private String price;
    @Column(name = "real_name")
    private String real_name;
    @Column(name = "selltype")
    private String selltype;
    @Column(name = "show")
    private String show;
    @Column(name = "sold_num")
    private int sold_num;
    @Column(name = "starttime")
    private String starttime;
    @Column(name = "store_num")
    private int store_num;
    @Column(name = "store_nums")
    private int store_nums;
    @Column(name = "tipsimg")
    private String tipsimg;
    @Column(name = "title")
    private String title;
    @Column(name = "unit")
    private String unit;
    @Column(name = "up_time")
    private String up_time;
    @Column(name = "userid")
    private String userid;

    public ShoppingCartGoodsEntity(String goods_type, String up_time, String down_time, String is_del, String unit, String newimg, Integer id, String title, String price, String icon, int store_nums, int sold_num, String market_price, String real_name, int store_num, String selltype, String tipsimg, String phonetips, String starttime, String endtime, String show, int count, String amount, String details, String details2, String userid) {
        this.goods_type = goods_type;
        this.up_time = up_time;
        this.down_time = down_time;
        this.is_del = is_del;
        this.unit = unit;
        this.newimg = newimg;
        this.id = id;
        this.title = title;
        this.price = price;
        this.icon = icon;
        this.store_nums = store_nums;
        this.sold_num = sold_num;
        this.market_price = market_price;
        this.real_name = real_name;
        this.store_num = store_num;
        this.selltype = selltype;
        this.tipsimg = tipsimg;
        this.phonetips = phonetips;
        this.starttime = starttime;
        this.endtime = endtime;
        this.show = show;
        this.count = count;
        this.amount = amount;
        this.details = details;
        this.details2 = details2;
        this.userid = userid;

    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<?> getCorver() {
        return corver;
    }

    public void setCorver(List<?> corver) {
        this.corver = corver;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails2() {
        return details2;
    }

    public void setDetails2(String details2) {
        this.details2 = details2;
    }

    public String getDown_time() {
        return down_time;
    }

    public void setDown_time(String down_time) {
        this.down_time = down_time;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public List<?> getGroup_price() {
        return group_price;
    }

    public void setGroup_price(List<?> group_price) {
        this.group_price = group_price;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getNewimg() {
        return newimg;
    }

    public void setNewimg(String newimg) {
        this.newimg = newimg;
    }

    public String getPhonetips() {
        return phonetips;
    }

    public void setPhonetips(String phonetips) {
        this.phonetips = phonetips;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getSelltype() {
        return selltype;
    }

    public void setSelltype(String selltype) {
        this.selltype = selltype;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public int getSold_num() {
        return sold_num;
    }

    public void setSold_num(int sold_num) {
        this.sold_num = sold_num;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public int getStore_num() {
        return store_num;
    }

    public void setStore_num(int store_num) {
        this.store_num = store_num;
    }

    public int getStore_nums() {
        return store_nums;
    }

    public void setStore_nums(int store_nums) {
        this.store_nums = store_nums;
    }

    public String getTipsimg() {
        return tipsimg;
    }

    public void setTipsimg(String tipsimg) {
        this.tipsimg = tipsimg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
