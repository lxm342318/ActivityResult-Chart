package com.lxm.test.utils;

import android.app.Activity;
import java.util.Stack;

/**
 * @Copyright (C), 2020
 * @Author: ym
 * @Date:
 * @Description:
 */
public class ActivityManager {

    private static volatile Stack<Activity> activityStack;
    private static volatile ActivityManager sInstance;

    private ActivityManager() {
    }

    public static synchronized ActivityManager getInstance() {
        if (sInstance == null) {
            synchronized (ActivityManager.class) {
                if (sInstance == null) {
                    sInstance = new ActivityManager();
                    if (activityStack == null) {
                        activityStack = new Stack<>();
                    }
                }
            }
        }
        return sInstance;
    }

    public int size() {
        return activityStack.size();
    }

    public void popActivity() {
        if (size() > 0) {
            Activity activity = activityStack.lastElement();
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
            }
        }
    }

    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
        }
    }

    public Activity currentActivity() {
        Activity activity = null;
        if (activityStack != null && !activityStack.isEmpty()) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public void popAllActivityExceptOne(Class<?> cls) {
        if (!activityStack.isEmpty()) {
            int stockLen = activityStack.size() - 1;
            for (int i = stockLen; i >= 0; i--) {
                Activity activity = activityStack.get(i);
                if (null != activity) {
                    if (cls != null && activity.getClass().equals(cls)) {
                    } else {
                        popActivity(activity);
                    }
                }
            }
        }
    }

    public void popAllActivity(String... clsStr) {
        int count = 0;
        if (!activityStack.isEmpty()) {
            int stockLen = activityStack.size() - 1;
            for (int i = stockLen; i > 0; i--) {
                Activity activity = activityStack.get(i);
                if (activity == null) {
                    break;
                }
                int len = clsStr.length;
                for (String aClsStr : clsStr) {
                    if (activity.getClass().getSimpleName().equals(aClsStr)) {
                        popActivity(activity);
                        count++;
                    }
                }
                if (count == len) {
                    break;
                }
            }
        }
    }

    public void popAllActivity() {
        popAllActivityExceptOne(null);
    }

    public void popAllActivity(Class<?> cls) {
        popAllActivityExceptOne(cls);
    }
}