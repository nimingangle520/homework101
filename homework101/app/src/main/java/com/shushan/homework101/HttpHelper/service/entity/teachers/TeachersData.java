package com.shushan.homework101.HttpHelper.service.entity.teachers;

import java.util.ArrayList;

public class TeachersData {
    ArrayList<Recommend> recommend;
    ArrayList<Attention> attention;

    public ArrayList<Recommend> getRecommend() {
        return recommend;
    }

    public void setRecommend(ArrayList<Recommend> recommend) {
        this.recommend = recommend;
    }

    public ArrayList<Attention> getAttention() {
        return attention;
    }

    public void setAttention(ArrayList<Attention> attention) {
        this.attention = attention;
    }

    @Override
    public String toString() {
        return "TeachersData{" +
                "recommend=" + recommend +
                ", attention=" + attention +
                '}';
    }
}
