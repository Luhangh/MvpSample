package com.mvpdemo.luch.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.mvpdemo.luch.R;
import com.mvpdemo.luch.adapter.UstorePagerAdpater;
import com.mvpdemo.luch.utils.L;
import com.mvpdemo.luch.utils.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AutoPagerView extends FrameLayout implements OnPageChangeListener {

    private final Timer timer = new Timer();
    private Context mContent;
    private ViewPager mViewPager;
    private List<Map<String, Object>> picPagelist;
    private int curItemPic = 0;
    private LinearLayout lineary;
    private List<ImageView> pointImg;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPager.setCurrentItem(curItemPic);
//			mViewPager.setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					Log.i("AutoPagerView....","autoPagerView onclick");
//					T.showShort(mContent, "curItemPic"+curItemPic);
//				}
//			});
        }

        ;
    };

    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (picPagelist.size() == 0) {
                return;
            } else {
                synchronized (mViewPager) {
                    curItemPic = (curItemPic + 1) % picPagelist.size();
                    handler.obtainMessage().sendToTarget();
                }
            }
        }
    };

    public AutoPagerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /*加载布局文件时执行*/
    public AutoPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutoPagerView(Context context) {
        super(context);
        init(context, null);
    }

    public void init(Context context, AttributeSet attrs) {
        L.e("ustore", "intit");
        this.mContent = context;

        pointImg = new ArrayList<ImageView>();

        FrameLayout frameContainer = new FrameLayout(mContent);

        LayoutParams containerParams = new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT);

        addView(frameContainer, containerParams);

        mViewPager = new ViewPager(mContent);
        frameContainer.addView(mViewPager);

        mViewPager.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                L.i("AutoPagerView....", "autoPagerView onclick");
                T.showShort(mContent, "curItemPic" + curItemPic);
            }
        });


        lineary = new LinearLayout(mContent);

        LayoutParams LinearyParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        lineary.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        lineary.setPadding(0, 0, 0, 10);
        frameContainer.addView(lineary, LinearyParams);
    }

    public void changeCurPontImg(int curPos) {
        for (int i = 0; i < picPagelist.size(); i++) {
            if (i == curPos) {
                pointImg.get(i).setBackgroundResource(R.mipmap.page_indicator_unfocused);
            } else {
                pointImg.get(i).setBackgroundResource(R.mipmap.page_indicator_focused);
            }
        }
    }

    public void setPageViewPics(List<Map<String, Object>> picPageList) {
        this.picPagelist = picPageList;
        mViewPager.setAdapter(new UstorePagerAdpater(this.picPagelist, mContent));
        mViewPager.setOnPageChangeListener(this);

        for (int i = 0; i < picPageList.size(); i++) {
            ImageView imageview = new ImageView(mContent);
            imageview.setBackgroundResource(R.mipmap.page_indicator_unfocused);
            pointImg.add(imageview);
//			imageview.setPadding(10, 0, 10, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 10, 10, 0);
            imageview.setLayoutParams(params);
//			imageview.setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//
//					T.showShort(mContent, "success");
//				}
//			});
            lineary.addView(imageview);
        }

        mViewPager.setCurrentItem(0);
        pointImg.get(0).setBackgroundResource(R.mipmap.page_indicator_unfocused);
        timer.schedule(task, 4000, 4000);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {


    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {


    }

    @Override
    public void onPageSelected(int position) {

        this.curItemPic = position;
        changeCurPontImg(position);
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


}
