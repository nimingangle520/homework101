package com.shushan.homework101.mine;

import android.content.Context;

import com.shushan.homework101.R;
import com.shushan.homework101.bean.Teacher;

import java.util.ArrayList;

public class HelpModel {
    private ArrayList<Teacher> teacherArrayList=new ArrayList<>();
    private Context context;
    public HelpModel(Context context) {
        this.context=context;
    }
    public ArrayList<Teacher> getHelpModelList(){
        Teacher teacher=new Teacher();
        teacher.setIconUri("");
        teacher.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));
        teacher.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher.setCourse(context.getResources().getString(R.string.teacher_course));
        teacher.setCheckPrice(context.getResources().getString(R.string.mine_help_obligation_price));

        Teacher teacher1=new Teacher();
        teacher1.setIconUri("");
        teacher1.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher1.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));
        teacher1.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher1.setCourse(context.getResources().getString(R.string.teacher_course));
        teacher1.setCheckPrice(context.getResources().getString(R.string.mine_help_obligation_price));

        Teacher teacher2=new Teacher();
        teacher2.setIconUri("");
        teacher2.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher2.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));
        teacher2.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher2.setCourse(context.getResources().getString(R.string.teacher_course));
        teacher2.setCheckPrice(context.getResources().getString(R.string.mine_help_obligation_price));

        Teacher teacher3=new Teacher();
        teacher3.setIconUri("");
        teacher3.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher3.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));
        teacher3.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher3.setCourse(context.getResources().getString(R.string.teacher_course));
        teacher3.setCheckPrice(context.getResources().getString(R.string.mine_help_obligation_price));
        teacherArrayList.add(teacher);
        teacherArrayList.add(teacher3);
        teacherArrayList.add(teacher1);
        teacherArrayList.add(teacher2);
        return teacherArrayList;
    }
}
