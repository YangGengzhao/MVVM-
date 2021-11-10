package com.example.baselibrary.base.popupWindow;


import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.viewbinding.ViewBinding;

public abstract class BasePopupWindow<VB extends ViewBinding> implements PopupWindow.OnDismissListener {
    public final String TAG = getClass().getSimpleName();
    private PopupWindow mPopWindow;
    public VB banding;
    private Context context;

    public BasePopupWindow(Context context, int width, int height) {
        banding = createViewBanding(context);
        this.context = context;
        mPopWindow = new PopupWindow(banding.getRoot(), width, height);
        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setOnDismissListener(this);
        setStyle();
        initData(context);
    }

    public abstract VB createViewBanding(Context context);//设置ViewBanding

    public abstract void setStyle();//设置风格属性

    public abstract void initData(Context context);//初始化数据

    //显示页面底部
    public void showUp(View view) {
        if (mPopWindow == null) return;
        if (mPopWindow.isShowing()) return;
        mPopWindow.setBackgroundDrawable(null);
        mPopWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

    }

    public void showUp(View view, int x, int y) {
        if (mPopWindow == null) return;
        if (mPopWindow.isShowing()) return;
        mPopWindow.setBackgroundDrawable(null);
        mPopWindow.showAtLocation(view, Gravity.BOTTOM, x, y);
    }

    public void showTop(View view) {
        if (mPopWindow == null) return;
        if (mPopWindow.isShowing()) return;
        mPopWindow.setBackgroundDrawable(null);
        mPopWindow.showAtLocation(view, Gravity.TOP, 0, 0);

    }

    //显示页面中间
    public void showCenter(View view) {
        if (mPopWindow == null) return;
        if (mPopWindow.isShowing()) return;
        mPopWindow.setBackgroundDrawable(null);
        mPopWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }

    //显示控件底部
    public void showAsDropDown(View view) {
        if (mPopWindow == null) return;
        if (mPopWindow.isShowing()) return;
        mPopWindow.showAsDropDown(view);

    }

    public void showAsDropDown(View view, int x, int y) {
        if (mPopWindow == null) return;
        if (mPopWindow.isShowing()) return;
        mPopWindow.showAsDropDown(view, x, y);


    }

    public void showAtLocation(View view, int x, int y) {
        if (mPopWindow == null) return;
        if (mPopWindow.isShowing()) return;
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY, x, y);
    }

    public void dismiss() {
        if (mPopWindow == null) return;
        if (!mPopWindow.isShowing()) return;
        mPopWindow.dismiss();
    }

    public PopupWindow getmPopWindow() {
        return mPopWindow;
    }


    @Override
    public void onDismiss() {
//        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
//        lp.alpha = 1f;
//        ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        ((Activity) context).getWindow().setAttributes(lp);
    }
}
