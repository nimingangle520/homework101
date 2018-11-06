package com.shushan.homework101.HttpHelper.service.manager;

import android.content.Context;

import com.shushan.homework101.HttpHelper.service.RetrofitHelper;
import com.shushan.homework101.HttpHelper.service.RetrofitService;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;


public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context, String baseUrl){
        this.mRetrofitService = RetrofitHelper.getInstance(context,baseUrl).getServer();
    }

    public Call<ResponseBody> getCaptchaMsg( String mobile,String cate){
        return  mRetrofitService.getCaptchaMsg(mobile,cate);
    }


    public Call<ResponseBody> getCallRegisterMsg(Map<String,String> requests){
        return  mRetrofitService.getCallRegisterMsg(requests);
    }

    public Call<ResponseBody> getVerifyCaptchaMsg(String mobile, String code){
        return  mRetrofitService.getVerifyCaptchaMsg(mobile,code);
    }

    public Call<ResponseBody> getLoginMsg(Map<String,String> requests){
        return  mRetrofitService.getLoginMsg(requests);
    }

    public Call<ResponseBody> getJobCheckMsg(String userid,String token){
        return  mRetrofitService.getJobCheckMsg(userid,token);
    }
    public Call<ResponseBody> getJobTutorshipMsg(String userid,String token){
        return  mRetrofitService.getJobTutorshipMsg(userid,token);
    }
    public Call<ResponseBody> getUploadMsg(String pic,String t,String dir){
        return  mRetrofitService.getUploadMsg(pic,t,dir);
    }

    public Call<ResponseBody> getJobPublishMsg(String userid,String  images,int label,String grade,String subject,float price,String start_time,String token){
        return  mRetrofitService.getJobPublishMsg(userid,images,label,grade,subject,price,start_time,token);
    }

    public Call<ResponseBody> getHomepageMsg(String userid,String token){
        return  mRetrofitService.getHomepageMsg(userid,token);
    }

    public Call<ResponseBody> getGradeMsg(){
        return  mRetrofitService.getGradeMsg();
    }

    public Call<ResponseBody> getSubjectMsg(){
        return  mRetrofitService.getSubjectMsg();
    }

    public Call<ResponseBody> getOrdersMsg(Map<String,String> requests){
        return  mRetrofitService.getOrdersMsg(requests);
    }

    public Call<ResponseBody> getPayMsg(Map<String,String> requests){
        return  mRetrofitService.getPayMsg(requests);
    }

    public Call<ResponseBody> getTeacherMsg(String userid,String token){
        return  mRetrofitService.getTeacherMsg(userid,token);
    }
    public Call<ResponseBody> getOrdersDetailsMsg(String userid,String token,String oid){
        return  mRetrofitService.getOrdersDetailsMsg(userid,token,oid);
    }

    public Call<ResponseBody> setUserInfoMsg(Map<String,String> requests){
        return  mRetrofitService.setUserInfoMsg(requests);
    }

    public Call<ResponseBody> getUserInfoMsg(String userid,String token) {
        return mRetrofitService.getUserInfoMsg(userid, token);
    }
}
