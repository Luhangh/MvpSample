package com.mvpdemo.luch.api;

import static com.mvpdemo.luch.api.RetrofitService.CACHE_CONTROL_NETWORK;

import com.mvpdemo.luch.bean.WelfarePhotoList;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Creator lh on 2017/6/22 9:59.
 * Email:luchefg@gmail.com
 * Description:  接口定义
 */

public interface jokeApi {

    /**
     * 获取福利图片
     * eg: http://gank.io/api/data/福利/10/1
     *
     * @param page 页码
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @GET("/api/data/福利/10/{page}")
    Observable<WelfarePhotoList> getWelfarePhoto(@Path("page") int page);
}
