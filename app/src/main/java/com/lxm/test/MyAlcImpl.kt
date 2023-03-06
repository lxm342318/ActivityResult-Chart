package com.lxm.test

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.lxm.test.interfaces.StatusListener
import java.io.Serializable
import com.lxm.test.utils.ActivityManager
import com.lxm.test.utils.LogCompat.logI
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

/**
 * @author : lxm
 * @package_name ：com.jganzhiyun.controlbox
 * @date : 2021年3月29日
 * @description ：activity生命周期监听
 */
class MyAlcImpl private constructor() : Application.ActivityLifecycleCallbacks, Serializable {

    private lateinit var mApplication: Application
    private val mStatusListener : MutableList<StatusListener> = CopyOnWriteArrayList()
    private val mActivityList = LinkedList<Activity>()
    private var mForegroundCount = 0
    private var mConfigCount = 0
    private var mIsBackground = false

    companion object {
        @JvmStatic
        fun getInstance(): MyAlcImpl {
            return SingletonHolder.INSTANCE
        }
    }

    private object SingletonHolder {
        val INSTANCE: MyAlcImpl = MyAlcImpl()
    }

    private fun readResolve(): Any {//防止单例对象在反序列化时重新生成对象
        return SingletonHolder.INSTANCE
    }

    fun getApplication(): Application {
        return mApplication
    }

    fun bindApplication(application: Application) {
        this.mApplication = application
        application.registerActivityLifecycleCallbacks(this)
    }

    fun addStatusListener(listener : StatusListener) {
        mStatusListener.add(listener)
    }

    fun removeStatusListener(listener : StatusListener){
        mStatusListener.remove(listener)
    }

    private fun postStatus(activity: Activity, isForeground: Boolean) {
        if (mStatusListener.isEmpty())
            return
        for (statusListener in mStatusListener) {
            if (isForeground)
                statusListener.onForeground(activity)
            else
                statusListener.onBackground(activity)
        }
    }

    private fun setTopActivity(activity: Activity) {
        if (mActivityList.contains(activity)) {
            if (mActivityList.first != activity) {
                mActivityList.remove(activity)
                mActivityList.addFirst(activity)
            }
        } else {
            mActivityList.addFirst(activity)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        ("${mActivityList.size} -- $mForegroundCount -- $mConfigCount").logI()
        if (mActivityList.size == 0)
            postStatus(activity = activity, isForeground = true)
        setTopActivity(activity = activity)
    }

    override fun onActivityStarted(activity: Activity) {
        if (!mIsBackground)
            setTopActivity(activity)
        if (mConfigCount < 0)
            ++mConfigCount
        else
            ++mForegroundCount
    }

    override fun onActivityResumed(activity: Activity) {
        setTopActivity(activity = activity)
        if (mIsBackground) {
            mIsBackground = false
            postStatus(activity = activity, isForeground = true)
        }
        ("${mActivityList.size} -- $mForegroundCount -- $mConfigCount").logI()
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {
        if (activity.isChangingConfigurations) {
            --mConfigCount
        } else {
            --mForegroundCount
            if (mForegroundCount <= 0) {
                mIsBackground = true
                postStatus(activity = activity, isForeground = false)
            }
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        mActivityList.remove(activity)
        ("${mActivityList.size} -- $mForegroundCount -- $mConfigCount").logI()
    }

}