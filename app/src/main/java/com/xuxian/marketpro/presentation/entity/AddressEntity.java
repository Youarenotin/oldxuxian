package com.xuxian.marketpro.presentation.entity;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Id;
import com.ab.db.orm.annotation.Table;

/**
 * Created by youarenotin on 16/8/16.
 */
@Table(name = "Address")
public class AddressEntity extends BaseEntity{
    private static final long serialVersionUID = 1;
    @Column(name = "accept_name")
    private String accept_name;
    @Column(name = "address")
    private String address;
    @Column(name = "area")
    private String area;
    @Column(name = "area_id")
    private String area_id;
    @Column(name = "city")
    private String city;
    @Column(name = "city_id")
    private String city_id;
    @Column(name = "d_address")
    private String d_address;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "is_default")
    private String is_default;
    @Column(name = "l_address")
    private String l_address;
    @Column(name = "lat")
    private double lat;
    @Column(name = "lng")
    private double lng;
    @Column(name = "mobile")
    private String mobile;

    public AddressEntity(Integer id, String address, String l_address, String d_address, String city, String area, String mobile, double lat, double lng, String is_default, String accept_name, String city_id, String area_id) {
        this.id = id;
        this.address = address;
        this.l_address = l_address;
        this.d_address = d_address;
        this.city = city;
        this.area = area;
        this.mobile = mobile;
        this.lat = lat;
        this.lng = lng;
        this.is_default = is_default;
        this.accept_name = accept_name;
        this.city_id = city_id;
        this.area_id = area_id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getL_address() {
        return this.l_address;
    }

    public void setL_address(String l_address) {
        this.l_address = l_address;
    }

    public String getD_address() {
        return this.d_address;
    }

    public void setD_address(String d_address) {
        this.d_address = d_address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getIs_default() {
        return this.is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getAccept_name() {
        return this.accept_name;
    }

    public void setAccept_name(String accept_name) {
        this.accept_name = accept_name;
    }

    public String getCity_id() {
        return this.city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getArea_id() {
        return this.area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }
}
