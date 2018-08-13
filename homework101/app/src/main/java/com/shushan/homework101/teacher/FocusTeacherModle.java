package com.shushan.homework101.teacher;

import android.content.Context;

import com.shushan.homework101.R;
import com.shushan.homework101.bean.Teacher;

import java.util.ArrayList;

public class FocusTeacherModle {
    private ArrayList<Teacher> teacherArrayList = new ArrayList<>();
    private Context context;

    public FocusTeacherModle(Context context) {
        this.context = context;
    }

    public ArrayList<Teacher> getTeacherList() {
        Teacher teacher = new Teacher();
        teacher.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher.setCourse(context.getResources().getString(R.string.teacher_course));


        Teacher teacher1 = new Teacher();
        teacher1.setName(context.getResources().getString(R.string.homepage_teacher_name));
        teacher1.setGrade(context.getResources().getString(R.string.homepage_teacher_grade));
        teacher1.setCourse(context.getResources().getString(R.string.teacher_course));

        teacherArrayList.add(teacher);
        teacherArrayList.add(teacher1);
        return teacherArrayList;
    }
}
