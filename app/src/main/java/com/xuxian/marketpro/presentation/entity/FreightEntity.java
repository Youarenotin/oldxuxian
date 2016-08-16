package com.xuxian.marketpro.presentation.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youarenotin on 16/8/16.
 */
public class FreightEntity extends BaseEntity{
    private Double free_pay;
    private String freight_img;
    private String freight_name;
    private Integer id;
    private String intro;
    private Double pay_freight;
    private List<SendTimesEntity> send_times;

    public static class SendTimesEntity implements Serializable {
        private String freight_pay;
        private String intro;
        private String send_time;

        public void setSend_time(String send_time) {
            this.send_time = send_time;
        }

        public void setFreight_pay(String freight_pay) {
            this.freight_pay = freight_pay;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getSend_time() {
            return this.send_time;
        }

        public String getFreight_pay() {
            return this.freight_pay;
        }

        public String getIntro() {
            return this.intro;
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFreight_img(String freight_img) {
        this.freight_img = freight_img;
    }

    public void setFreight_name(String freight_name) {
        this.freight_name = freight_name;
    }

    public void setPay_freight(Double pay_freight) {
        this.pay_freight = pay_freight;
    }

    public void setFree_pay(Double free_pay) {
        this.free_pay = free_pay;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setSend_times(List<SendTimesEntity> send_times) {
        this.send_times = send_times;
    }

    public Integer getId() {
        return this.id;
    }

    public String getFreight_img() {
        return this.freight_img;
    }

    public String getFreight_name() {
        return this.freight_name;
    }

    public Double getPay_freight() {
        return this.pay_freight;
    }

    public Double getFree_pay() {
        return this.free_pay;
    }

    public String getIntro() {
        return this.intro;
    }

    public List<SendTimesEntity> getSend_times() {
        return this.send_times;
    }
}
