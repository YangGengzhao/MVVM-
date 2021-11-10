package com.example.baselibrary.utils;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * @author David
 * @description:
 * @date :2020/7/29 16:11
 */
public class WebViewSettingUtil {

    /**
     * webview通用的设置
     * @param webView
     */
    public static void generalSetting(WebView webView){

        //防止http图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false); //隐藏原生的缩放控件
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(false);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
//        //设置支持DomStorage
        webView.getSettings().setDomStorageEnabled(true);
//        // 设置支持本地存储
//        webView.getSettings().setDatabaseEnabled(true);
//        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //启用地理定位
        webView.getSettings().setGeolocationEnabled(true);
        //防止中文乱码
        webView.getSettings().setDefaultTextEncodingName("UTF -8");

        //webview调试
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(true);
        }

    }




}
