package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.homework.JobCheck;

public interface JobCheckView extends View{
    void onSuccess(JobCheck jobCheck);
    void onError(String result);
}
