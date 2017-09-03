package com.example.qwsone.androidmvp.presenter;


import com.example.qwsone.androidmvp.dao.TaskManager;
import com.example.qwsone.androidmvp.dao.impl.TaskDataSourceImpl;
import com.example.qwsone.androidmvp.dao.impl.TaskDataSourceTestImpl;
import com.example.qwsone.androidmvp.view.MainView;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by AIERXUAN on 2017/8/27.
 */

public class MainPresenter {

    MainView mainView;
    TaskManager taskData;

    public MainPresenter() {
        this.taskData = new TaskManager(new TaskDataSourceImpl());
    }

    public MainPresenter test() {
        this.taskData = new TaskManager(new TaskDataSourceTestImpl());
        return this;
    }

    public MainPresenter addTaskListener(MainView viewListener) {
        this.mainView = viewListener;
        return this;
    }

    public void getString() {
        Func1 dataAction = new Func1<String,String>() {
            @Override
            public String call(String param) {
                return  "Use RxJava "+taskData.getShowContent();
            }
        };
        final Action1 viewAction = new Action1<String>() {
            @Override
            public void call( String str) {
                mainView.onShowString(str);
            }
        };
        Observable.just("")
                .observeOn(Schedulers.io())
                .map(dataAction)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewAction);

    }
}