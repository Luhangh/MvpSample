package com.mvpdemo.luch.mvp.presenter;


import com.mvpdemo.luch.api.RetrofitService;
import com.mvpdemo.luch.bean.WelfarePhotoList;
import com.mvpdemo.luch.mvp.Contract.PhotoContract;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.functions.Action0;


/**
 * Created by admin on 2017/03/30
 */

public class PhotoPresenterImpl implements PhotoContract.Presenter {

    private PhotoContract.View mView;
    private PhotoContract.Model mModel;

    public PhotoPresenterImpl(PhotoContract.View View) {
        mView = View;
        mView.setPresenter(this);
    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }



    @Override
    public void getPhoto(int page) {
        RetrofitService.getWelfarePhoto(page).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                mView.showLoading("福利加载中");
            }
        }).subscribe(new Subscriber<WelfarePhotoList>() {
            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.photoFaild();
                mView.hideLoading();
            }

            @Override
            public void onNext(WelfarePhotoList welfarePhotoList) {
                mView.photoSuccess(welfarePhotoList);
            }
        });
    }
}