package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.register.Grade;

public interface GradeView extends View {
    void onSuccess(Grade grade);
    void onError(String result);
}
