package com.example.baselibrary.base.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import com.example.baselibrary.utils.ScreenUtils;

public class CommonDialog {
    Context context;

    public CommonDialog(Context context) {
        this.context = context;
    }


    public void showHintDialog(String content) {
        showDialog(null, content, null);
    }



    /**
     * @param title          标题
     * @param content        内容
     * @param cancelable     设置是否可以通过点击对话框外区域或者返回按键关闭对话框,true 可以 false 不可以
     * @param singleAffirm   是否只有确定按钮
     * @param affirmCallBack 确定回调
     */
    public void showDialog(String title, String content, boolean cancelable,
                           boolean singleAffirm, DialogInterface.OnClickListener cancelClallBack, DialogInterface.
                                   OnClickListener affirmCallBack) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//        dialog.setIcon(R.mipmap.ic_launcher_round);
        dialog.setTitle(title);
        dialog.setMessage(content);
        dialog.setCancelable(cancelable);    //设置是否可以通过点击对话框外区域或者返回按键关闭对话框,true 可以 false 不可以
        if (affirmCallBack != null) {
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    affirmCallBack.onClick(dialog, which);

                }
            });
        }
        if (!singleAffirm) {
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (cancelClallBack != null) {
                        cancelClallBack.onClick(dialog, which);
                    }
                }
            });
        }

        AlertDialog pd2 = dialog.show();
        WindowManager.LayoutParams params = pd2.getWindow().getAttributes();
        params.width = ScreenUtils.getScreenWidth(context) * 4 / 5;
        pd2.getWindow().setAttributes(params);
    }


    public void showDialog(String title, String content, DialogInterface.OnClickListener
            cancelClallBack, DialogInterface.OnClickListener affirmCallBack) {
        showDialog(title, content, false, false, cancelClallBack, affirmCallBack);
    }


    public void showDialog(String title, String content, DialogInterface.OnClickListener
            affirmCallBack) {
        showDialog(title, content, false, false, null, affirmCallBack);
    }

    ProgressDialog pd;

    public void showProgressDialog(String msg) {
        pd = new ProgressDialog(context);
        //设置标题
        //pd.setTitle("我是加载框");
        //设置提示信息
        pd.setMessage(msg);
        //设置ProgressDialog 是否可以按返回键取消；
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        //显示ProgressDialog
        pd.show();

        WindowManager.LayoutParams params = pd.getWindow().getAttributes();
        params.width = ScreenUtils.getScreenWidth(context) * 4 / 5;
        pd.getWindow().setAttributes(params);
    }

    public void dismissProgressDialog() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }
}
