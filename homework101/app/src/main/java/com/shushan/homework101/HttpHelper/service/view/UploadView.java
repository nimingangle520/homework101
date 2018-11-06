package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.homework.UploadPic;

public interface UploadView extends View{
    void onSuccess(UploadPic uploadPic);
    void onError(String result);
}
