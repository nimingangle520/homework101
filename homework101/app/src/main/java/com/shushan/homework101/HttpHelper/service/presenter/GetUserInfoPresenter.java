package com.shushan.homework101.HttpHelper.service.presenter;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.shushan.homework101.HttpHelper.service.entity.mine.GetUserInfo;
import com.shushan.homework101.HttpHelper.service.manager.DataManager;
import com.shushan.homework101.HttpHelper.service.view.GetUserInfoView;
import com.shushan.homework101.HttpHelper.service.view.View;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.Utils.Utils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subscriptions.CompositeSubscription;

public class GetUserInfoPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private GetUserInfoView getUserInfoView;
    private GetUserInfo mGetUserInfo;

    public GetUserInfoPresenter(Context mContext){
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
        getUserInfoView = (GetUserInfoView) view;

    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void getUserInfoMsg(String userid,String token) {
        manager.getUserInfoMsg(userid,token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.body() != null) {
                        String str = response.body().string().trim();
                        String newStr = Utils.UStr_2_Str(str);
                        LogUtils.d(newStr);
                        Gson gson = new Gson();
                        mGetUserInfo = gson.fromJson(newStr, GetUserInfo.class);

                        if (getUserInfoView != null) {
                            getUserInfoView.onSuccess(mGetUserInfo);
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
                if(t!=null&&!TextUtils.isEmpty(t.getMessage())){
                    t.printStackTrace();
                    LogUtils.e(t.getMessage());
                    getUserInfoView.onError(t.getMessage());

                }
            }
        });
    }
}
