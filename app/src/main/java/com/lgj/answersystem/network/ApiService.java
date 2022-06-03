package com.lgj.answersystem.network;

import com.lgj.answersystem.bean.ActivationBean;
import com.lgj.answersystem.bean.AllSubjectBean;
import com.lgj.answersystem.bean.ChapterBean;
import com.lgj.answersystem.bean.PlacardBean2;
import com.lgj.answersystem.bean.SmsResponse;
import com.lgj.answersystem.bean.UserLoginBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Aoc on 2016/8/17.
 */
public interface ApiService {
    //获取公告新闻
    @GET("tmsNotice/all")
    Call<PlacardBean2> getPlacard();

    @POST("sso/login")
    @Headers({"Content-type:application/json;charset=UTF-8"})
    Call<UserLoginBean> login(@Body RequestBody requestBody);

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("sso/register")
    Call<UserLoginBean> register(@Body RequestBody requestBod);

    //  获取验证码
    @GET("sso/getAuthCode")
    Call<SmsResponse> getSmsCode(@Query("phone") String phone);

    //获取专业列表及对应的科目列表
    @GET("tmsProfession/all")
    @Headers({"Content-type:application/json;charset=UTF-8"})
    Call<AllSubjectBean> AllListSubjectList();

    //使用激活码
    @POST("/tmsActivationCode/active")
    @Headers({"Content-type:application/json;charset=UTF-8"})
    Call<ActivationBean> useActivationCode(@Body RequestBody requestBod);


    //根据大分类管理获取所有章节练习题库
    @GET("/tmsBigCategory/all")
    @Headers({"Content-type:application/json;charset=UTF-8"})
    Call<ChapterBean> allChapter(@Query("subjectId") int subjectId);

}
