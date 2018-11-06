package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.mine.SetUserInfo;

public interface SetUserInfoView extends View{
    void onSuccess(SetUserInfo setUserInfo);
    void onError(String result);
}
