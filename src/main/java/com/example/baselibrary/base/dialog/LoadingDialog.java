package com.example.baselibrary.base.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.baselibrary.R;
import com.example.baselibrary.databinding.DialogLoadingBinding;
import com.example.baselibrary.utils.StringUtil;

import org.jetbrains.annotations.NotNull;


public class LoadingDialog extends BaseDialog<DialogLoadingBinding> {
    private String msg;

    public LoadingDialog(@NonNull @NotNull Context context) {
        super(context);
    }

    public LoadingDialog(@NonNull @NotNull Context context, int style) {
        super(context, style);
    }


    public void show(String msg) {
        this.msg = msg;
        show();
    }

    @Override
    public DialogLoadingBinding createViewBanding() {
        return DialogLoadingBinding.inflate(getLayoutInflater());
    }

    @Override
    public void setStyle() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setAttributes(lp);
        getWindow().setWindowAnimations(R.style.PopWindowAnimStyle);
        setCancelable(true);
        CanceledOnTouchOutside();
    }

    @Override
    public void initData() {
        if (!StringUtil.isEmpty(msg)) {
            banding.tipTextView.setText(msg + "");// 设置加载信息
        }
    }

    @Override
    public void initListener() {

    }

}