package com.lxm.test

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

/**
 * @author : lxm
 * @package_name ：com.lxm.test
 * @date : 2021/5/28 18:13
 * @description ：
 */

const val NULL_CHARACTER = ""

/**
 * @param radius  模糊度（0--25）
 * @param context 上下文
 * @return 模糊后的bitmap
 */
fun Bitmap.toBlur(radius : Float, context: Context) : Bitmap {

    var temp  = radius
    val outBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
    val renderScript = RenderScript.create(context)
    val scriptIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
    val allIn = Allocation.createFromBitmap(renderScript, this)
    val allOut = Allocation.createFromBitmap(renderScript, outBitmap)
    if(temp > 25)
        temp = 25F
    else if(temp <= 0)
        temp = 1F
    scriptIntrinsic.setRadius(temp)
    scriptIntrinsic.setInput(allIn)
    scriptIntrinsic.forEach(allOut)
    allOut.copyTo(outBitmap)
    scriptIntrinsic.destroy()
    if(!this.isRecycled)
        this.recycle()
    return outBitmap
}

/**
 * @param tintColor
 * @param context
 * @return 针对图标颜色换色
 */
@SuppressLint("ResourceType")
fun ImageView.tint(@ColorInt tintColor : Int, context: Context){
    // 通过mutate()复制加载出来的对象
    val drawable = this.drawable.mutate()
    val wrappedDrawable = DrawableCompat.wrap(drawable)
    DrawableCompat.setTintList(
            wrappedDrawable,
            ColorStateList.valueOf(ContextCompat.
            getColor(context, tintColor))
    )
    this.setImageDrawable(wrappedDrawable)
}

/**
 * 字符串非空判断
 * @return  Boolean
 */
fun CharSequence.isNullEmpty(): Boolean {
    return this.isEmpty() ||  this.toString().compareTo("null",true) == 0
}