package com.example.baselibrary.base;

import android.app.Application;

public class BaseApplication extends Application {
    public static BaseApplication baseContext;

    @Override
    public void onCreate() {
        super.onCreate();
        baseContext=this;
    }


}
