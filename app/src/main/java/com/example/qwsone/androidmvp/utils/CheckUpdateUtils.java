package com.example.qwsone.androidmvp.utils;

import android.util.Log;

import com.example.qwsone.androidmvp.model.UpdateAppInfo;
import com.example.qwsone.androidmvp.services.ApiService;
import com.example.qwsone.androidmvp.services.ServiceFactory;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AIERXUAN on 2017/9/3.
 */

public class CheckUpdateUtils {

    /**
     * 检查更新
     */
    @SuppressWarnings("unused")
    public static void checkUpdate(String appCode, String curVersion,final CheckCallBack updateCallback) {
        ApiService apiService=   ServiceFactory.createServiceFrom(ApiService.class);

        apiService.getUpdateInfo()//测试使用
                //   .apiService.getUpdateInfo(appCode, curVersion)//开发过程中可能使用的
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateAppInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("获取版本信息","出错");
                    }

                    @Override
                    public void onNext(UpdateAppInfo updateAppInfo) {
                        if (updateAppInfo.error_code == 0 || updateAppInfo.data == null ||
                                updateAppInfo.data.updateurl == null) {
                            Log.e("获取版本信息","数据有误");
                            updateCallback.onError(); // 失败
                        } else {
                            updateCallback.onSuccess(updateAppInfo);
                        }
                    }
                });
    }


    public interface CheckCallBack{
        void onSuccess(UpdateAppInfo updateInfo);
        void onError();
    }

}
