package com.xuxian.marketpro.presentation.entity;

import java.util.List;

/**
 * Created by youarenotin on 16/7/27.
 */
public class GetStoreEntity {
    private DataEntity data;
    private StatusEntity status;

    public static class DataEntity{
        private List<StoreInfoEntity> store_info;
        public static  class  StoreInfoEntity{
            private String area_id;
            private String area_name;
            private List<StoreEntity> store_list;

            public List<StoreEntity> getStore_list() {
                return store_list;
            }

            public void setStore_list(List<StoreEntity> store_list) {
                this.store_list = store_list;
            }

            public String getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }

            public String getArea_id() {
                return area_id;
            }

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }
        }
    }
    public static class StatusEntity{
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        private int code;
        private String message;
    }
}
