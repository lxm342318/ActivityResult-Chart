package com.lxm.test

import android.app.Application
import com.lxm.test.utils.DisplayManager

/**
 * @author : lxm
 * @package_name ：com.lxm.test
 * @date : 2021/5/28 18:17
 * @description ：
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MyAlcImpl.bindApplication(this)
        DisplayManager.init(this)
    }
}