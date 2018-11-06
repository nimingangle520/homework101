package com.shushan.homework101.HttpHelper.service.entity.homepage;

public class Homepage {
    int error;
    String msg;
    HomepageData data;

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

    public HomepageData getData() {
        return data;
    }

    public void setData(HomepageData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Homepage{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
