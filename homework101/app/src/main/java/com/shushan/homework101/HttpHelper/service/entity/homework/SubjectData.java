package com.shushan.homework101.HttpHelper.service.entity.homework;

public class SubjectData {
    private String sub_id;
    private String subject;

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "SubjectData{" +
                "sub_id='" + sub_id + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
