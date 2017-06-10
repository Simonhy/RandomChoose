package com.isoftstone.randomchoose.utils;

import android.app.Activity;

import java.util.Stack;

public class ActivityUtils {

    private static Stack<Activity> mActivityStack;

    /**
     * 添加一个Activity到堆栈中
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (null == mActivityStack) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 从堆栈中移除指定的Activity
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
        }
    }

    /**
     * 获取顶部的Activity
     * @return 顶部的Activity
     */
     public static Activity getTopActivity() {
        if (mActivityStack.isEmpty()) {
            return null;
        } else {
            return mActivityStack.get(mActivityStack.size() - 1);
        }
    }

    /**
     * 结束所有的Activity，退出应用
     */
    public static void removeAllActivity() {
        if (mActivityStack != null && mActivityStack.size() > 0) {
            for (Activity activity : mActivityStack) {
                activity.finish();
            }
        }
    }
}