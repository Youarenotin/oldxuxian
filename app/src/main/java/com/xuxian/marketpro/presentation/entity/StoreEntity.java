package com.xuxian.marketpro.presentation.entity;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Id;
import com.ab.db.orm.annotation.Table;

/**
 * Created by youarenotin on 16/7/27.
 */
@Table(name = "Store")
public class StoreEntity extends BaseEntity {
    @Column(name = "area")
    private String area;//
    @Column(name = "area_id")
    private String area_id;
    @Column(name = "bdate")
    private String bdate;
    @Column(name = "bstarttime")
    private String bstarttime;
    @Column(name = "city_area")
    private String city_area;
    @Column(name = "city_id")
    private String city_id;
    @Column(name = "city_name")
    private String city_name;
    @Column(name = "distance")
    private String distance;//
    @Column(name = "fristtime")
    private String fristtime;
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lng")
    private Double lng;
    @Column(name = "starttime")
    private String starttime;

    @Column(name = "message_alert")
    private String message_alert;
    @Column(name = "store_status")
    private int store_status;
    @Column(name = "telphone")
    private String telphone;
    @Column(name = "title")
    private String title;//
    @Column(name = "type")
    private int type;


    @Column(name = "add_time")
    private String add_time;
    @Column(name = "send_range")
    private String send_range;
    @Column(name = "divide")
    private String divide;
    @Column(name = "tags")
    private String tags;
    @Column(name = "discount")
    private String discount;
    @Column(name = "store_attr")
    private String store_attr;

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getSend_range() {
        return send_range;
    }

    public void setSend_range(String send_range) {
        this.send_range = send_range;
    }

    public String getDivide() {
        return divide;
    }

    public void setDivide(String divide) {
        this.divide = divide;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStore_attr() {
        return store_attr;
    }

    public void setStore_attr(String store_attr) {
        this.store_attr = store_attr;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getBstarttime() {
        return bstarttime;
    }

    public void setBstarttime(String bstarttime) {
        this.bstarttime = bstarttime;
    }

    public String getCity_area() {
        return city_area;
    }

    public void setCity_area(String city_area) {
        this.city_area = city_area;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFristtime() {
        return fristtime;
    }

    public void setFristtime(String fristtime) {
        this.fristtime = fristtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getMessage_alert() {
        return message_alert;
    }

    public void setMessage_alert(String message_alert) {
        this.message_alert = message_alert;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public int getStore_status() {
        return store_status;
    }

    public void setStore_status(int store_status) {
        this.store_status = store_status;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
