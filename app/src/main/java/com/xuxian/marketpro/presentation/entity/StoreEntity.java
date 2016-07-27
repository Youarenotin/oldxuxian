package com.xuxian.marketpro.presentation.entity;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Id;
import com.ab.db.orm.annotation.Table;

/**
 * Created by youarenotin on 16/7/27.
 */
@Table("Store")
public class StoreEntity extends BaseEntity {
    @Column( "area")
    private String area;
    @Column( "area_id")
    private String area_id;
    @Column( "bdate")
    private String bdate;
    @Column( "bstarttime")
    private String bstarttime;
    @Column( "city_area")
    private String city_area;
    @Column( "city_id")
    private String city_id;
    @Column( "city_name")
    private String city_name;
    @Column( "distance")
    private String distance;
    @Column( "fristtime")
    private String fristtime;
    @Id
    @Column( "id")
    private int id;
    @Column( "lat")
    private Double lat;
    @Column( "lng")
    private Double lng;
    @Column( "message_alert")
    private String message_alert;
    @Column( "starttime")
    private String starttime;

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

    @Column( "store_status")
    private int store_status;
    @Column( "telphone")
    private String telphone;
    @Column( "title")
    private String title;
    @Column( "type")
    private int type;
}
