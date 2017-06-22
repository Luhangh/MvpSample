package com.mvpdemo.luch.base;

import static com.tencent.bugly.beta.tinker.TinkerManager.getApplication;

import android.app.Application;
import android.content.Context;
import android.support.compat.BuildConfig;

import com.mvpdemo.luch.api.RetrofitService;
import com.mvpdemo.luch.utils.AppUtils;
import com.mvpdemo.luch.utils.CrashHandler;
import com.mvpdemo.luch.utils.L;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Creator lh on 2017/6/22 10:06.
 * Email:luchefg@gmail.com
 * Description: 全局初始化
 */

public class MvpAppliption  extends Application{

    public static final String TAG = "MvpAppliption";

    private static Context mContext;

    private Boolean isDebug = BuildConfig.DEBUG;

    //全局context获取
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initConfig();
        initCrashHandler();
        initBugly();
        _initInjector();
        initLeakCanary();
    }

    //内存泄露检查
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(mContext)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    //日志捕获缓存类
    private void initCrashHandler() {
        //日志捕获缓存类
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(mContext);
    }

    /**
     * 初始化配置
     */
    private void initConfig() {
        L.isDebug = BuildConfig.DEBUG;
        RetrofitService.init();
    }

    /**
     * 初始化注射器
     */
    private void _initInjector() {
        // 这里不做注入操作，只提供一些全局单例数据
       /* sAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(ApplicationLike.this, mRxBus))
                .build();*/
    }

    //TODO 腾讯bugly
    private void initBugly() {
        try {
            // 获取当前包名
            String packageName = getPackageName();
            // 获取当前进程名
            String processName = AppUtils.getProcessName(android.os.Process.myPid());
            // 设置是否为上报进程
            CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplication());
            strategy.setUploadProcess(processName == null || processName.equals(packageName));
            strategy.setAppChannel("luchMvp");
            // 设置开发设备
            Bugly.setIsDevelopmentDevice(this, isDebug);
            Bugly.init(getApplicationContext(), "5b1bfad4a7", isDebug,
                    strategy);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
