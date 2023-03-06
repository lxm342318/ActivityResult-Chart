package com.lxm.test.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var _live = MutableLiveData<String>()
    val live : LiveData<String> = _live

}