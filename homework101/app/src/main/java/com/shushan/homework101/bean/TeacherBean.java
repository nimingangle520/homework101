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
        return "teacher{" +
                "iconUri='" + iconUri + '\'' +
                ", name='" + name + '\'' +
                ", honor='" + honor + '\'' +
                ", grade='" + grade + '\'' +
                ", helpPrice='" + helpPrice + '\'' +
                ", helpCount='" + helpCount + '\'' +
                '}';
    }
}
