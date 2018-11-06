package com.shushan.homework101.HttpHelper.service.entity.register;

import java.util.ArrayList;

public class LoginData {
    private int userid;
    private String username;
    private String trait;
    private ArrayList<LoginTeachers> teachers;
    private String grade;
    private String money;
    private String province;
    private String city;
    private String country;
    private String third_id;
    private String third_token;
    private String token;
    private String logid;

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public ArrayList<LoginTeachers> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<LoginTeachers> teachers) {
        this.teachers = teachers;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getThird_id() {
        return third_id;
    }

    public void setThird_id(String third_id) {
        this.third_id = third_id;
    }

    public String getThird_token() {
        return third_token;
    }

    public void setThird_token(String third_token) {
        this.third_token = third_token;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", trait='" + trait + '\'' +
                ", teachers=" + teachers +
                ", grade='" + grade + '\'' +
                ", money='" + money + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", third_id='" + third_id + '\'' +
                ", third_token='" + third_token + '\'' +
                ", token='" + token + '\'' +
                ", logid='" + logid + '\'' +
                '}';
    }
}
