package com.example.qwsone.androidmvp.dao;

/**
 * Created by AIERXUAN on 2017/8/27.
 */

/**
 * 从数据层获取的数据，在这里进行拼装和组合
 */
public class TaskManager {
    TaskDataSource dataSource;

    public TaskManager(TaskDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public String getShowContent() {
        //Todo what you want do on the original data
        return dataSource.getStringFromRemote() + dataSource.getStringFromCache();
    }
}