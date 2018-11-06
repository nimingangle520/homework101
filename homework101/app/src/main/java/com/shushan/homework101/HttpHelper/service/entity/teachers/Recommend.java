package com.shushan.homework101.HttpHelper.service.entity.teachers;

import java.util.Arrays;

public class Recommend {
    private int tch_id;
    private String tch_name;
    private String trait;
    private String [] grade;
    private String subject;
    private float score;
    private float guide_price;
    private String level;
    private int online;
    private String third_id;

    public int getTch_id() {
        return tch_id;
    }

    public void setTch_id(int tch_id) {
        this.tch_id = tch_id;
    }

    public String getTch_name() {
        return tch_name;
    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String[] getGrade() {
        return grade;
    }

    public void setGrade(String[] grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getGuide_price() {
        return guide_price;
    }

    public void setGuide_price(float guide_price) {
        this.guide_price = guide_price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getThird_id() {
        return third_id;
    }

    public void setThird_id(String third_id) {
        this.third_id = third_id;
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "tch_id=" + tch_id +
                ", tch_name='" + tch_name + '\'' +
                ", trait='" + trait + '\'' +
                ", grade=" + Arrays.toString(grade) +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                ", guide_price=" + guide_price +
                ", level='" + level + '\'' +
                ", online=" + online +
                ", third_id='" + third_id + '\'' +
                '}';
    }
}
