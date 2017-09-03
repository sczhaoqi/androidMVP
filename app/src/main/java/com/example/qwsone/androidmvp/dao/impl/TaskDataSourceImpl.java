package com.example.qwsone.androidmvp.dao.impl;

import com.example.qwsone.androidmvp.dao.TaskDataSource;

/**
 * Created by AIERXUAN on 2017/8/27.
 */

public class TaskDataSourceImpl implements TaskDataSource {
    @Override
    public String getStringFromRemote() {
        return "Hello ";
    }

    @Override
    public String getStringFromCache() {
        return "World, " + "Hello MVP!";
    }
}
