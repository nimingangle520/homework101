package com.shushan.homework101.HttpHelper.service.entity.homework;

import java.util.Arrays;

public class UploadPic {
    int error;
    String msg;
    String[] data;

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
        return "UploadPic{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
