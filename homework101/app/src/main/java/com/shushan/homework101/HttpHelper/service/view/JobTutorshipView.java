package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.homework.JobTutorship;

public interface JobTutorshipView extends View {

    void onSuccess(JobTutorship jobTutorship);
    void onError(String result);
}
