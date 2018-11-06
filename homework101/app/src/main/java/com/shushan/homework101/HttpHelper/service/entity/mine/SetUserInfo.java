package com.shushan.homework101.HttpHelper.service.entity.mine;

import java.util.Arrays;

public class SetUserInfo {
    private int error;
    private String msg;
    private String [] data;

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

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SetUserInfo{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
