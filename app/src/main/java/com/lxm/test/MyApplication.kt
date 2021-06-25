package com.lxm.test

import android.app.Application
import android.content.Context
import com.lxm.test.utils.DisplayManager

/**
 * @author : lxm
 * @package_name ：com.lxm.test
 * @date : 2021/5/28 18:17
 * @description ：
 */
class MyApplication : Application() {

    companion object{

        lateinit var context: Context
        lateinit var application: Application

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        application = this@MyApplication
        MyAlcImpl.getInstance().bindApplication(this)
        DisplayManager.init(this)
    }
}