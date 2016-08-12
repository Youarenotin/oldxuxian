package com.xuxian.marketpro.presentation.entity;

/**
 * Created by youarenotin on 16/8/10.
 */
public class PageEntity {
    private int pagecount;
    private  int pagenum;
    private int total;

    public PageEntity() {
    }

    public PageEntity(int pagecount, int pagenum, int total) {
        this.pagecount = pagecount;
        this.pagenum = pagenum;
        this.total = total;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
