package com.example.baselibrary.utils;

public class ClickHelperUtil {
    private static long lastClickTime = 0;

    /**
     * 判断事件出发时间间隔是否超过预定值
     * 如果小于间隔（目前是1000毫秒）则返回true，否则返回false
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
