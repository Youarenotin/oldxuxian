package com.xuxian.marketpro.presentation.entity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/10.
 */
public class IndexEntity {
    private List<GoodsEntity> data;
    private PageEntity page;
    private StatusEntity status;

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }


    public List<GoodsEntity> getData() {
        return data;
    }

    public void setData(List<GoodsEntity> data) {
        this.data = data;
    }

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }



    public static  class GoodsEntity {
        private List<GoodsListEntity> goods;
        private String title;

        public List<GoodsListEntity> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsListEntity> goods) {
            this.goods = goods;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public    class StatusEntity {
        private int  code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
