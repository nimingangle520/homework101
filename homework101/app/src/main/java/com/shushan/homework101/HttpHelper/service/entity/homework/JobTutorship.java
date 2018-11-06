package com.shushan.homework101.HttpHelper.service.entity.homework;

public class JobTutorship {
    int error;
    String msg;
    JobTutorshipData data;

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

    public JobTutorshipData getData() {
        return data;
    }

    public void setData(JobTutorshipData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JobTutorship{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
