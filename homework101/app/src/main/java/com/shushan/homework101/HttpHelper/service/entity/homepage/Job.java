package com.shushan.homework101.HttpHelper.service.entity.homepage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Job implements Parcelable {
    private int job_id;
    private String[] images;
    private long start_time;
    private String grade;
    private String subject;
    private int is_receive;
    private long create_time;
    private float price;
    private int label;
    private int yuyue;

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getIs_receive() {
        return is_receive;
    }

    public void setIs_receive(int is_receive) {
        this.is_receive = is_receive;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getYuyue() {
        return yuyue;
    }

    public void setYuyue(int yuyue) {
        this.yuyue = yuyue;
    }

    @Override
    public String toString() {
        return "Job{" +
                "job_id=" + job_id +
                ", images=" + Arrays.toString(images) +
                ", start_time=" + start_time +
                ", grade='" + grade + '\'' +
                ", subject='" + subject + '\'' +
                ", is_receive=" + is_receive +
                ", create_time=" + create_time +
                ", price=" + price +
                ", label=" + label +
                ", yuyue=" + yuyue +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.job_id);
        dest.writeStringArray(this.images);
        dest.writeLong(this.start_time);
        dest.writeString(this.grade);
        dest.writeString(this.subject);
        dest.writeInt(this.is_receive);
        dest.writeLong(this.create_time);
        dest.writeFloat(this.price);
        dest.writeInt(this.label);
        dest.writeInt(this.yuyue);
    }

    public Job() {
    }

    protected Job(Parcel in) {
        this.job_id = in.readInt();
        this.images = in.createStringArray();
        this.start_time = in.readLong();
        this.grade = in.readString();
        this.subject = in.readString();
        this.is_receive = in.readInt();
        this.create_time = in.readLong();
        this.price = in.readFloat();
        this.label = in.readInt();
        this.yuyue = in.readInt();
    }

    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel source) {
            return new Job(source);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };
}
