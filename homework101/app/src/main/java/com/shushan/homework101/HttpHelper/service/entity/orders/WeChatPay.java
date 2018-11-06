package com.shushan.homework101.HttpHelper.service.entity.orders;

import java.io.Serializable;

public class WeChatPay implements Serializable{
    int error;
    String msg;
    WeChatPayData data;

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

    public WeChatPayData getData() {
        return data;
    }

    public void setData(WeChatPayData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WeChatPay{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
