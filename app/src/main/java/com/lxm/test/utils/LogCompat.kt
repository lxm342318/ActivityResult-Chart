package com.lxm.test.utils

import com.lxm.test.NULL_CHARACTER
import com.orhanobut.logger.Logger


/**
 * @author : lxm
 * @package_name ：com.jganzhiyun.controlbox.uitls
 * @date : 2021/3/18 16:13
 * @description ：日志打印
 */
object LogCompat {

    private const val NONE = 6
    private const val ERROR = 5
    private const val WARN = 4
    private const val INFO = 3
    private const val DEBUG = 2
    private const val VERBOSE = 1

    private var printLevel = VERBOSE

    fun Any?.logV() {
        if (printLevel <= VERBOSE) {
            //if (BuildConfig.ISDEBUG)
            Logger.v(logTag, toString())
        }
    }

    fun Any?.logD() {
        if (printLevel <= DEBUG) {
            //if (BuildConfig.ISDEBUG)
            Logger.d(logTag, toString())
        }
    }

    fun Any?.logI() {
        if (printLevel <= INFO) {
            //if (BuildConfig.ISDEBUG)
            Logger.i("${toString()}  $logTag", toString())
        }
    }

    fun Any?.logW() {
        if (printLevel <= WARN) {
            //if (BuildConfig.ISDEBUG)
            Logger.w(logTag, toString())
        }
    }

    fun Any?.logW(t: Throwable) {
        if (printLevel <= WARN) {
            Logger.w(logTag, toString(), t)
        }
    }

    fun Any?.logWRuntimeException(msg: Any = NULL_CHARACTER) {
        if (printLevel <= WARN) {
            //if (BuildConfig.ISDEBUG)
            Logger.w(logTag, msg.toString(), RuntimeException(msg.toString()))
        }
    }

    fun Any?.logE() {
        if (printLevel <= ERROR) {
            //if (BuildConfig.ISDEBUG)
            Logger.e("${toString()}  $logTag", toString())
        }
    }

    fun Any?.logE(t: Throwable) {
        if (printLevel <= ERROR) {
            //if (BuildConfig.ISDEBUG)
            Logger.e(logTag, toString(), t)
        }
    }

    fun Any?.logERuntimeException(msg: Any = NULL_CHARACTER) {
        if (printLevel <= ERROR) {
            //if (BuildConfig.ISDEBUG)
            Logger.e(logTag, msg.toString(), RuntimeException(msg.toString()))
        }
    }

    private val logTag: String
        get() {
            val element = Thread.currentThread().stackTrace[4]
            return "(${element.fileName}:${element.lineNumber}) ${element.methodName}"
        }


}