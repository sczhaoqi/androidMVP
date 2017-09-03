package com.example.qwsone.androidmvp.services;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.example.qwsone.androidmvp.application.DemoApplication.AppBaseUrl;

/**
 * Created by AIERXUAN on 2017/9/3.
 */

public class ServiceFactory {
    private static final String BASEURL=AppBaseUrl;
    public static <T> T createServiceFrom(final Class<T> serviceClass) {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return adapter.create(serviceClass);
    }
}

