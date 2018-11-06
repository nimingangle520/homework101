package com.shushan.homework101.HttpHelper.service.entity.homework;

import java.util.ArrayList;

public class JobTutorshipData {
    ArrayList<Subject> subject;
    ArrayList<Level> level;

    public ArrayList<Subject> getSubject() {
        return subject;
    }

    public void setSubject(ArrayList<Subject> subject) {
        this.subject = subject;
    }

    public ArrayList<Level> getLevel() {
        return level;
    }

    public void setLevel(ArrayList<Level> level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "JobTutorshipData{" +
                "subject=" + subject +
                ", level=" + level +
                '}';
    }
}
