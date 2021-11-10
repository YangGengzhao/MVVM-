package com.example.baselibrary.base.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.baselibrary.R;
import com.example.baselibrary.base.dialog.CommonDialog;
import com.example.baselibrary.base.dialog.LoadingDialog;
import com.example.baselibrary.eventBus.EventMessage;
import com.gyf.immersionbar.ImmersionBar;

import com.example.baselibrary.base.dialog.TipsDialog;
import com.example.baselibrary.log.MLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseFragment extends Fragment {
    private boolean mDo_TransStatusBar = true;
    private boolean mStatusBarFontDark = true;
    private String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.e(TAG, TAG + "：  onCreate.........");
        tipsDialog = new TipsDialog(getActivity());
        commonDialog = new CommonDialog(getActivity());
        loadingDialog = new LoadingDialog(getActivity(), R.style.MyDialogStyle);
        if (!mDo_TransStatusBar) return;
        ImmersionBar.with(this).transparentBar().statusBarDarkFont(mStatusBarFontDark).navigationBarColor(R.color.black).fullScreen(false).init();
    }

    public void setStatusBarDarkFont(boolean dark) {
        this.mStatusBarFontDark = dark;
    }

    public void seTransStatusBar(boolean transStatusBar) {
        this.mDo_TransStatusBar = transStatusBar;
    }

    public void changeVisible(boolean visible) {

    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private long lastClick = 0;

    public boolean fastClick() {
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return true;
        }
        lastClick = System.currentTimeMillis();
        return false;
    }

    protected TipsDialog tipsDialog;
    protected CommonDialog commonDialog;
    protected LoadingDialog loadingDialog;

    public void showLoadingDialog(String msg) {
        if (loadingDialog == null) return;
        if (loadingDialog.isShowing()) return;
        loadingDialog.show(msg);
    }

    public void dismissLoadingDialog() {
        if (loadingDialog == null) return;
        if (!loadingDialog.isShowing()) return;
        loadingDialog.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        MLog.w(TAG, TAG + "：  onStart.........");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventMessage(EventMessage message) {

    }

    @Override
    public void onResume() {
        super.onResume();
        MLog.w(TAG, TAG + "：  onResume.........");
    }

    @Override
    public void onStop() {
        super.onStop();
        MLog.w(TAG, TAG + "：  onStop.........");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        MLog.w(TAG, TAG + "：  onDestroy.........");
    }

}
