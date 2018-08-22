package com.shushan.homework101.teacher;

import android.content.Context;

import com.shushan.homework101.R;
import com.shushan.homework101.bean.TeacherBean;

import java.util.ArrayList;

public class RecommendTeacherModel{
    private ArrayList<TeacherBean> teacherArrayList = new ArrayList<>();
    private Context context;

    public RecommendTeacherModel(Context context) {
        this.context = context;
    }

    public ArrayList<TeacherBean> getTeacherList() {
        TeacherBean teacher = new TeacherBean();
        teacher.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher.setCourse(context.getResources().getString(R.string.teacher_course));
        teacher.setExp(context.getResources().getString(R.string.teacher_exp));
        teacher.setHelpCount(context.getResources().getString(R.string.homepage_teacher_help_count));
        teacher.setHelpPrice(context.getResources().getString(R.string.homepage_teacher_help_price));
        teacher.setGrade(context.getResources().getString(R.string.teacher_grade));


        TeacherBean teacher1 = new TeacherBean();
        teacher1.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher1.setCourse(context.getResources().getString(R.string.teacher_course));
        teacher1.setExp(context.getResources().getString(R.string.teacher_exp));
        teacher1.setHelpCount(context.getResources().getString(R.string.homepage_teacher_help_count));
        teacher1.setHelpPrice(context.getResources().getString(R.string.homepage_teacher_help_price));
        teacher1.setGrade(context.getResources().getString(R.string.teacher_grade));

        teacherArrayList.add(teacher);
        teacherArrayList.add(teacher1);
        return teacherArrayList;
    }
}