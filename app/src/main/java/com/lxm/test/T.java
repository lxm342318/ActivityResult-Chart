package com.lxm.test;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.MainThread;


/**
  * @Copyright (C), 2020
  * @Author: ym
  * @Date:   11.10
  * @Description: Toast工具类
  */
public class T {
    private static Toast toast;

    /**
     * Show long toast
     *
     * @param textId content res
     */
    @MainThread
    public static void l(final int textId) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(HT.get().getApplication(), null, Toast.LENGTH_LONG);
        toast.setText(textId);
        toast.show();
    }

    /**
     * Show long toast
     *
     * @param text content res
     */
    @MainThread
    public static void l(final String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(HT.get().getApplication(), null, Toast.LENGTH_LONG);
        toast.setText(text);
        toast.show();
    }

    /**
     * Show short toast
     *
     * @param textId text
     */
    @MainThread
    public static void s(final int textId) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(HT.get().getApplication(), "", Toast.LENGTH_SHORT);
        toast.setText(textId);
        toast.show();
    }

    /**
     * Show short toast
     *
     * @param text text
     */
    @MainThread
    public static void s(final String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(HT.get().getApplication(), "", Toast.LENGTH_SHORT);
        toast.setText(text);
        toast.show();
    }

    /**
     * Show long toast, center
     *
     * @param textId content res
     */
    @MainThread
    public static void lc(final int textId) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(HT.get().getApplication(), null, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(textId);
        toast.show();
    }

    /**
     * Show long toast, center
     *
     * @param text content res
     */
    @MainThread
    public static void lc(final String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(HT.get().getApplication(), null, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(text);
        toast.show();
    }

    /**
     * Show short toast, center
     *
     * @param textId text
     */
    @MainThread
    public static void sc(final int textId) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(HT.get().getApplication(), null, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(textId);
        toast.show();
    }

    /**
     * Show short toast, center
     *
     * @param text text
     */
    @MainThread
    public static void sc(final String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(HT.get().getApplication(), null, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(text);
        toast.show();
    }

    /**
     * Show custom view toast, short
     *
     * @param view custom view
     */
    @MainThread
    public static void sv(View view) {
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(HT.get().getApplication());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    /**
     * Show custom view toast, long
     *
     * @param view custom view
     */
    @MainThread
    public static void lv(View view) {
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(HT.get().getApplication());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    /**
     * Show custom view toast, short, center
     *
     * @param view custom view
     */
    @MainThread
    public static void svc(View view) {
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(HT.get().getApplication());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * Show custom view toast, long, center
     *
     * @param view custom view
     */
    @MainThread
    public static void lvc(View view) {
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(HT.get().getApplication());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @MainThread
    public static void success(View view) {
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(HT.get().getApplication());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}