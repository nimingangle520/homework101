package com.shushan.homework101.HttpHelper.service.entity.register;

public class SubjectData {
    private int id;
    private String subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", subject='" + subject + '\'' +
                '}';
    }
}
