package com.example.baselibrary.service;

public interface BaseInterFace {
    void showToast(String msg);//Toast弹出

    void showLoadingDialog(String msg);//loading弹出

    void closeLoadingDialog();//loading 消失
}
