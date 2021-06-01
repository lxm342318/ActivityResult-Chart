package com.lxm.test.utils


import android.app.Activity
import java.lang.ref.WeakReference
import java.util.*

/**
 * @author : lxm
 * @package_name ：com.jganzhiyun.controlbox.uitls
 * @date : 2021/3/18 16:25
 * @description ：activity管理
 */
object ActivityManager {

    private var activityStack: Stack<Activity>? = null
    var currentActivity: WeakReference<Activity>? = null


    @Synchronized
    fun getCurrentActivity(): Activity? {
        var activity: Activity? = null
        if (currentActivity != null) {
            activity = currentActivity?.get()
        }
        return activity
    }

    @Synchronized
    fun setCurrentActivity(activity: Activity) {
        currentActivity = WeakReference(activity)
    }

    @Synchronized
    fun popActivity(activity: Activity) {
        activity.finish()
        activityStack?.remove(activity)
    }

    @Synchronized
    fun pushActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack?.add(activity)
    }

    @Synchronized
    fun takeActivity(clazz: Class<*>) : Activity? {
        activityStack?.let {
            for ((index,value) in it.withIndex()){
                  if(clazz == value::class)
                      return value
            }
        }
        return null
    }

    private fun popAllActivityExceptOne(clazz: Class<Any>?) {
        activityStack?.isNotEmpty()?.let {
            if (it) {
                activityStack?.size?.run {
                    val length = this
                    for (i in length - 1 downTo 0) {
                        activityStack?.run {
                            val activity: Activity = this[i]
                            if (activity.javaClass != clazz)
                                popActivity(activity)
                        }

                    }
                }

            }
        }
    }

    fun popAllActivity(vararg clsStr: String) {
        var count = 0
        activityStack?.isNotEmpty()?.let {
            if (it) {
                activityStack?.size?.run {
                    val stockLen: Int = this - 1
                    for (i in stockLen downTo 1) {
                        val activity: Activity =
                            activityStack!![i] ?: break
                        val len = clsStr.size
                        for (aClsStr in clsStr) {
                            if (activity.javaClass.simpleName == aClsStr) {
                                popActivity(activity)
                                count++
                            }
                        }
                        if (count == len)
                            break
                    }
                }
            }
        }
    }

    fun popAllActivity() {
        popAllActivityExceptOne(null)
    }

    fun getSize(): Int? {
        return activityStack?.size
    }

}