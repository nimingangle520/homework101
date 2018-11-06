package com.shushan.homework101.HttpHelper.service.entity.mine;

import java.util.Arrays;

public class GetUserInfoData {
    private int userid;
    private String username;
    private String trait;
    private int sex;
    private int[] teachers;
    private int grade_id;
    private String grade;
    private double money;
    private String province;
    private String city;
    private String country;
    private String token;
    private String third_id;
    private String third_token;

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int[] getTeachers() {
        return teachers;
    }

    public void setTeachers(int[] teachers) {
        this.teachers = teachers;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    public void setMoney(float money) {
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

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "GetUserInfoData{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", trait='" + trait + '\'' +
                ", sex=" + sex +
                ", teachers=" + Arrays.toString(teachers) +
                ", grade_id=" + grade_id +
                ", grade='" + grade + '\'' +
                ", money=" + money +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", token='" + token + '\'' +
                ", third_id='" + third_id + '\'' +
                ", third_token='" + third_token + '\'' +
                '}';
    }
}
