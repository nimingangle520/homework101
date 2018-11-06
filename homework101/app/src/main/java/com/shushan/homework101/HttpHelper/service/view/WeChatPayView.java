package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.orders.WeChatPay;

public interface WeChatPayView extends View{
    void onSuccess(WeChatPay weChatPay);
    void onError(String result);
}
