package com.lxm.test.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.lxm.test.R;
import com.lxm.test.T;
import com.lxm.test.utils.ActivityManager;
import org.jetbrains.annotations.NotNull;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
  * @Copyright (C), 2020
  * @Author: ym
  * @Date:
  * @Description:
  */
public abstract class BaseActivity extends AppCompatActivity implements DialogInterface.OnKeyListener {

    Unbinder unbinder;
    private long exitTime = 0;
    protected int page = 1, limit = 20;
    protected ProgressDialog progressDialog;
    protected ActivityManager activityManager;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(@NotNull Message msg) {

        }
    };

    protected Handler getHandler() {
        return mHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //Activity全屏显示，且状态栏被隐藏覆盖掉
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | 0x00002000);
        //设置状态栏颜色
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //SDK版本21（5.0）之后 防止系统状态栏看不见和背景冲突
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(getLayoutResource());
        unbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
        activityManager = ActivityManager.getInstance();
        activityManager.pushActivity(this);
    }

    /**
     * 加载布局
     *
     * @return int 布局文件id
     */
    public abstract int getLayoutResource();

    /**
     * 初始化view
     *
     * @param savedInstanceState activity实例状态
     */
    protected void initView(Bundle savedInstanceState) {
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    public void openActivity(Class<?> cls) {
        openActivity(cls, null);
    }

    public void openActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void openActivityForResult(Class<?> cls, int requestCode) {
        openActivityForResult(cls, null, requestCode);
    }

    public void openActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        activityManager.popActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.str_please_wait));
            progressDialog.setOnKeyListener(this);
        }
        if (!isDestroyed()) {
            progressDialog.show();
        }
    }

    protected void showProgressDialog(boolean cancelable) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.str_please_wait));
            progressDialog.setOnKeyListener(this);
        }
        progressDialog.setCancelable(cancelable);
        if (!isDestroyed()) {
            progressDialog.show();
        }
    }

    protected void showProgressDialog(int msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setOnKeyListener(this);
        }
        progressDialog.setMessage(getString(msg));
        progressDialog.setCancelable(false);
        if (!isDestroyed()) {
            progressDialog.show();
        }
    }

    protected void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            if (!isDestroyed()) {
                progressDialog.dismiss();
            }
        }
    }

    /**
     * progressDialog 系统返回按键事件
     */
    protected void onProDialogBack() {
        dismissProgressDialog();
    }



    /**
     * 菜单点击事件
     *
     * @param position item position
     */
    protected void onMenuItemClick(int position) {
    }

    @Override
    public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //progressDialog 系统返回按键事件
            onProDialogBack();
        }
        return false;
    }

    protected void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            T.l(getString(R.string.exit_app));
            exitTime = System.currentTimeMillis();
        } else {
            activityManager.popAllActivity();
            System.exit(0);
        }
    }

}

