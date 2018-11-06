package com.shushan.homework101.HttpHelper.service.entity.orders;

public class Comment {
    private float score;
    private String comment;

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
