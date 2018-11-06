package com.shushan.homework101.HttpHelper.service.entity.orders;

import java.util.Arrays;

public class OrdersData {

    private float price;
    private String oid;
    private int tch_id;
    private int status;
    private int create_time;
    private int label;
    private int jobid;
    private String[] job_pic;
    private String grade;
    private String subject;
    private String name;
    private String trait;
    private String level;
    private float pay_money;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getTch_id() {
        return tch_id;
    }

    public void setTch_id(int tch_id) {
        this.tch_id = tch_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public String[] getJob_pic() {
        return job_pic;
    }

    public void setJob_pic(String[] job_pic) {
        this.job_pic = job_pic;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public float getPay_money() {
        return pay_money;
    }

    public void setPay_money(float pay_money) {
        this.pay_money = pay_money;
    }

    @Override
    public String toString() {
        return "OrdersData{" +
                "price=" + price +
                ", oid='" + oid + '\'' +
                ", tch_id=" + tch_id +
                ", status=" + status +
                ", create_time=" + create_time +
                ", label=" + label +
                ", jobid=" + jobid +
                ", job_pic=" + Arrays.toString(job_pic) +
                ", grade='" + grade + '\'' +
                ", subject='" + subject + '\'' +
                ", name='" + name + '\'' +
                ", trait='" + trait + '\'' +
                ", level='" + level + '\'' +
                ", pay_money=" + pay_money +
                '}';
    }
}
