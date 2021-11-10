package com.example.baselibrary.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import com.example.baselibrary.base.BaseApplication;
import com.example.baselibrary.eventBus.EventMessage;
import com.example.baselibrary.log.MLog;

import org.greenrobot.eventbus.EventBus;


public class NetWorkChangeReceiver extends BroadcastReceiver {
    EventMessage netWorkEvent = new EventMessage(EventMessage.NetWorkType, null);

    @Override
    public void onReceive(Context context, Intent intent) {
        // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {

            if (!isNetworkAvailable(BaseApplication.baseContext)) {
                MLog.e("TAG", "网络不可用");
                netWorkEvent.data = 0;
//                netWorkEvent.setStatus(0);
                EventBus.getDefault().post(netWorkEvent);
            } else {
                //获取联网状态的NetworkInfo对象
                NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                if (info != null) {
                    //如果当前的网络连接成功并且网络连接可用
                    if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
                        if (info.getType() == ConnectivityManager.TYPE_WIFI || info.getType() == ConnectivityManager.TYPE_MOBILE) {
                            MLog.e("TAG", getConnectionType(info.getType()) + "连上");
                            if ((getConnectionType(info.getType())).equals("移动网络")) {
                                netWorkEvent.data = 1;
//                                netWorkEvent.setStatus(1);
                                EventBus.getDefault().post(netWorkEvent);
                            } else {
//                                netWorkEvent.setStatus(2);
                                netWorkEvent.data = 2;
                                EventBus.getDefault().post(netWorkEvent);
                            }
                        }
                    } else {
                        MLog.e("TAG", getConnectionType(info.getType()) + "断开");
                    }
                }
            }
        }
    }

    public boolean isNetworkAvailable(@NonNull Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    private String getConnectionType(int type) {
        String connType = "";
        if (type == ConnectivityManager.TYPE_MOBILE) {
            connType = "移动网络";
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            connType = "WIFI网络";
        }
        return connType;
    }
}
