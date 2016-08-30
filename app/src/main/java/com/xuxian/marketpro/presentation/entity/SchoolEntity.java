package com.xuxian.marketpro.presentation.entity;

/**
 * Created by youarenotin on 16/8/30.
 */
public class SchoolEntity extends BaseEntity{
    private int id;
    private String province;
    private String school;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
