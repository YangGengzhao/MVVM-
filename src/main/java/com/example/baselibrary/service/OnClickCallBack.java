package com.example.baselibrary.service;

import android.view.View;

/**
 * 点击回调
 */
public abstract class OnClickCallBack {
    public void OnClickCall(View view, Object obj){};
    public void OnClickCall(View view,int position,  Object obj){};
    public void OnClickCall(View view,String type, Object obj){};
    public void OnClickCall(View view,boolean status,int position, Object obj){};
}
