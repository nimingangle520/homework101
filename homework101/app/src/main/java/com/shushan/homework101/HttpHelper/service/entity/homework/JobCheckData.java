package com.shushan.homework101.HttpHelper.service.entity.homework;

import java.util.ArrayList;

public class JobCheckData {
    UserInfo userinfo;
    ArrayList<Subject> subject;

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public ArrayList<Subject> getSubject() {
        return subject;
    }

    public void setSubject(ArrayList<Subject> subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "JobCheckData{" +
                "userinfo=" + userinfo +
                ", subject=" + subject +
                '}';
    }
}
