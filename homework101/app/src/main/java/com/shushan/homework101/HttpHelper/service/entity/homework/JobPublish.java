package com.shushan.homework101.HttpHelper.service.entity.homework;

public class JobPublish {
    int error;
    String msg;
    JobPublishData data;

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

    public JobPublishData getData() {
        return data;
    }

    public void setData(JobPublishData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JobPublish{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
