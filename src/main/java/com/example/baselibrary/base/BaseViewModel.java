package com.example.baselibrary.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baselibrary.eventBus.EventMessage;
import com.example.baselibrary.eventBus.ViewModelBean;
import com.example.baselibrary.service.BaseInterFace;
import com.example.baselibrary.service.ViewModelLifecycle;

/**
 * ViewModel基类
 */
public class BaseViewModel extends ViewModel implements ViewModelLifecycle, BaseInterFace {


    public MutableLiveData<String> toast = new MutableLiveData<>();
    public MutableLiveData<String> showLoading = new MutableLiveData<>();
    public MutableLiveData<String> closeLoading = new MutableLiveData<>();
    public ViewModelBean viewModelBean = new ViewModelBean();

    //弹出toast
    public void showToast(String msg) {
        toast.setValue(msg);
    }

    //显示Loading
    public void showLoadingDialog(String msg) {
        showLoading.postValue(msg);

    }

    public void showLoadingDialog() {
        showLoadingDialog("加载中...");

    }

    //隐藏Loading
    public void closeLoadingDialog() {
        closeLoading.postValue(null);
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
