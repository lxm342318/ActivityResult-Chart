package com.lxm.test.ext

import android.app.Activity
import android.app.Dialog
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.view.postDelayed

/**
 *  配合 implementation 'androidx.core:core-ktx:1.3.2'
 *	View.isGone()
 *	View.isInvisible()
 *  View.isVisible()
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

val String.toEditable: Editable
    get() = Editable.Factory.getInstance().newEditable(this)

/**
 * Allows calls like
 *
 * `viewGroup.inflate(R.layout.foo)`
 */
fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layout, this, attachToRoot)
}

/**
 * Registers the [block] lambda as [View.OnClickListener] to this View.
 *
 * If this View is not clickable, it becomes clickable.
 */
inline fun View.click(crossinline block: (view: View?) -> Unit) = setOnClickListener { block(it) }

/**
 * Register the [block] lambda as [View.OnLongClickListener] to this View.
 * By default, [consume] is set to true because it's the most common use case, but you can set it
 * to false.
 * If you want to return a value dynamically, use [View.setOnLongClickListener] instead.
 *
 * If this view is not long clickable, it becomes long clickable.
 */
inline fun View.longClick(
    consume: Boolean = true,
    crossinline block: (view: View?) -> Unit
) = setOnLongClickListener { block(it); consume }

inline fun View.delayClick(crossinline block: (view: View?) -> Unit, delayInMillis: Long = 1000L) =
    setOnClickListener {
        isClickable = false
        block(it)
        postDelayed(delayInMillis) {
            isClickable = true
        }
    }


inline fun <reified T : View> View.find(@IdRes id: Int): T = findViewById(id)
inline fun <reified T : View> Activity.find(@IdRes id: Int): T = findViewById(id)
inline fun <reified T : View> Dialog.find(@IdRes id: Int): T = findViewById(id)
inline fun <reified T : View> View.findOptional(@IdRes id: Int): T? = findViewById(id) as? T
inline fun <reified T : View> Activity.findOptional(@IdRes id: Int): T? = findViewById(id) as? T
inline fun <reified T : View> Dialog.findOptional(@IdRes id: Int): T? = findViewById(id) as? T