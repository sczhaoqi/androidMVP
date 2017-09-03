package com.example.qwsone.androidmvp.dao;

/**
 * Created by AIERXUAN on 2017/8/27.
 */

/**
 * data 层接口定义
 */
public interface TaskDataSource {
    String getStringFromRemote();
    String getStringFromCache();
}
