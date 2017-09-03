package com.example.qwsone.androidmvp.model;

/**
 * Created by AIERXUAN on 2017/9/3.
 */

public class FileLoadingBean {
    /**
     * 文件大小
     */
    long total;
    /**
     * 已下载大小
     */
    long progress;

    public long getProgress() {
        return progress;
    }

    public long getTotal() {
        return total;
    }

    public FileLoadingBean(long total, long progress) {
        this.total = total;
        this.progress = progress;
    }
}
