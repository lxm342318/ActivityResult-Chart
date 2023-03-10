package com.lxm.test.utils


import android.app.Activity
import java.lang.ref.WeakReference
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

/**
 * @author : lxm
 * @package_name ：com.jganzhiyun.controlbox.uitls
 * @date : 2021/3/18 16:25
 * @description ：activity管理
 */
object ActivityManager {

    private val activityStack by lazy{ CopyOnWriteArrayList<WeakReference<Activity>>() }
    private var currentActivity: WeakReference<Activity>? = null


    @Synchronized
    fun getCurrentActivity(): Activity? {
        return currentActivity?.get()
    }

    @Synchronized
    fun setCurrentActivity(activity: WeakReference<Activity>) {
        currentActivity = activity
    }

    @Synchronized
    fun popActivity(activity: WeakReference<Activity>) {
        activity.get()?.finish()
        activityStack.remove(activity)
    }

    @Synchronized
    fun pushActivity(activity: Activity) {
        activityStack.add(WeakReference(activity))
    }

    @Synchronized
    fun takeActivity(clazz: Class<*>):WeakReference<Activity>? {
        for (value in activityStack) {
            if (clazz == value::class.java)
                return value
        }
        return null
    }

    @Synchronized
    private fun popAllActivityExceptOne(clazz: Class<*>?) {
        for (value in activityStack) {
            if (value::class.java != clazz)
                popActivity(activity = value)
        }
    }

    fun popAllActivity(vararg clazz: Class<*>) {
        var count = 0
        for (i in activityStack.indices) {
            val activity: WeakReference<Activity> = activityStack[i]
            val len = clazz.size
            for (classStr in clazz) {
                if (activity::class.java == classStr) {
                    popActivity(activity = activity)
                    count++
                }
            }
            if (count == len)
                break
        }
    }

    fun popAllActivity() {
        popAllActivityExceptOne(clazz = null)
    }

    fun getSize(): Int {
        return activityStack.size
    }

}