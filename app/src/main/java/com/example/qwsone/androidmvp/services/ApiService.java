package com.example.qwsone.androidmvp.services;

import com.example.qwsone.androidmvp.model.UpdateAppInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by AIERXUAN on 2017/9/3.
 */

public interface ApiService {
    //实际开发过程可能的接口方式
    @GET("appVersion.php")
    Observable<UpdateAppInfo> getUpdateInfo(@Query("appname") String appname, @Query("serverVersion") String appVersion);
    //以下方便版本更新接口测试
    @GET("appVersion.php")
    Observable<UpdateAppInfo> getUpdateInfo();
    //测试接口数据
    @GET("appVersion.php")
    Call<ResponseBody> getInfo();
}
