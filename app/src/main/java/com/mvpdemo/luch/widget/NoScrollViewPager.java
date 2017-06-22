package com.mvpdemo.luch.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ${xueyuebai} on 2016/1/14.
 */
public class NoScrollViewPager extends ViewPager {

    public static boolean isChange = false;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public NoScrollViewPager(Context context) {
        super(context);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {

        if (isChange) {
            isChange = false;
            return super.onInterceptTouchEvent(arg0);

        }
        return false;
    }

    @Override
    public void setCurrentItem(int item) {

        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {

        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {

        if (isChange) {
            isChange = false;
            return super.onTouchEvent(arg0);
        }
        return false;
    }

    @Override
    public void scrollTo(int x, int y) {

        super.scrollTo(x, y);
    }
}
