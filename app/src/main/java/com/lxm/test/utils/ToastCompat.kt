package com.lxm.test.utils

import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes
import com.lxm.test.MyApplication.Companion.application
import com.lxm.test.utils.DisplayManager.dp2px

/**
 * @author : lxm
 * @package_name ：com.jganzhiyun.controlbox.uitls
 * @date : 2021/3/18 16:13
 * @description ：吐司工具
 */
object ToastCompat {

    private var toast: Toast? = null

    @JvmOverloads
    fun show(content: String, duration: Int = Toast.LENGTH_SHORT): Toast? {
        toast?.cancel()
        toast = Toast.makeText(application, content, duration)
        dp2px(96f)?.let {
            toast?.setGravity(Gravity.CENTER, 0, it)
        }
        toast?.show()
        return toast
    }

    fun show(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT): Toast? {
        toast?.cancel()
        toast = Toast.makeText(application, application.resources.getText(resId), duration)
        dp2px(96f)?.let {
            toast?.setGravity(Gravity.CENTER, 0, it)
        }
        toast?.show()
        return toast
    }

}