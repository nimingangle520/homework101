package com.shushan.homework101.HttpHelper.service.presenter;


import android.content.Intent;

import com.shushan.homework101.HttpHelper.service.view.View;


/**
 * Created by win764-1 on 2016/12/12.
 */

public interface Presenter {
    void onCreate(String baseUrl);

    void onStart();

    void onStop();

    void pause();

    void attachView(View view);

    void attachIncomingIntent(Intent intent);
}
