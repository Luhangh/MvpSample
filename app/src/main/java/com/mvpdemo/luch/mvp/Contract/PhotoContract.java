package com.mvpdemo.luch.mvp.Contract;

import com.mvpdemo.luch.base.IBasePresenter;
import com.mvpdemo.luch.base.IBaseView;
import com.mvpdemo.luch.bean.WelfarePhotoList;

import rx.Observer;

/**
 * Creator lh on 2017/6/22 11:39.
 * Email:luchefg@gmail.com
 * Description:
 */

public class PhotoContract {

    public interface View extends IBaseView<Presenter> {


        void photoSuccess(WelfarePhotoList welfarePhotoList);

        void photoFaild();
    }

    public interface Presenter extends IBasePresenter {
        void getPhoto(int page);
    }

    public interface Model {
        void getPhoto(int page, Observer observer);

    }

}
