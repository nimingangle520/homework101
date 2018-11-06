package com.shushan.homework101.HttpHelper.service.entity.mine;

public class GetUserInfo {
    private int error;
    private String msg;
    private GetUserInfoData data;

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

    public GetUserInfoData getData() {
        return data;
    }

    public void setData(GetUserInfoData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetUserInfo{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
