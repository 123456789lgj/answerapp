package com.lgj.answersystem.util;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by lqx on 2019/4/15.
 */
public class RequestUtils {
    public static RequestBody getRequestBody(Object params){
        Gson gson = new Gson();
        String jsonStr = gson.toJson(params);
        System.out.println("jsonStr : " + jsonStr);
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),jsonStr);
    }
}
