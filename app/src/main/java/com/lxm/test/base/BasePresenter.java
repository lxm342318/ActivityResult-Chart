package com.lxm.test.base;

import androidx.annotation.NonNull;

/**
 * @Copyright (C), 2020
 * @Author: ym
 * @Date:
 * @Description:
 *@param<V>
 */

public abstract class BasePresenter<V> {

    protected V view;

    public BasePresenter(@NonNull V view) {
        this.view = view;
    }
}
