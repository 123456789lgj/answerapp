package com.lgj.answersystem.network;

import com.lgj.answersystem.MyApplication;
import com.lgj.answersystem.constant.Constant;
import com.lgj.answersystem.util.PrefUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static volatile RetrofitUtil instance;
    private static ApiService mApiService;


    public RetrofitUtil() {
        initRetrofit();
    }

    private void initRetrofit() {
        //配置超时时间等信息
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(70L, TimeUnit.SECONDS)
                .readTimeout(70L, TimeUnit.SECONDS)
                //其他配置
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        // 请求中加入token，用于鉴权
                        Request.Builder builder = chain.request().newBuilder();
                        boolean isLogin = PrefUtils.getBoolean(MyApplication.mApplicationContext, "isLogin", false);
                        if(isLogin){
                            String token = PrefUtils.getString(MyApplication.mApplicationContext, "token", "");
                            builder.addHeader("Authorization", "Bearer " + token);
                            System.out.println("lgj Bearer token : " + token);
                        }
                        Request request = builder.build();
                        return chain.proceed(request);
                    }
                })
                .build();





        // 创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        // 生成ApiService接口代理
        mApiService = retrofit.create(ApiService.class);
    }

    private static RetrofitUtil getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtil.class) {
                if (instance == null) {
                    instance = new RetrofitUtil();
                }
            }
        }
        return instance;
    }

    public static ApiService getApiService() {
        return getInstance().mApiService;
    }

}
