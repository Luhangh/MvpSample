package com.mvpdemo.luch.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast统一管理类
 */
public class T {

    public static boolean isShow = true;
    public static boolean isShowLOG = true;
    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast日志
     */
    public static void showShortLog(Context context, CharSequence message) {
        if (isShowLOG && null != context) {
            Toast.makeText(context, "LOG==" + StringUtils.object2String(message),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow && null != context) {
            Toast.makeText(context, StringUtils.object2String(message), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, int message) {
        if (isShow && null != context) {
            Toast.makeText(context, StringUtils.object2String(message + ""),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow && null != context) {
            Toast.makeText(context, StringUtils.object2String(message), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, int message) {
        if (isShow && null != context) {
            Toast.makeText(context, StringUtils.object2String(message + ""),
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow && null != context) {
            Toast.makeText(context, StringUtils.object2String(message), duration).show();
        }
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, int message, int duration) {
        if (isShow && null != context) {
            Toast.makeText(context, StringUtils.object2String(message + ""), duration).show();
        }
    }

    /**
     * 全局可显示的Toast
     */
    public static void showMessage(String msg) {
        Toast.makeText(AppManager.getAppManager().currentActivity(), msg,
                Toast.LENGTH_SHORT).show();
    }
}