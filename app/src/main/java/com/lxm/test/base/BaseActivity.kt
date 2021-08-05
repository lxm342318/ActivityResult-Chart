package com.lxm.test.base;

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.lxm.test.R
import com.lxm.test.utils.ConnectionLiveData
import java.lang.Exception
import kotlin.system.exitProcess

/**
  * @Copyright (C), 2020
  * @Author: ym
  * @Date:
  * @Description:
  */
abstract class BaseActivity : AppCompatActivity() , DialogInterface.OnKeyListener {

    private var unBinder : Unbinder ? = null
    private var  exitTime : Long = 0
    protected val  page : Int = 1
    protected val limit : Int = 20
    private var progressDialog : ProgressDialog ? = null

    private val mHandler = object : Handler(Looper.getMainLooper()){

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
        }
    }

    protected fun  getHandler() : Handler{
        return mHandler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //Activity全屏显示，且状态栏被隐藏覆盖掉
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        //设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //SDK版本21（5.0）之后 防止系统状态栏看不见和背景冲突
        window.statusBarColor = Color.TRANSPARENT
        setContentView(getLayoutResource())
        unBinder = ButterKnife.bind(this)
        initView(savedInstanceState)
    }


    /**
     * 加载布局
     *
     * @return int 布局文件id
     */
    abstract fun  getLayoutResource() : Int

    /**
     * 初始化view
     *
     * @param savedInstanceState activity实例状态
     */
    open fun initView(savedInstanceState: Bundle?) {
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    open  fun isRegisterEventBus() : Boolean = false



    open fun openActivity(cls: Class<*>) {
        openActivity(cls, null)
    }

    open fun openActivity(cls: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, cls)
        bundle?.let {
            intent.putExtras(it)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if(packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)!=null){
            try {
                startActivity(intent)
            }catch (e :Exception){
                e.printStackTrace()
            }
        }

    }

    open fun openActivityForResult( cls : Class<*>,  requestCode:Int) {
        openActivityForResult(cls, null, requestCode);
    }

    open  fun openActivityForResult(cls : Class<*>, bundle : Bundle?, requestCode:Int) {
        val  intent = Intent(this, cls)
        bundle?.let {
            intent.putExtras(it)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if(packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)!=null){
            try {
                startActivityForResult(intent, requestCode)
            }catch (e :Exception){
                e.printStackTrace()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun onDestroy() {
        super.onDestroy()
        unBinder?.unbind()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    protected fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog?.setMessage(getString(R.string.str_please_wait))
            progressDialog?.setOnKeyListener(this)
        }
        if (!isDestroyed)
            progressDialog?.show()
    }

    protected fun showProgressDialog( cancelable : Boolean) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this);
            progressDialog?.setMessage(getString(R.string.str_please_wait))
            progressDialog?.setOnKeyListener(this)
        }
        progressDialog?.setCancelable(cancelable)
        if (!isDestroyed)
            progressDialog?.show()
    }

    protected fun showProgressDialog( msg : Int) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog?.setOnKeyListener(this)
        }
        progressDialog?.setMessage(getString(msg))
        progressDialog?.setCancelable(false)
        if (!isDestroyed)
            progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            if (!isDestroyed)
                progressDialog?.dismiss()
        }
    }

    /**
     * progressDialog 系统返回按键事件
     */
    private fun onProDialogBack() {
        dismissProgressDialog()
    }



    /**
     * 菜单点击事件
     *
     * @param position item position
     */
    protected fun onMenuItemClick( position : Int) {
    }


    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //progressDialog 系统返回按键事件
            onProDialogBack()
        }
        return false
    }

    protected fun exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            //(getString(R.string.exit_app))
            exitTime = System.currentTimeMillis()
        } else {
            exitProcess(0)
        }
    }

}

