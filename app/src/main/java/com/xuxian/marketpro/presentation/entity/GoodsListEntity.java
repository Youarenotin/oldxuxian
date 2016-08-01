package com.xuxian.marketpro.presentation.entity;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Id;
import com.ab.db.orm.annotation.Table;

/**
 * 作者：lubo on 8/1 0001 16:08
 * 邮箱：lubo_wen@126.com
 */
@Table(name = "GoodsList")
public class GoodsListEntity extends BaseEntity {
    @Column(name = "details2")
    private String details2;
    @Column(name = "endtime")
    private String endtime;
    @Column(name = "goods_type")
    private String goods_type;
    private String grouptitle;
    @Column(name = "icon")
    private String icon;
    @Column(name = "id")
    @Id
    private Integer id;
    @Column(name = "market_price")
    private String market_price;
    @Column(name = "newimg")
    private String newimg;
    @Column(name = "phonetips")
    private String phonetips;
    @Column(name = "price")
    private String price;
    private int section;
    @Column(name = "selltype")
    private String selltype;
    @Column(name = "show")
    private String show;
    @Column(name = "sold_num")
    private int sold_num;
    @Column(name = "starttime")
    private String starttime;
    @Column(name = "store_nums")
    private int store_nums;
    @Column(name = "tipsimg")
    private String tipsimg;
    @Column(name = "title")
    private String title;
    @Column(name = "unit")
    private String unit;

    public String getDetails2() {
        return details2;
    }

    public void setDetails2(String details2) {
        this.details2 = details2;
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

    public String getGrouptitle() {
        return grouptitle;
    }

    public void setGrouptitle(String grouptitle) {
        this.grouptitle = grouptitle;
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

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
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
}
