package com.shushan.homework101;

import android.content.Context;

import com.shushan.homework101.HttpHelper.service.entity.orders.WeChatPayData;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayUtils {
    private IWXAPI iwxapi; //微信支付api

    private WeChatPayData data;

    public WXPayUtils(WeChatPayData data) {
        this.data = data;
    }

    /**
     * 调起微信支付的方法
     **/
    public void toWXPay(Context context) {
        iwxapi = WXAPIFactory.createWXAPI(context, null); //初始化微信api
        iwxapi.registerApp(data.getAppid()); //注册appid  appid可以在开发平台获取

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，
                request.appId = data.getAppid();
                request.partnerId = data.getPartnerid();
                request.prepayId = data.getPrepayid();
                request.packageValue = data.getmPackage();
                request.nonceStr = data.getNoncestr();
                request.timeStamp =data.getTimestamp()+"";
                request.sign = data.getSign();
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
