package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.teachers.Teacher;

public interface TeachersView extends View{
    void onSuccess(Teacher teacher);
    void onError(String result);
}
