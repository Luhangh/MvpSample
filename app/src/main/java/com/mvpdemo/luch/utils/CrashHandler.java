package com.mvpdemo.luch.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Creator lh on 2017/2/24 9:55.
 * Email:3021634343@qq.com
 * Description: 全局异常捕获日志打印
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler instance;
    private final HashMap<String, String> infos = new HashMap<String, String>();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();

        }
        return instance;
    }

    public void init(Context context) {
        this.mContext = context;

        // 获取系统默认的UncaughtException处理类
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        // 设置 CrashHandler为系统默认的处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理让系统默认的来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            //不退出程序
            /*try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            } finally {

            }

            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            //非正常退出
            System.exit(1);*/

        }
    }

    /**
     * @return true
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        final String str = ex.toString();

        // Toast来显示提示信息
        new Thread() {
            @Override
            public void run() {

                Looper.prepare();
                if (str.contains("IllegalStateException")) {
                    L.v("程序异常IllegalStateException");
                    //T.showShort(mContext, "程序异常");
                } else {
                    L.v("糟糕，程序出异常，即将退出！");
                    //T.showShort(mContext, "糟糕，程序出异常，即将退出！");
                }
                Looper.loop();
            }
        }.start();

        // 收集设备参数信息
        collectDeviceInfo(mContext);

        L.e("出错了", ex.getStackTrace().toString());
        L.e((ex.toString() + saveCrashInfo2File(ex)).replace("=", "|"));

        return true;
    }

    /**
     * 收集设备参数信息
     */
    private void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);

            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
//            Log.e(TAG, "an error occured when collect package info", e);
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
//                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
//                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息
     * *
     *
     * @return 返回错误信息
     */
    private String saveCrashInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        return new String(sb);
    }

}
