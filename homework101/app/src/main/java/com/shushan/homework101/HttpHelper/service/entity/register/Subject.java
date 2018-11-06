package com.shushan.homework101.HttpHelper.service.entity.register;

import java.util.ArrayList;

public class Subject {
    private String error;
    private String msg;
    private ArrayList<SubjectData> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<SubjectData> getData() {
        return data;
    }

    public void setData(ArrayList<SubjectData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "error='" + error + '\'' +
                ", msg='" + msg + '\'' +
                ", subjectData=" + data +
                '}';
    }
}
