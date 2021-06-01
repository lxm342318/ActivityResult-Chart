package com.lxm.test

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.io.Serializable
import com.lxm.test.utils.ActivityManager
import com.lxm.test.utils.LogCompat.logI

/**
 * @author : lxm
 * @package_name ：com.jganzhiyun.controlbox
 * @date : 2021年3月29日
 * @description ：activity生命周期监听
 */
class MyAlcImpl private constructor() : Application.ActivityLifecycleCallbacks, Serializable {

    private lateinit var mApplication: Application
    private var resumeNumbers: Int = 0

    companion object {
        @JvmStatic
        fun getInstance(): MyAlcImpl {
            return SingletonHolder.instance
        }
    }

    private object SingletonHolder {
        val instance: MyAlcImpl = MyAlcImpl()
    }

    private fun readResolve(): Any {//防止单例对象在反序列化时重新生成对象
        return SingletonHolder.instance
    }

    fun getApplication(): Application {
        return mApplication
    }

    fun bindApplication(application: Application) {
        this.mApplication = application
        application.registerActivityLifecycleCallbacks(this)
    }


    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        (activity.javaClass.simpleName).logI()
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
        resumeNumbers ++
        ActivityManager.pushActivity(activity)
        ActivityManager.setCurrentActivity(activity)
        (activity.javaClass.simpleName).logI()
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        resumeNumbers --
        ActivityManager.popActivity(activity)
        (activity.javaClass.simpleName).logI()
    }

}