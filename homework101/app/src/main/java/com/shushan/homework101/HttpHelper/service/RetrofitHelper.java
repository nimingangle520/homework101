package com.shushan.homework101.HttpHelper.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper {

    private Context mCntext;
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    private  OkHttpClient client=new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .build();


    public static RetrofitHelper getInstance(Context context, String baseUrl){
        if (instance == null){
            instance = new RetrofitHelper(context,baseUrl);
        }
        return instance;
    }
    private RetrofitHelper(Context mContext, String baseUrl){
        mCntext = mContext;
        //client = genericClient();
        init(baseUrl);
    }

    private void init(String baseUrl) {
        resetApp(baseUrl);
    }

    private void resetApp(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                //.baseUrl("http://api.jyeoo.com/")
                .baseUrl(baseUrl)
                .client(client)
                //.addConverterFactory(factory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }
    /*public  OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("PlatformName", "AndroidPhone")
                                .addHeader("Platform", "2")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Mac", "94:63:72:2f:48:62")
                                .addHeader("DeviceID", "94:63:72:2f:48:62")
                                .addHeader("VersionName", "ystudy3.6.6")
                                .addHeader("Version", "128")
                                .addHeader("FINGERPRINT", "OnePlus/OnePlus5T/OnePlus5T:7.1.1/NMF26X/11280212:user/release-keys")
                                .addHeader("IMEI", "868040033198109")
                                .addHeader("Package", "jyeoo.app.ystudy")
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();

        return httpClient;
    }*/
/*    public static OkHttpClient getClient(Context context) {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new ReceivedCookiesInterceptor(context));
        client.interceptors().add(new AddCookiesInterceptor(context));
        return client;
    }*/
}
