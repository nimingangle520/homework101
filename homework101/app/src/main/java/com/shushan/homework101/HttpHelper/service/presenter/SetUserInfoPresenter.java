package com.shushan.homework101.HttpHelper.service.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.shushan.homework101.HttpHelper.service.entity.mine.SetUserInfo;
import com.shushan.homework101.HttpHelper.service.manager.DataManager;
import com.shushan.homework101.HttpHelper.service.view.SetUserInfoView;
import com.shushan.homework101.HttpHelper.service.view.View;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.Utils.Utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subscriptions.CompositeSubscription;

public class SetUserInfoPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private SetUserInfoView setUserInfoView;
    private SetUserInfo mSetUserInfo;

    public SetUserInfoPresenter(Context mContext){
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
        setUserInfoView = (SetUserInfoView) view;

    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void setUserInfoMsg(final Map<String,String> mapRequest) {
        manager.setUserInfoMsg(mapRequest).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.body() != null) {
                        String str = response.body().string().trim();
                        String newStr = Utils.UStr_2_Str(str);
                        LogUtils.d(newStr);
                        Gson gson = new Gson();
                        mSetUserInfo = gson.fromJson(newStr, SetUserInfo.class);

                        if (setUserInfoView != null) {
                            setUserInfoView.onSuccess(mSetUserInfo);
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
                    setUserInfoView.onError(t.getMessage());

                }
            }
        });
    }
}
