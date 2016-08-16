package com.xuxian.marketpro.presentation.entity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/16.
 */
public class SureOrderEntity extends  BaseEntity{
    private DataEntity data;
    private StatusEntity status;

    public static class DataEntity extends BaseEntity {
        private ActivityEntity activity;
        private List<AddressEntity> address;
        private List<FreightEntity> companies;
        private List<CouponEntity> coupons;
        private List<String> sendtime;
        private List<ShoppingCartGoodsEntity> shoppingCartGoodsEntities;
        private StoreActivityEntity store_activity;
        private List<TexeInfoEntity> text_info;
        private Double total_price;
        private String user_balance;

        public static class ActivityEntity extends BaseEntity {
            private String other;
            private String qb;
            private String w;
            private String z;

            public void setZ(String z) {
                this.z = z;
            }

            public void setW(String w) {
                this.w = w;
            }

            public void setOther(String other) {
                this.other = other;
            }

            public void setQb(String qb) {
                this.qb = qb;
            }

            public String getZ() {
                return this.z;
            }

            public String getW() {
                return this.w;
            }

            public String getOther() {
                return this.other;
            }

            public String getQb() {
                return this.qb;
            }
        }

        public static class StoreActivityEntity extends BaseEntity {
            private Double amount;
            private String intro;

            public String getIntro() {
                return this.intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public Double getAmount() {
                return this.amount;
            }

            public void setAmount(Double amount) {
                this.amount = amount;
            }
        }

        public static class TexeInfoEntity extends BaseEntity {
            private String text_info;
            private String text_type;

            public String getText_type() {
                return this.text_type;
            }

            public void setText_type(String text_type) {
                this.text_type = text_type;
            }

            public String getText_info() {
                return this.text_info;
            }

            public void setText_info(String text_info) {
                this.text_info = text_info;
            }
        }

        public List<ShoppingCartGoodsEntity> getShoppingCartGoodsEntities() {
            return this.shoppingCartGoodsEntities;
        }

        public void setShoppingCartGoodsEntities(List<ShoppingCartGoodsEntity> shoppingCartGoodsEntities) {
            this.shoppingCartGoodsEntities = shoppingCartGoodsEntities;
        }

        public void setActivity(ActivityEntity activity) {
            this.activity = activity;
        }

        public void setStore_activity(StoreActivityEntity store_activity) {
            this.store_activity = store_activity;
        }

        public void setUser_balance(String user_balance) {
            this.user_balance = user_balance;
        }

        public void setCoupons(List<CouponEntity> coupons) {
            this.coupons = coupons;
        }

        public void setCompanies(List<FreightEntity> companies) {
            this.companies = companies;
        }

        public void setAddress(List<AddressEntity> address) {
            this.address = address;
        }

        public void setSendtime(List<String> sendtime) {
            this.sendtime = sendtime;
        }

        public void setText_info(List<TexeInfoEntity> text_info) {
            this.text_info = text_info;
        }

        public ActivityEntity getActivity() {
            return this.activity;
        }

        public StoreActivityEntity getStore_activity() {
            return this.store_activity;
        }

        public String getUser_balance() {
            return this.user_balance;
        }

        public List<CouponEntity> getCoupons() {
            return this.coupons;
        }

        public List<FreightEntity> getCompanies() {
            return this.companies;
        }

        public List<AddressEntity> getAddress() {
            return this.address;
        }

        public List<String> getSendtime() {
            return this.sendtime;
        }

        public List<TexeInfoEntity> getText_info() {
            return this.text_info;
        }

        public Double getTotal_price() {
            return this.total_price;
        }

        public void setTotal_price(Double total_price) {
            this.total_price = total_price;
        }
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public StatusEntity getStatus() {
        return this.status;
    }

    public DataEntity getData() {
        return this.data;
    }
}
