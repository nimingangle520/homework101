package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.register.Captcha;

public interface CaptchaView extends View{
        void onSuccess(Captcha captcha);
        void onError(String result);
}
