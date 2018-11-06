package com.shushan.homework101.HttpHelper.service.entity.orders;

public class Complain {
    private int label;
    private String complain;

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    @Override
    public String toString() {
        return "Complain{" +
                "label=" + label +
                ", complain='" + complain + '\'' +
                '}';
    }
}
