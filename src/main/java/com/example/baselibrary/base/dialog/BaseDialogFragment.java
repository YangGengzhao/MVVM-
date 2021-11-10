package com.example.baselibrary.base.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.baselibrary.R;


public abstract class BaseDialogFragment extends DialogFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.BottomDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }


    /**
     * 设置布局
     * */
    public abstract int getLayoutId();


    /**
     * 加载数据
     */
    public abstract void initData();

    /**
     * 初始化View
     * */
    public abstract void initView(View view);


    public abstract void initListener();

    /**
     * 是否能触摸外部关闭dialog
     * */
    public void CanceledOnTouchOutside() {
        getDialog().setCanceledOnTouchOutside(false);
    }


}
