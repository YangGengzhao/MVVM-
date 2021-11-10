package com.example.baselibrary.error;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import com.example.baselibrary.log.MLog;


//不能与bugly一起用
public class BuglyCrashHandler implements Thread.UncaughtExceptionHandler {

    Thread.UncaughtExceptionHandler exceptionHandler;
    List<Activity> activities = new ArrayList<>();
    static BuglyCrashHandler instance;

    public BuglyCrashHandler(Application application, Thread.UncaughtExceptionHandler handler) {
        exceptionHandler = handler;
        registerActivityListener(application);
        instance = this;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        clearAllActivity();
        if (exceptionHandler != null) {
            exceptionHandler.uncaughtException(t, e);
        }
    }

    public void clearAllActivity() {
        MLog.e("TAG","............................................");
        for (Activity activity : activities) {
            if (null != activity) {
                activity.finish();
            }
        }
    }

    public static BuglyCrashHandler getInstance() {
        return instance;
    }

    private void registerActivityListener(Application application) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    /**
                     *  监听到 Activity创建事件 将该 Activity 加入list
                     */
                    activities.add(activity);
                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    if (null == activities && activities.isEmpty()) {
                        return;
                    }
                    if (activities.contains(activity)) {
                        /**
                         *  监听到 Activity销毁事件 将该Activity 从list中移除
                         */
                        activities.remove(activity);
                    }
                }
            });
        }
    }
}