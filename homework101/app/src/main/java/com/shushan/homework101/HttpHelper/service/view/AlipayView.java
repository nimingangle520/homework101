package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.orders.Alipay;

public interface AlipayView extends View{
    void onSuccess(Alipay alipays);
    void onError(String result);
}
