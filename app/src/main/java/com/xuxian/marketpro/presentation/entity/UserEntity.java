package com.xuxian.marketpro.presentation.entity;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Id;
import com.ab.db.orm.annotation.Table;

import java.util.List;

/**
 * 作者：lubo on 8/2 0002 13:45
 * 邮箱：lubo_wen@126.com
 */
@Table(name = "User")
public class UserEntity extends BaseEntity{
    public UserEntity() {
    }

    public UserEntity(String userid, String phone, String payment, String token, String username, int developersid, String email, int point, int sex, String birthday, String occupation, String school, String interest, String head_ico) {
        this.userid = userid;
        this.phone = phone;
        this.payment = payment;
        this.token = token;
        this.username = username;
        this.developersid = developersid;
        this.email = email;
        this.point = point;
        this.sex = sex;
        this.birthday = birthday;
        this.occupation = occupation;
        this.school = school;
        this.interest = interest;
        this.head_ico = head_ico;
    }


    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getDevelopersid() {
        return developersid;
    }

    public void setDevelopersid(int developersid) {
        this.developersid = developersid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getHead_ico() {
        return head_ico;
    }

    public void setHead_ico(String head_ico) {
        this.head_ico = head_ico;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Integer getPush_count() {
        return push_count;
    }

    public void setPush_count(Integer push_count) {
        this.push_count = push_count;
    }

    public String getPush_link() {
        return push_link;
    }

    public void setPush_link(String push_link) {
        this.push_link = push_link;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @Column(name = "balance")
    private String balance;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "developersid")
    private int developersid;
    @Column(name = "email")
    private String email;
    @Column(name = "exp")
    private int exp;
    @Column(name = "group_id")
    private int group_id;
    @Column(name = "head_ico")
    private String head_ico;
    @Column(name = "interest")
    private String interest;
    @Column(name = "occupation")
    private String occupation;
    @Column(name = "payment")
    private String payment;
    @Column(name = "phone")
    private String phone;
    @Column(name = "point")
    private int point;
    @Column(name = "push_count")
    private Integer push_count;
    @Column(name = "push_link")
    private String push_link;
    @Column(name = "school")
    private String school;
    @Column(name = "sex")
    private int sex;
    @Column(name = "token")
    private String token;
    @Column(name = "userid")
    @Id
    private String userid;
    @Column(name = "username")
    private String username;
    private List<UserEntity> users;
}
