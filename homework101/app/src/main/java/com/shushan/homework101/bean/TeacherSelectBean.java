package com.shushan.homework101.bean;

public class TeacherSelectBean {
    private int teacherIcon;
    private String teacherHonor;
    private String teacherHelpPrice;

    public int getTeacherIcon() {
        return teacherIcon;
    }

    public void setTeacherIcon(int teacherIcon) {
        this.teacherIcon = teacherIcon;
    }

    public String getTeacherHonor() {
        return teacherHonor;
    }

    public void setTeacherHonor(String teacherHonor) {
        this.teacherHonor = teacherHonor;
    }

    public String getTeacherHelpPrice() {
        return teacherHelpPrice;
    }

    public void setTeacherHelpPrice(String teacherHelpPrice) {
        this.teacherHelpPrice = teacherHelpPrice;
    }

    @Override
    public String toString() {
        return "TeacherSelectBean{" +
                "teacherIcon=" + teacherIcon +
                ", teacherHonor='" + teacherHonor + '\'' +
                ", teacherHelpPrice='" + teacherHelpPrice + '\'' +
                '}';
    }
}
