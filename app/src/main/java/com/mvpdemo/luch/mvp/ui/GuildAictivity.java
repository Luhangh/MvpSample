package com.mvpdemo.luch.mvp.ui;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mvpdemo.luch.R;
import com.mvpdemo.luch.api.RetrofitService;
import com.mvpdemo.luch.base.BaseActivity;
import com.mvpdemo.luch.bean.WelfarePhotoList;
import com.mvpdemo.luch.utils.RxHelper;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.functions.Action0;

/**
 * Creator lh on 2017/6/28 10:37.
 * Email:luchefg@gmail.com
 * Description:
 */

public class GuildAictivity extends BaseActivity {

    @BindView(R.id.img_guide)
    ImageView mImageView;

    @OnClick(R.id.img_guide)
    public void OnImgClick(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom);
        mImageView.startAnimation(animation);
    }
    @OnClick(R.id.btn_next)
    public void OnBit(){
        startActivity(new Intent(GuildAictivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void setPresenter(@NonNull Object presenter) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_guild;
    }

    @Override
    protected void initViews() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom);
        mImageView.startAnimation(animation);
        gets();
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {
        RxHelper.countdown(3)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        _doSkip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        _doSkip();
                    }

                    @Override
                    public void onNext(Integer integer) {
                    }
                });
    }

    private void _doSkip() {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                startActivity(new Intent(GuildAictivity.this, MainActivity.class));
                finish();
            }
    }
    private void gets(){
        RetrofitService.getWelfarePhoto(1).doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).subscribe(new Subscriber<WelfarePhotoList>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(WelfarePhotoList welfarePhotoList) {
            }
        });
    }
}
