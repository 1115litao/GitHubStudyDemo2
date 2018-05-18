package com.demotest.app;

import android.app.Application;

import com.demotest.crash.CrashHandler;

/**
 * @author 李涛
 * @description
 * @Date 2017/12/5.
 */


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //处理Error
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
    }
}
