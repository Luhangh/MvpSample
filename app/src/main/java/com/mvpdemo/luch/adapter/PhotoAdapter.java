package com.mvpdemo.luch.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mvpdemo.luch.R;
import com.mvpdemo.luch.base.MvpAppliption;
import com.mvpdemo.luch.bean.WelfarePhotoList;
import com.squareup.picasso.Picasso;


/**
 * Creator lh on 2017/6/22 13:15.
 * Email:luchefg@gmail.com
 * Description:
 */

public class PhotoAdapter extends BaseQuickAdapter<WelfarePhotoList.ResultsBean, BaseViewHolder> {


    public PhotoAdapter() {
        super(R.layout.item_rec_photo);
    }


    @Override
    protected void convert(BaseViewHolder helper, WelfarePhotoList.ResultsBean item) {
        Picasso.with(MvpAppliption.getContext()).load(item.getUrl()).into((ImageView) helper.getView(R.id
                .img_photo));
    }


}
