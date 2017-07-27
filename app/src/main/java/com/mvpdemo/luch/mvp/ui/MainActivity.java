package com.mvpdemo.luch.mvp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mvpdemo.luch.R;
import com.mvpdemo.luch.adapter.PhotoAdapter;
import com.mvpdemo.luch.base.BaseActivity;
import com.mvpdemo.luch.bean.WelfarePhotoList;
import com.mvpdemo.luch.mvp.Contract.PhotoContract;
import com.mvpdemo.luch.mvp.presenter.PhotoPresenterImpl;
import com.mvpdemo.luch.utils.L;
import com.mvpdemo.luch.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<PhotoContract.Presenter> implements PhotoContract.View {

    @BindView(R.id.back)
    TextView mBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.rec_list)
    RecyclerView mRecList;

    private int page = 0;
    private PhotoAdapter mPhotoAdapter;
    private List<WelfarePhotoList.ResultsBean> mList;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        new PhotoPresenterImpl(this);
        mPresenter.getPhoto(page);
        mList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecList.setLayoutManager(linearLayoutManager);
        mPhotoAdapter = new PhotoAdapter();
        mRecList.setAdapter(mPhotoAdapter);
        mPhotoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void photoSuccess(WelfarePhotoList welfarePhotoList) {
        L.v(welfarePhotoList.toString());
        mList.addAll(welfarePhotoList.getResults());
        mPhotoAdapter.setNewData(mList);
    }

    @Override
    public void photoFaild() {

    }

    @Override
    public void finishRefresh() {
        super.finishRefresh();
        mList.clear();
        page = 0 ;
        mPresenter.getPhoto(page);
    }



    @Override
    public void setPresenter(@NonNull PhotoContract.Presenter presenter) {
        mPresenter = StringUtils.checkNotNull(presenter);
    }
}
