package com.example.qwsone.androidmvp.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * Created by AIERXUAN on 2017/9/3.
 */

public interface IDownApk {
    @GET("apk/lasted.apk")
    Call<ResponseBody> loadFile();
}
