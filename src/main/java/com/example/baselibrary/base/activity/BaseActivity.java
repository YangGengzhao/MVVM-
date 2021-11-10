package com.example.baselibrary.base.activity;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.baselibrary.R;

import com.example.baselibrary.eventBus.EventMessage;
import com.example.baselibrary.log.MLog;
import com.example.baselibrary.utils.ResizeDensity;
import com.example.baselibrary.utils.StringUtil;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseActivity extends AppCompatActivity {
    public final String TAG = getClass().getSimpleName();
    private boolean isTransStatusBar = false;
    private boolean mStatusBarFontDark = true;


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.w(TAG, TAG + "：  onCreate.........");
        if (getClass() != null && !StringUtil.isEmpty(getClass().getSimpleName()) && !(getClass().getSimpleName().equals("LauncherActivity")))
            ResizeDensity.setCustomDensity(this, getApplication());//今日头条适配
        if (!isTransStatusBar) return;
        ImmersionBar.with(this).statusBarDarkFont(mStatusBarFontDark).navigationBarColor(R.color.black).fullScreen(false).init();
    }

    //设置状态栏字体颜色
    public void setStatusBarDarkFont(boolean dark) {
        this.mStatusBarFontDark = dark;
    }

    //沉浸式状态栏 transStatusBar为true时
    public void setTransStatusBar(boolean transStatusBar) {
        this.isTransStatusBar = transStatusBar;
    }

    //隐藏状态栏
    public void setHideStatusBar() {
        isTransStatusBar = false;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventMessage(EventMessage message) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        MLog.w(TAG, TAG + "：  onStart.........");
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        MLog.w(TAG, TAG + "：  onStop.........");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MLog.w(TAG, TAG + "：  onDestroy.........");
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MLog.w(TAG, TAG + "：  onPause.........");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MLog.w(TAG, TAG + "：  onResume.........");
    }
}
