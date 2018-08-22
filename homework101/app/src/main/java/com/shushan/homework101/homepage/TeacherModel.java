package com.shushan.homework101.homepage;

import android.content.Context;

import com.shushan.homework101.R;
import com.shushan.homework101.bean.TeacherBean;

import java.util.ArrayList;

public class TeacherModel {
    private  ArrayList<TeacherBean> teacherArrayList=new ArrayList<>();
    private  Context context;
    public TeacherModel(Context context) {
        this.context=context;
    }
    public ArrayList<TeacherBean> getTeacherList(){
        TeacherBean teacher=new TeacherBean();
        teacher.setIconUri("");
        teacher.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher.setHelpCount(context.getResources().getString(R.string.homepage_teacher_help_count));
        teacher.setHelpPrice(context.getResources().getString(R.string.homepage_teacher_help_price));
        teacher.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));

        TeacherBean teacher1=new TeacherBean();
        teacher1.setIconUri("");
        teacher1.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher1.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher1.setHelpCount(context.getResources().getString(R.string.homepage_teacher_help_count));
        teacher1.setHelpPrice(context.getResources().getString(R.string.homepage_teacher_help_price));
        teacher1.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));

        TeacherBean teacher2=new TeacherBean();
        teacher2.setIconUri("");
        teacher2.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher2.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher2.setHelpCount(context.getResources().getString(R.string.homepage_teacher_help_count));
        teacher2.setHelpPrice(context.getResources().getString(R.string.homepage_teacher_help_price));
        teacher2.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));

        TeacherBean teacher3=new TeacherBean();
        teacher3.setIconUri("");
        teacher3.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher3.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher3.setHelpCount(context.getResources().getString(R.string.homepage_teacher_help_count));
        teacher3.setHelpPrice(context.getResources().getString(R.string.homepage_teacher_help_price));
        teacher3.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));
        teacherArrayList.add(teacher);
        teacherArrayList.add(teacher3);
        teacherArrayList.add(teacher1);
        teacherArrayList.add(teacher2);
        return teacherArrayList;
    }
}
