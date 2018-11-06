package com.shushan.homework101.HttpHelper.service.entity.register;

public class DataCaptcha {
    String msgid;

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    @Override
    public String toString() {
        return "DataCaptcha{" +
                "msgid=" + msgid +
                '}';
    }
}
