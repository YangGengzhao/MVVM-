package com.example.baselibrary.base.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.baselibrary.base.activity.BaseActivity;
import com.example.baselibrary.base.BaseViewModel;
import com.example.baselibrary.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;


public abstract class BaseViewModelFragment<VM extends BaseViewModel, VB extends ViewBinding> extends BaseFragment {
    public VB binding;
    public VM viewModel;
//    public Dialog loadingDialog;
    private BaseActivity activity;

    public abstract VB createViewBanding();//设置ViewBanding

    public abstract VM createViewModel();//设置ViewModel

    public abstract void initData();//初始化数据
    public abstract void initListener();//监听事件

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        activity = (BaseActivity) getActivity();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = createViewBanding();//设置ViewBanding
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = (VM) new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(createViewModel().getClass());//设置ViewModel
        getLifecycle().addObserver(viewModel);//将生命周期方法延申给viewModel（添加观察者）
        //toast 弹出
        viewModel.toast.observe(activity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ToastUtil.show(s);
            }
        });
        //loading弹窗弹出
        viewModel.showLoading.observe(activity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showLoadingDialog(s);
            }
        });
        //loading弹窗关闭
        viewModel.closeLoading.observe(activity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                dismissLoadingDialog();
            }
        });
        initData();
        initListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(viewModel);//移除观察者
    }
}
