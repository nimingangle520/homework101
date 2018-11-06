package com.shushan.homework101.HttpHelper.service.entity.orders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeChatPayData implements Serializable{
    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String mPackage;
    private String partnerid;
    private String prepayid;
    private long timestamp;
    private String sign;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getmPackage() {
        return mPackage;
    }

    public void setmPackage(String mPackage) {
        this.mPackage = mPackage;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "WeChatPayData{" +
                "appid='" + appid + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", mPackage='" + mPackage + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", timestamp=" + timestamp +
                ", sign='" + sign + '\'' +
                '}';
    }
}
