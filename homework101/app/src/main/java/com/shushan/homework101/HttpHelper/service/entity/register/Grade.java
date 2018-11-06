package com.shushan.homework101.HttpHelper.service.entity.register;

import java.util.ArrayList;

public class Grade {
    private String error;
    private String msg;
    private ArrayList<GradeData> data;

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

    public ArrayList<GradeData> getData() {
        return data;
    }

    public void setData(ArrayList<GradeData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "error='" + error + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
