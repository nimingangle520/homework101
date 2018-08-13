package com.shushan.homework101.homepage;

import android.content.Context;

import com.shushan.homework101.R;
import com.shushan.homework101.bean.Teacher;

import java.util.ArrayList;

public class TeacherModel {
    private  ArrayList<Teacher> teacherArrayList=new ArrayList<>();
    private  Context context;
    public TeacherModel(Context context) {
        this.context=context;
    }
    public ArrayList<Teacher> getTeacherList(){
        Teacher teacher=new Teacher();
        teacher.setIconUri("");
        teacher.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher.setHelpCount(context.getResources().getString(R.string.homepage_teacher_help_count));
        teacher.setHelpPrice(context.getResources().getString(R.string.homepage_teacher_help_price));
        teacher.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));

        Teacher teacher1=new Teacher();
        teacher1.setIconUri("");
        teacher1.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher1.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher1.setHelpCount(context.getResources().getString(R.string.homepage_teacher_help_count));
        teacher1.setHelpPrice(context.getResources().getString(R.string.homepage_teacher_help_price));
        teacher1.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));

        Teacher teacher2=new Teacher();
        teacher2.setIconUri("");
        teacher2.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher2.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher2.setHelpCount(context.getResources().getString(R.string.homepage_teacher_help_count));
        teacher2.setHelpPrice(context.getResources().getString(R.string.homepage_teacher_help_price));
        teacher2.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));

        Teacher teacher3=new Teacher();
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
