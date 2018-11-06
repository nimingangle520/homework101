package com.shushan.homework101.HttpHelper.service.entity.orders;

public class OrdersDetails {
    private int error;
    private String msg;
    private OrdersDetailsData data;

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

    public OrdersDetailsData getData() {
        return data;
    }

    public void setData(OrdersDetailsData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrdersDetails{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
