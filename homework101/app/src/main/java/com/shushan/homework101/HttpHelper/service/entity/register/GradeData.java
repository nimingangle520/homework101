package com.shushan.homework101.HttpHelper.service.entity.register;

public class GradeData {
    private int id;
    private String grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "GradeData{" +
                "id=" + id +
                ", grade='" + grade + '\'' +
                '}';
    }
}
