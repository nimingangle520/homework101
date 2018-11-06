package com.shushan.homework101.HttpHelper.service.presenter;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.shushan.homework101.HttpHelper.service.entity.orders.WeChatPay;
import com.shushan.homework101.HttpHelper.service.manager.DataManager;
import com.shushan.homework101.HttpHelper.service.view.View;
import com.shushan.homework101.HttpHelper.service.view.WeChatPayView;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.Utils.Utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subscriptions.CompositeSubscription;

public class WeChatPayPresenter implements Presenter{

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private WeChatPayView weChatPayView;
    private WeChatPay mWeChatPay;

    public WeChatPayPresenter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public void onCreate(String baseUrl) {
        manager = new DataManager(mContext,baseUrl);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        weChatPayView = (WeChatPayView) view;

    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void getPayMsg(Map<String,String> mapRequest) {
        manager.getPayMsg(mapRequest).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.body() != null) {
                        String str = response.body().string().trim();
                        String newStr = Utils.UStr_2_Str(str);
                        LogUtils.e(newStr);
                        Gson gson = new Gson();
                        mWeChatPay = gson.fromJson(newStr, WeChatPay.class);

                        if (weChatPayView != null) {
                            weChatPayView.onSuccess(mWeChatPay);
                        }
                    } else {
                        LogUtils.d("is null");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                if(t!=null){
                    LogUtils.e(t.getMessage());
                    weChatPayView.onError(t.getMessage());

                }
            }
        });
    }
}
