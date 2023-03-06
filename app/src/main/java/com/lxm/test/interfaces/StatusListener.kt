package com.lxm.test.interfaces

import android.app.Activity

interface StatusListener {

    fun onForeground(activity: Activity?)

    fun onBackground(activity: Activity?)

}