package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.register.Subject;

public interface SubjectView extends View{
    void onSuccess(Subject subject);
    void onError(String result);
}
