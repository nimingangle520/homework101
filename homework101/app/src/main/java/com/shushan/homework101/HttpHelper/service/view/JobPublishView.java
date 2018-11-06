package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.homework.JobPublish;

public interface JobPublishView extends View{
    void onSuccess(JobPublish jobPublish);
    void onError(String result);
}
