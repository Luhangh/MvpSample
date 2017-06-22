package com.mvpdemo.luch.base;


import android.support.annotation.NonNull;

import com.mvpdemo.luch.widget.EmptyLayout;


/**
 * Created by long on 2016/8/23.
 * 基础 BaseView 接口
 */
public interface IBaseView<T> {

    /**
     * 显示加载动画
     */
    void showLoading(String msg);

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示网络错误
     *
     * @param onRetryListener 点击监听
     */

    void showNetError(EmptyLayout.OnRetryListener onRetryListener);

    /**
     * 绑定生命周期
     */
    //<T> LifecycleTransformer<T> bindToLife();
    void setPresenter(@NonNull T presenter);

    /**
     * 完成刷新, 新增控制刷新
     */
    void finishRefresh();

}
