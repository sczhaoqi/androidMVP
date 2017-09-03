package com.example.qwsone.androidmvp.application;

import android.app.Application;

/**
 * Created by AIERXUAN on 2017/9/3.
 */
public class DemoApplication extends Application {

    public static String AppBaseUrl="http://121.42.216.198/";
    public static String DownApkUrl="apk/lasted.apk";
    private static DemoApplication mInstance = null;
    public static DemoApplication getInstance() {

        if(mInstance==null) {
            mInstance = new DemoApplication();
        }
        return mInstance;
    }
    @Override
    public void onCreate() {
        //应用程序启动时被系统调用
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        //应用程序退出时会被系统调用
        super.onTerminate();
    }

}
