package com.example.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

import com.example.baselibrary.log.MLog;

import java.util.List;


public class CheckClientUtil {
    /**
     * 检测是否安装微信
     *
     * @param context
     * @return
     */
    public static boolean isWxInstall(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 检查是否安装QQ
     *
     * @param context
     * @return
     */
    public static boolean isQQInstall(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 检查是否安装微博
     *
     * @param context
     * @return
     */
    public static boolean isWeiboInstall(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.sina.weibo")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 根据指定包名查找是否已经安装应用，没有权限限制
     */
    public static boolean CheckInstallApp(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) return false;
        if (context.getPackageManager() != null) {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent != null) {
                List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
                if (resolveInfos != null && resolveInfos.size() > 0) {
                    for (ResolveInfo resolveInfo : resolveInfos) {
                        if (resolveInfo != null && resolveInfo.activityInfo != null) {
                            if (!StringUtil.isEmpty(resolveInfo.activityInfo.packageName) && resolveInfo.activityInfo.packageName.equals(packageName)) {
                                MLog.e("packageName", "--------------->" + resolveInfo.activityInfo.packageName + "");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
