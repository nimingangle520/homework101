package com.shushan.homework101.HttpHelper.service.view;


import com.shushan.homework101.HttpHelper.service.entity.register.Register;

public interface RegisterView extends View {
    void onSuccess(Register register);
    void onError(String result);
}
