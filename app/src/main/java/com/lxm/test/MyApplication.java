package com.lxm.test;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        HT.get().bindApplication(this);
        super.onCreate();

    }

}
