package com.lxm.test.base;

import androidx.annotation.NonNull;

/**
 * @Copyright (C), 2020
 * @Author: ym
 * @Date:
 * @Description:
 *@param<V>
 */

abstract class BasePresenter<V>(view : V) {

    private var _view : V = view


}
