package com.shushan.homework101.HttpHelper.service.entity.homepage;

import java.util.ArrayList;
import java.util.Arrays;

public class HomepageData {

    private String[] banner;
    private ArrayList<Job> job;
    private ArrayList<Teachers> teacher;
    private int count;

    public ArrayList<Teachers> getTeacher() {
        return teacher;
    }

    public void setTeacher(ArrayList<Teachers> teacher) {
        this.teacher = teacher;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String[] getBanner() {
        return banner;
    }

    public void setBanner(String[] banner) {
        this.banner = banner;
    }

    public ArrayList<Job> getJob() {
        return job;
    }

    public void setJob(ArrayList<Job> job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "HomepageData{" +
                "banner=" + Arrays.toString(banner) +
                ", job=" + job +
                ", teacher=" + teacher +
                ", count=" + count +
                '}';
    }
}
