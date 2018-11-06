package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.mine.GetUserInfo;

public interface GetUserInfoView extends View {
    void onSuccess(GetUserInfo getUserInfo);
    void onError(String result);
}
