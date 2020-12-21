package com.lxm.test;

import com.orhanobut.logger.Logger;

/**
  * @Copyright (C), 2020
  * @Author: ym
  * @Date:   11.10
  * @Description: 日志工具类
  */
public class L {

    public static void d(String log) {
        if (BuildConfig.DEBUG)
            Logger.d(log);
    }

    public static void e(String log) {
        if (BuildConfig.DEBUG)
            Logger.e(log);
    }


    public static void i(String log) {
        if (BuildConfig.DEBUG)
            Logger.i(log);
    }

    public static void w(String log) {
        if (BuildConfig.DEBUG)
            Logger.w(log);
    }

    public static void json(String json) {
        if (BuildConfig.DEBUG)
            Logger.json(json);
    }
}
