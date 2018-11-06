package com.shushan.homework101.HttpHelper.service.entity.homework;

public class JobPublishData {
    private String jobid;

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    @Override
    public String toString() {
        return "JobPublishData{" +
                "jobid='" + jobid + '\'' +
                '}';
    }
}
