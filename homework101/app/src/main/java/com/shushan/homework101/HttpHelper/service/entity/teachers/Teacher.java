package com.shushan.homework101.HttpHelper.service.entity.teachers;

public class Teacher {
    int error;
    String msg;
    TeachersData data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TeachersData getData() {
        return data;
    }

    public void setData(TeachersData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
