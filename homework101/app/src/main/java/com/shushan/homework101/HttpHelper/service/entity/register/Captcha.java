package com.shushan.homework101.HttpHelper.service.entity.register;

public class Captcha {
    int error;
    String msg;
    DataCaptcha data;

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

    public DataCaptcha getData() {
        return data;
    }

    public void setData(DataCaptcha data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
