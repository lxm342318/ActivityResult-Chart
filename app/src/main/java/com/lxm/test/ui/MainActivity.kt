package com.lxm.test.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import com.lxm.test.base.BaseVmActivity
import com.lxm.test.databinding.ActivityMainBinding
import com.lxm.test.ext.viewBinding
import com.lxm.test.vm.MainViewModel


class MainActivity : BaseVmActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onBackground(activity: Activity?) {

    }

    override fun onForeground(activity: Activity?) {

    }



}