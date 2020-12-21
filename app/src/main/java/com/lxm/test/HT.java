package com.lxm.test;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class HT implements Application.ActivityLifecycleCallbacks {
    private static volatile HT instance;
    private Application application;
    private int resumeActivitiesNum = 0;

    private HT() {
    }

    public static HT get() {
        if (instance == null) {
            synchronized (HT.class) {
                if (instance == null) {
                    instance = new HT();
                }
            }
        }
        return instance;
    }

    public Application getApplication() {
        return application;
    }

    void bindApplication(Application application) {
        this.application = application;
        application.registerActivityLifecycleCallbacks(this);
    }

    public boolean isAppForeground() {
        return resumeActivitiesNum > 0;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        L.i(activity.getClass().getSimpleName() + " onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        resumeActivitiesNum++;
        L.i(activity.getClass().getSimpleName() + " onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        resumeActivitiesNum--;
        L.i(activity.getClass().getSimpleName() + " onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        L.w(activity.getClass().getSimpleName() + " onActivityDestroyed");
    }
}