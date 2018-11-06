package com.shushan.homework101.HttpHelper.service.entity.homework;

import java.util.ArrayList;

public class Subject {
    private int grade_id;
    private String grade;
    private ArrayList<SubjectData> data;

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public ArrayList<SubjectData> getData() {
        return data;
    }

    public void setData(ArrayList<SubjectData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "grade_id=" + grade_id +
                ", grade='" + grade + '\'' +
                ", data=" + data +
                '}';
    }
}
