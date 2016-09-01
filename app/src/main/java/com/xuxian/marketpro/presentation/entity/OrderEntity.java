package com.xuxian.marketpro.presentation.entity;

/**
 * Created by youarenotin on 16/9/1.
 */
public class OrderEntity extends BaseEntity{
    private String accept_time;
    private String address;
    private String code;
    private String completion_time;
    private String create_time;
    private String distribution;
    private String distribution_company;
    private String distribution_member;
    private String distribution_phone;
    private int distribution_status;
    private String id;
    private String invoice;
    private String mobile;
    private String order_no;
    private String order_status;
    private int orderstatus;
    private int pay_status;
    private int pay_type;
    private String postcode;
    private String postscript;
    private String real_amount;
    private int send_status;
    private String send_time;
    private String store;
    private String telphone;

    public int getSend_status() {
        return this.send_status;
    }

    public void setSend_status(int send_status) {
        this.send_status = send_status;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_no() {
        return this.order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getStore() {
        return this.store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public int getPay_type() {
        return this.pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public int getOrderstatus() {
        return this.orderstatus;
    }

    public void setOrderstatus(int orderstatus) {
        this.orderstatus = orderstatus;
    }

    public int getPay_status() {
        return this.pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    public String getReal_amount() {
        return this.real_amount;
    }

    public void setReal_amount(String real_amount) {
        this.real_amount = real_amount;
    }

    public String getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCompletion_time() {
        return this.completion_time;
    }

    public void setCompletion_time(String completion_time) {
        this.completion_time = completion_time;
    }

    public String getAccept_time() {
        return this.accept_time;
    }

    public void setAccept_time(String accept_time) {
        this.accept_time = accept_time;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInvoice() {
        return this.invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getPostscript() {
        return this.postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public String getTelphone() {
        return this.telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getDistribution_status() {
        return this.distribution_status;
    }

    public void setDistribution_status(int distribution_status) {
        this.distribution_status = distribution_status;
    }

    public String getSend_time() {
        return this.send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getDistribution() {
        return this.distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrder_status() {
        return this.order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getDistribution_member() {
        return this.distribution_member;
    }

    public void setDistribution_member(String distribution_member) {
        this.distribution_member = distribution_member;
    }

    public String getDistribution_phone() {
        return this.distribution_phone;
    }

    public void setDistribution_phone(String distribution_phone) {
        this.distribution_phone = distribution_phone;
    }

    public String getDistribution_company() {
        return this.distribution_company;
    }

    public void setDistribution_company(String distribution_company) {
        this.distribution_company = distribution_company;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
