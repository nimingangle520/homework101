package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.register.Login;

public interface LoginView extends View{
    void onSuccess(Login login);
    void onError(String result);
}
