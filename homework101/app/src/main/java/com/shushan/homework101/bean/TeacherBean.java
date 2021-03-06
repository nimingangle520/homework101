package com.shushan.homework101.bean;

import java.io.Serializable;

public class TeacherBean implements Serializable{
private String iconUri;
private String name;
private String honor;
private String grade;
private String helpPrice;
private String helpCount;
private String course;
private String exp;
private String checkPrice;
private String checkConut;
private String form;
private String defaultTurorshipPrice;
private String defaultObligationPrice;
private String time;
private String state;

    public String getCheckConut() {
        return checkConut;
    }

    public void setCheckConut(String checkConut) {
        this.checkConut = checkConut;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDefaultObligationPrice() {
        return defaultObligationPrice;
    }

    public void setDefaultObligationPrice(String defaultObligationPrice) {
        this.defaultObligationPrice = defaultObligationPrice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDefaultTurorshipPrice() {
        return defaultTurorshipPrice;
    }

    public void setDefaultTurorshipPrice(String defaultTurorshipPrice) {
        this.defaultTurorshipPrice = defaultTurorshipPrice;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getCheckPrice() {
        return checkPrice;
    }

    public void setCheckPrice(String checkPrice) {
        this.checkPrice = checkPrice;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getHelpPrice() {
        return helpPrice;
    }

    public void setHelpPrice(String helpPrice) {
        this.helpPrice = helpPrice;
    }

    public String getHelpCount() {
        return helpCount;
    }

    public void setHelpCount(String helpCount) {
        this.helpCount = helpCount;
    }

    @Override
    public String toString() {
        return "TeacherBean{" +
                "iconUri='" + iconUri + '\'' +
                ", name='" + name + '\'' +
                ", honor='" + honor + '\'' +
                ", grade='" + grade + '\'' +
                ", helpPrice='" + helpPrice + '\'' +
                ", helpCount='" + helpCount + '\'' +
                ", course='" + course + '\'' +
                ", exp='" + exp + '\'' +
                ", checkPrice='" + checkPrice + '\'' +
                ", checkConut='" + checkConut + '\'' +
                ", form='" + form + '\'' +
                ", defaultTurorshipPrice='" + defaultTurorshipPrice + '\'' +
                ", defaultObligationPrice='" + defaultObligationPrice + '\'' +
                ", time='" + time + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
