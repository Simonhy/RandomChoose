package com.isoftstone.randomchoose;

import android.app.Application;

import com.isoftstone.randomchoose.utils.CrashHandlerUtils;

/**
 * Created by yonghuangl on 2017/6/10.
 */
public class MApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //对日志的捕获处理
        CrashHandlerUtils.getInstance().init(getApplicationContext());
    }
}
