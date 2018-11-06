package com.shushan.homework101.HttpHelper.service.entity.orders;

import java.util.ArrayList;

public class Orders {
    int error;
    String msg;
    ArrayList<OrdersData> data;

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

    public ArrayList<OrdersData> getData() {
        return data;
    }

    public void setData(ArrayList<OrdersData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
