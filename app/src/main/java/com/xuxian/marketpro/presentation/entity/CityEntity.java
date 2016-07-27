package com.xuxian.marketpro.presentation.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youarenotin on 16/7/24.
 */
public class CityEntity {
    private DataEntity data;
    private StatusEntity status;

    public static class DataEntity {
        private List<CityInfoEntity> city_info;

        public List<CityInfoEntity> getCity_info() {
            return city_info;
        }

        public void setCity_info(List<CityInfoEntity> city_info) {
            this.city_info = city_info;
        }
        public static class     CityInfoEntity implements Comparable<CityInfoEntity> ,Serializable{
            private static final long serialVersionUID = 1;
            private String PinYinName;
            private String city_id;
            private String city_name;
            private Integer city_status;
            private String firstLetter;

            public String getPinYinName() {
                return PinYinName;
            }

            public void setPinYinName(String pinYinName) {
                PinYinName = pinYinName;
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

            public Integer getCity_status() {
                return city_status;
            }

            public void setCity_status(Integer city_status) {
                this.city_status = city_status;
            }

            public String getFirstLetter() {
                return firstLetter;
            }

            public void setFirstLetter(String firstLetter) {
                this.firstLetter = firstLetter;
            }


            @Override
            public int compareTo(CityInfoEntity cityInfoEntity) {
                if (getFirstLetter().equals("@") || cityInfoEntity.getFirstLetter().equals("#")){
                    return -1;
                }
                if (getFirstLetter().equals("#") || cityInfoEntity.getFirstLetter().equals("@")){
                    return 1;
                }
                return getFirstLetter().compareTo(cityInfoEntity.getFirstLetter());
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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private String code;
        private String message;

    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
