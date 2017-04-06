package com.gjr.wheelpickerbyrecyclerview;

import android.app.Application;

import com.gjr.wheelpickerbyrecyclerview.data.ProvinceUtil;

import io.realm.Realm;

/**
 * Created by geng
 * on 2017/4/6.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);


        ProvinceUtil.insertAll(this);
    }
}
