package com.example.baselibrary.base.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.baselibrary.databinding.DialogTipsBinding;
import com.example.baselibrary.utils.ScreenUtils;
import com.example.baselibrary.utils.StringUtil;

import org.jetbrains.annotations.NotNull;


public class TipsDialog extends BaseDialog<DialogTipsBinding> {
    private Context context;
    private String title;
    private String content;
    private boolean cancelable;
    private boolean singleAffirm;
    private OnClickListener.affirmCallBack affirmCallBack;
    private OnClickListener.cancelCallBack cancelCallBack;

    public TipsDialog(@NonNull @NotNull Context context) {
        super(context);
        this.context = context;
    }

    public interface OnClickListener {
        interface cancelCallBack {
            void cancel();
        }

        interface affirmCallBack {
            void affirm();
        }
    }

    @Override
    public DialogTipsBinding createViewBanding() {
        return DialogTipsBinding.inflate(getLayoutInflater());//绑定viewBinding;
    }

    @Override
    public void setStyle() {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ScreenUtils.getScreenWidth(getContext()) * 4 / 5;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
    }

    @Override
    public void initData() {
        if (!cancelable) {
            CanceledOnTouchOutside();//关闭外部点击关闭弹窗
        }
        if (banding != null) {
            FrameLayout.LayoutParams ll_contentLayoutParams = (FrameLayout.LayoutParams) banding.llContent.getLayoutParams();
            ll_contentLayoutParams.topMargin = ScreenUtils.getStatusBarHeight(context);
            banding.llContent.setLayoutParams(ll_contentLayoutParams);
            if (!StringUtil.isEmpty(title)) {
                banding.tvTitle.setVisibility(View.VISIBLE);
                banding.tvTitle.setText(title + "");
            } else {
                banding.tvTitle.setVisibility(View.GONE);
                banding.tvTitle.setText("");
            }
            if (!StringUtil.isEmpty(content)) {
                banding.tvContent.setVisibility(View.VISIBLE);
                banding.tvContent.setText(content + "");
            } else {
                banding.tvContent.setVisibility(View.GONE);
                banding.tvContent.setText("");
            }

            if (singleAffirm) {
                banding.tvCancel.setVisibility(View.GONE);
            } else {
                banding.tvCancel.setVisibility(View.VISIBLE);
            }

        }

    }

    @Override
    public void initListener() {
        banding.tvConfirm.setOnClickListener(view -> {
            if (affirmCallBack != null)
                affirmCallBack.affirm();
            dismiss();
        });
        banding.tvCancel.setOnClickListener(view -> {
            if (cancelCallBack != null)
                cancelCallBack.cancel();
            dismiss();
        });
    }


    /**
     * @param title          标题
     * @param content        内容
     * @param cancelable     设置是否可以通过点击对话框外区域或者返回按键关闭对话框,true 可以 false 不可以
     * @param singleAffirm   是否只有确定按钮
     * @param affirmCallBack 确定回调
     * @param cancelCallBack 取消回调
     */
    public void showDialog(String title, String content, boolean cancelable, boolean singleAffirm, OnClickListener.affirmCallBack affirmCallBack, OnClickListener.cancelCallBack cancelCallBack) {
        this.title = title;
        this.content = content;
        this.cancelable = cancelable;
        this.singleAffirm = singleAffirm;
        this.affirmCallBack = affirmCallBack;
        this.cancelCallBack = cancelCallBack;
        show();
    }

    public void showDialog(String title, String content, OnClickListener.affirmCallBack affirmCallBack, OnClickListener.cancelCallBack cancelCallBack) {
        showDialog(title, content, false, false, affirmCallBack, cancelCallBack);
    }

    public void showDialog(String title, String content, OnClickListener.affirmCallBack affirmCallBack) {
        showDialog(title, content, false, true, affirmCallBack, null);
    }
}
