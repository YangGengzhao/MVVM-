package com.example.baselibrary.utils;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.baselibrary.base.BaseApplication.baseContext;

public class ToastUtil {
    private static Toast toast;


    /**
     * 显示 Toast
     *
     * @param message 提示信息
     */
    public static void show(String message) {
        show(message, Toast.LENGTH_SHORT);
    }

    public static void show(int messageId) {
        show(baseContext.getString(messageId), Toast.LENGTH_SHORT);
    }

    /**
     * 显示 Toast
     *
     * @param message  提示信息
     * @param duration 显示时间长短
     */
    public static void show(String message, int duration) {
//        try {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(baseContext.getApplicationContext(), message, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
