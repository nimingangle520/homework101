package com.shushan.homework101.mine.tutorship;

import android.content.Context;

import com.shushan.homework101.R;
import com.shushan.homework101.bean.TeacherBean;

import java.util.ArrayList;

public class HelpModel {
    private ArrayList<TeacherBean> teacherArrayList=new ArrayList<>();
    private Context context;
    public HelpModel(Context context) {
        this.context=context;
    }
    public ArrayList<TeacherBean> getHelpModelList(){
        TeacherBean teacher=new TeacherBean();
        teacher.setIconUri("");
        teacher.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));
        teacher.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher.setCourse(context.getResources().getString(R.string.teacher_course));
        teacher.setCheckPrice(context.getResources().getString(R.string.mine_help_obligation_price));

        TeacherBean teacher1=new TeacherBean();
        teacher1.setIconUri("");
        teacher1.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher1.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));
        teacher1.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher1.setCourse(context.getResources().getString(R.string.teacher_course));
        teacher1.setCheckPrice(context.getResources().getString(R.string.mine_help_obligation_price));

        TeacherBean teacher2=new TeacherBean();
        teacher2.setIconUri("");
        teacher2.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher2.setHonor(context.getResources().getString(R.string.homepage_teacher_honor));
        teacher2.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher2.setCourse(context.getResources().getString(R.string.teacher_course));
        teacher2.setCheckPrice(context.getResources().getString(R.string.mine_help_obligation_price));

        TeacherBean teacher3=new TeacherBean();
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
