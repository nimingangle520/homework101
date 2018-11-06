package com.shushan.homework101.HttpHelper.service;


import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface RetrofitService {

    @FormUrlEncoded
    @POST("admin/code/get")
    Call<ResponseBody> getCaptchaMsg( @Field("mobile") String mobile,@Field("cate") String cate);

    @FormUrlEncoded
    @POST("student/user/register")
    Call<ResponseBody> getCallRegisterMsg(@FieldMap Map<String,String> requests);

    @FormUrlEncoded
    @POST("admin/code/check")
    Call<ResponseBody> getVerifyCaptchaMsg(@Field("mobile") String mobile, @Field("code") String code);

    @FormUrlEncoded
    @POST("student/user/login")
    Call<ResponseBody> getLoginMsg(@FieldMap Map<String,String> requests);

    @FormUrlEncoded
    @POST("student/job/check")
    Call<ResponseBody> getJobCheckMsg(@Field("userid") String userid,@Field("token") String token);

    @FormUrlEncoded
    @POST("/student/job/guide")
    Call<ResponseBody> getJobTutorshipMsg(@Field("userid") String userid,@Field("token") String token);

    /*@FormUrlEncoded
    @POST("student/upload")
    Call<ResponseBody> getUploadMsg(@Field("pic[]") String[] pic,@Field("t") String t,@Field("dir") String dir);*/

    @FormUrlEncoded
    @POST("student/upload")
    Call<ResponseBody> getUploadMsg(@Field("pic") String pic,@Field("t") String t,@Field("dir") String dir);

    @FormUrlEncoded
    @POST("student/job/publish")
    Call<ResponseBody> getJobPublishMsg(@Field("userid") String userid,@Field("images") String images,@Field("label") int label,
                                        @Field("grade") String grade, @Field("subject") String subject, @Field("price") float price,
                                        @Field("start_time") String start_time,@Field("token") String token);


    @FormUrlEncoded
    @POST("student")
    Call<ResponseBody> getHomepageMsg(@Field("userid") String userid,@Field("token") String token);


    @POST("student/job/grade")
    Call<ResponseBody> getGradeMsg();

    @POST("student/job/subject")
    Call<ResponseBody> getSubjectMsg();

    @FormUrlEncoded
    @POST("student/order")
    Call<ResponseBody> getOrdersMsg(@FieldMap Map<String,String> requests);

    @FormUrlEncoded
    @POST("student/order/pay")
    Call<ResponseBody> getPayMsg(@FieldMap Map<String,String> requests);


    @FormUrlEncoded
    @POST("student/teacher")
    Call<ResponseBody> getTeacherMsg(@Field("userid") String userid,@Field("token") String token);

    @FormUrlEncoded
    @POST("student/order/detail")
    Call<ResponseBody> getOrdersDetailsMsg(@Field("userid") String userid,@Field("token") String token,@Field("oid") String oid);

    @FormUrlEncoded
    @POST("student/user/setinfo")
    Call<ResponseBody> setUserInfoMsg(@FieldMap Map<String,String> requests);

    @FormUrlEncoded
    @POST("student/user/userinfo")
    Call<ResponseBody> getUserInfoMsg(@Field("userid") String userid,@Field("token") String token);

}
