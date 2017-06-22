package com.mvpdemo.luch.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * 检查权限的工具类
 * <p/>
 * Created by luch on 17/3/3.
 */
public class PermissionsChecker {
    private static Context mContext;

    private static PermissionsChecker sPermissionsChecker;

    public PermissionsChecker(Context context) {
        mContext = context.getApplicationContext();
    }

    public synchronized static PermissionsChecker getInstance(Context context) {
        if (sPermissionsChecker == null) {
            sPermissionsChecker = new PermissionsChecker(context);
        } else {
            mContext = context.getApplicationContext();
        }
        return sPermissionsChecker;
    }

    // 判断权限集合
    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) ==
                PackageManager.PERMISSION_DENIED;
    }
}
