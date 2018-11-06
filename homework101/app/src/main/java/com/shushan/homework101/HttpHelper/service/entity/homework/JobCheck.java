package com.shushan.homework101.HttpHelper.service.entity.homework;

public class JobCheck {
    int error;
    String msg;
    JobCheckData data;

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

    public JobCheckData getData() {
        return data;
    }

    public void setData(JobCheckData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JobCheck{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
