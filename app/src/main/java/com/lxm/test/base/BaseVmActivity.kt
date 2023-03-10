package com.lxm.test.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lxm.test.MyAlcImpl
import com.lxm.test.interfaces.StatusListener
import com.lxm.test.utils.LogCompat.logI

open class BaseVmActivity : AppCompatActivity(), StatusListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyAlcImpl.addStatusChangedListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        MyAlcImpl.removeStatusChangedListener(this)
    }

    override fun onForeground(activity: Activity?) {
         activity?.javaClass?.simpleName.logI()
    }

    override fun onBackground(activity: Activity?) {
         activity?.javaClass?.simpleName.logI()
    }

}