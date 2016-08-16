package com.xuxian.marketpro.presentation.entity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/16.
 */
public class NewCartGoodsEntity {
    private ShoppingCartDataEntity data;
    private StatusEntity status;

    public static class ShoppingCartDataEntity {
        private List<GoodssectionsEntity> goodssections;
        private List<?> push;
        private List<UndoactionsEntity> undoactions;

        public static class GoodssectionsEntity {
            private String accessabel;
            private List<ShoppingCartGoodsEntity> goodslist;
            private double prices;
            private String sectionname;

            public void setSectionname(String sectionname) {
                this.sectionname = sectionname;
            }

            public void setAccessabel(String accessabel) {
                this.accessabel = accessabel;
            }

            public void setPrices(double prices) {
                this.prices = prices;
            }

            public void setGoodslist(List<ShoppingCartGoodsEntity> goodslist) {
                this.goodslist = goodslist;
            }

            public String getSectionname() {
                return this.sectionname;
            }

            public String getAccessabel() {
                return this.accessabel;
            }

            public double getPrices() {
                return this.prices;
            }

            public List<ShoppingCartGoodsEntity> getGoodslist() {
                return this.goodslist;
            }
        }

        public static class UndoactionsEntity {
            private String actname;
            private Integer acttype;
            private Integer goodid;
            private String info;

            public void setActname(String actname) {
                this.actname = actname;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public void setActtype(Integer acttype) {
                this.acttype = acttype;
            }

            public void setGoodid(Integer goodid) {
                this.goodid = goodid;
            }

            public String getActname() {
                return this.actname;
            }

            public String getInfo() {
                return this.info;
            }

            public Integer getActtype() {
                return this.acttype;
            }

            public Integer getGoodid() {
                return this.goodid;
            }
        }

        public void setGoodssections(List<GoodssectionsEntity> goodssections) {
            this.goodssections = goodssections;
        }

        public void setUndoactions(List<UndoactionsEntity> undoactions) {
            this.undoactions = undoactions;
        }

        public void setPush(List<?> push) {
            this.push = push;
        }

        public List<GoodssectionsEntity> getGoodssections() {
            return this.goodssections;
        }

        public List<UndoactionsEntity> getUndoactions() {
            return this.undoactions;
        }

        public List<?> getPush() {
            return this.push;
        }
    }

    public static class StatusEntity {
        private int code;
        private String message;

        public void setCode(int code) {
            this.code = code;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public void setData(ShoppingCartDataEntity data) {
        this.data = data;
    }

    public StatusEntity getStatus() {
        return this.status;
    }

    public ShoppingCartDataEntity getData() {
        return this.data;
    }
}
