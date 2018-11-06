package com.shushan.homework101.HttpHelper.service.entity.homepage;

import java.util.Arrays;

public class Teachers {
    private int tid;
    private String tch_name;
    private String intro;
    private String trait;
    private String[] grade;
    private String subject;
    private float score;
    private int stu_num;
    private int follower_num;
    private float guide_price;
    private int guide_num;
    private int check_num;
    private String level;
    private int is_ID_auth;
    private int is_teach_auth;
    private int is_edu_auth;
    private int is_skill_auth;
    private int guide_time;
    private int edu_stage;
    private int edu_exp;
    private String college;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTch_name() {
        return tch_name;
    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String[] getGrade() {
        return grade;
    }

    public void setGrade(String[] grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getStu_num() {
        return stu_num;
    }

    public void setStu_num(int stu_num) {
        this.stu_num = stu_num;
    }

    public int getFollower_num() {
        return follower_num;
    }

    public void setFollower_num(int follower_num) {
        this.follower_num = follower_num;
    }

    public float getGuide_price() {
        return guide_price;
    }

    public void setGuide_price(float guide_price) {
        this.guide_price = guide_price;
    }

    public int getGuide_num() {
        return guide_num;
    }

    public void setGuide_num(int guide_num) {
        this.guide_num = guide_num;
    }

    public int getCheck_num() {
        return check_num;
    }

    public void setCheck_num(int check_num) {
        this.check_num = check_num;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getIs_ID_auth() {
        return is_ID_auth;
    }

    public void setIs_ID_auth(int is_ID_auth) {
        this.is_ID_auth = is_ID_auth;
    }

    public int getIs_teach_auth() {
        return is_teach_auth;
    }

    public void setIs_teach_auth(int is_teach_auth) {
        this.is_teach_auth = is_teach_auth;
    }

    public int getIs_edu_auth() {
        return is_edu_auth;
    }

    public void setIs_edu_auth(int is_edu_auth) {
        this.is_edu_auth = is_edu_auth;
    }

    public int getIs_skill_auth() {
        return is_skill_auth;
    }

    public void setIs_skill_auth(int is_skill_auth) {
        this.is_skill_auth = is_skill_auth;
    }

    public int getGuide_time() {
        return guide_time;
    }

    public void setGuide_time(int guide_time) {
        this.guide_time = guide_time;
    }

    public int getEdu_stage() {
        return edu_stage;
    }

    public void setEdu_stage(int edu_stage) {
        this.edu_stage = edu_stage;
    }

    public int getEdu_exp() {
        return edu_exp;
    }

    public void setEdu_exp(int edu_exp) {
        this.edu_exp = edu_exp;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "tid=" + tid +
                ", tch_name='" + tch_name + '\'' +
                ", intro='" + intro + '\'' +
                ", trait='" + trait + '\'' +
                ", grade=" + Arrays.toString(grade) +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                ", stu_num=" + stu_num +
                ", follower_num=" + follower_num +
                ", guide_price=" + guide_price +
                ", guide_num=" + guide_num +
                ", check_num=" + check_num +
                ", level='" + level + '\'' +
                ", is_ID_auth=" + is_ID_auth +
                ", is_teach_auth=" + is_teach_auth +
                ", is_edu_auth=" + is_edu_auth +
                ", is_skill_auth=" + is_skill_auth +
                ", guide_time=" + guide_time +
                ", edu_stage=" + edu_stage +
                ", edu_exp=" + edu_exp +
                ", college='" + college + '\'' +
                '}';
    }
}
