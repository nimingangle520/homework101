package com.shushan.homework101.HttpHelper.service.entity.register;

public class Data {
    private String third_id;
    private int userid;
    private String token;
    private String third_token;

    public String getThird_token() {
        return third_token;
    }

    public void setThird_token(String third_token) {
        this.third_token = third_token;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    @Override
    public String toString() {
        return "Data{" +
                "third_id='" + third_id + '\'' +
                ", userid=" + userid +
                ", token='" + token + '\'' +
                ", third_token='" + third_token + '\'' +
                '}';
    }
}
