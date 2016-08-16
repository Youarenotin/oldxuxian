package com.xuxian.marketpro.presentation.entity;

/**
 * Created by youarenotin on 16/8/16.
 */
public class ForumsEntity extends BaseEntity{
    private static final long serialVersionUID = 1;
    private String app_logo;
    private String descrip;
    private String fid;
    private String fupname;
    private String icon;
    private String name;

    public String getFid() {
        return this.fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrip() {
        return this.descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getApp_logo() {
        return this.app_logo;
    }

    public void setApp_logo(String app_logo) {
        this.app_logo = app_logo;
    }

    public String getFupname() {
        return this.fupname;
    }

    public void setFupname(String fupname) {
        this.fupname = fupname;
    }
}
