package com.shushan.homework101.HttpHelper.service.entity.homework;

public class UserInfo {
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "grade=" + grade +
                '}';
    }
}
