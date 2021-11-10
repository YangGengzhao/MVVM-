package com.example.baselibrary.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;


import com.example.baselibrary.log.MLog;
import com.example.baselibrary.utils.ScreenUtils;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

public abstract class BaseDialog<VB extends ViewBinding> extends Dialog {
    public final String TAG = getClass().getSimpleName();
    public VB banding;
    protected LoadingDialog loadingDialog;

    public BaseDialog(@NonNull @NotNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull @NotNull Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        hideBottomUIMenu();
        super.onCreate(savedInstanceState);
        MLog.w(TAG, TAG + "：  onCreate.........");
        loadingDialog = new LoadingDialog(getContext());

    }

    public abstract VB createViewBanding();//设置ViewBanding

    public abstract void setStyle();//设置风格

    public abstract void initData();//初始化数据

    public abstract void initListener();//点击事件

    @Override
    public void show() {
        if (isShowing()) return;
        super.show();
        initData();
        initListener();
    }

    @Override
    public void dismiss() {
        if (!isShowing()) return;
        super.dismiss();
//        showBottomUIMenu();
    }


    //关闭外部点击事件
    public void CanceledOnTouchOutside() {
        setCanceledOnTouchOutside(false);
    }

    public void setStyleBottom() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ScreenUtils.getScreenWidth(getContext());
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(params);
    }

    public void setStyleCenter() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ScreenUtils.getScreenWidth(getContext());
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
    }

    @Override
    protected void onStart() {
        super.onStart();
        banding = createViewBanding();
        setContentView(banding.getRoot());
        setStyle();
        MLog.w(TAG, TAG + "：  onStart.........");

    }

    @Override
    protected void onStop() {
        super.onStop();
        MLog.w(TAG, TAG + "：  onStop.........");
    }

    /**
     * 隐藏虚拟按键：必须放到setContentView前面
     */
    public void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.INVISIBLE |
//                    View.SYSTEM_UI_FLAG_FULLSCREEN
//                    |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.INVISIBLE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public void showBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.VISIBLE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = this.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

}
