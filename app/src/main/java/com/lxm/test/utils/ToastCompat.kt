package com.lxm.test.utils

import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes
import com.lxm.test.MyAlcImpl
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
        toast = Toast.makeText(MyAlcImpl.getApplication(), content, duration)
        toast?.setGravity(Gravity.CENTER, 0, dp2px(96f))
        toast?.show()
        return toast
    }

    fun show(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT): Toast? {
        toast?.cancel()
        toast = Toast.makeText(MyAlcImpl.getApplication(), MyAlcImpl.getApplication().resources.getText(resId), duration)
        toast?.setGravity(Gravity.CENTER, 0, dp2px(96f))
        toast?.show()
        return toast
    }

}