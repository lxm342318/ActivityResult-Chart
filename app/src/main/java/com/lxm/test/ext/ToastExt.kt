package com.lxm.test.ext

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT): Toast = Toast
    .makeText(this, message, duration)
    .apply {
        show()
    }

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(resId), duration)
}

fun Context.longToast(message: String) {
    toast(message, Toast.LENGTH_LONG)
}

fun Context.longToast(@StringRes resId: Int) {
    toast(resId, Toast.LENGTH_LONG)
}

fun Any.toast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    context.toast(message, duration)
}

fun Any.toast(context: Context, @StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    context.toast(resId, duration)
}

fun Any.longToast(context: Context, message: String) {
    context.longToast(message)
}

fun Any.longToast(context: Context, @StringRes resId: Int) {
    context.longToast(resId)
}

fun FragmentActivity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    applicationContext.toast(message, duration)
}

fun FragmentActivity.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    applicationContext.toast(getString(resId), duration)
}

fun FragmentActivity.longToast(message: String) {
    applicationContext.longToast(message)
}

fun FragmentActivity.longToast(@StringRes resId: Int) {
    this.applicationContext.longToast(resId)
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireActivity().toast(message, duration)
}

fun Fragment.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    requireActivity().toast(getString(resId), duration)
}

fun Fragment.longToast(message: String) {
    requireActivity().longToast(message)
}

fun Fragment.longToast(@StringRes resId: Int) {
    requireActivity().longToast(resId)
}