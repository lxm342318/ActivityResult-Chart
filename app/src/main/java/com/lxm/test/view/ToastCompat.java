package com.lxm.test.view;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.lxm.test.callback.IToast;
import com.lxm.test.utils.DensityUtils;
import com.lxm.test.utils.JudgmentSystemUtil;

public class ToastCompat implements IToast {

    private IToast mIToast;

    public ToastCompat(Context context) {
        this(context, null, -1);
    }

    ToastCompat(Context context, String text, int duration) {
        if (JudgmentSystemUtil.isMIUI()) {
            mIToast = new MIUIToast(context).setText(text).setDuration(duration)
                    .setGravity(Gravity.BOTTOM, 0, DensityUtils.dip2px(context, 64));
        } else {
            mIToast = new SystemToast(context).setText(text).setDuration(duration);
        }
    }

    public static IToast makeText(Context context, String text, int duration) {
        return new ToastCompat(context, text, duration);
    }

    @Override
    public IToast setGravity(int gravity, int xOffset, int yOffset) {
        return mIToast.setGravity(gravity, xOffset, yOffset);
    }

    @Override
    public IToast setDuration(long durationMillis) {
        return mIToast.setDuration(durationMillis);
    }

    /**
     * 不能和{@link #setText(String)}一起使用，要么{@link #setView(View)} 要么{@link #setView(View)}
     *
     * @param view
     */
    @Override
    public IToast setView(View view) {
        return mIToast.setView(view);
    }

    @Override
    public IToast setMargin(float horizontalMargin, float verticalMargin) {
        return mIToast.setMargin(horizontalMargin, verticalMargin);
    }

    /**
     * 不能和{@link #setView(View)}一起使用，要么{@link #setView(View)} 要么{@link #setView(View)}
     *
     * @param text
     */
    @Override
    public IToast setText(String text) {
        return mIToast.setText(text);
    }

    @Override
    public void show() {
        mIToast.show();
    }

    @Override
    public void cancel() {
        mIToast.cancel();
    }

}
