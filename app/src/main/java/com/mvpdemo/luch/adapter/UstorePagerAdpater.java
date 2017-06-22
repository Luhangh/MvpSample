package com.mvpdemo.luch.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 轮播图
 * */
public class UstorePagerAdpater extends PagerAdapter {

    private List<Map<String, Object>> pagerView;
    private Context context;

    public UstorePagerAdpater(List<Map<String, Object>> pagerView, Context mContext) {
        this.pagerView = new ArrayList<Map<String, Object>>();
        this.pagerView = pagerView;
        this.context = mContext;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return pagerView.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(View v, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) v).removeView((View) pagerView.get(position).get("PIC"));
    }

    @Override
    public void finishUpdate(View container) {
        // TODO Auto-generated method stub
    }

    @Override
    public Object instantiateItem(View v, final int position) {
        final String str = pagerView.get(position).get("PATH").toString();
        final String clickable = pagerView.get(position).get("APPIMG_CLICK").toString();
        ((View) pagerView.get(position).get("PIC")).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //如果是0可以加载
                if ("0".equals(clickable)) {
                /* Intent it = new Intent(context,webActivity.class);
                 it.putExtra("url", str);*/
				/* it.setAction("android.intent.action.VIEW");    
				    it.setData(Uri.parse(str));  */
                    //context.startActivity(it);
                }
            }
        });

        ((ViewPager) v).addView((View) pagerView.get(position).get("PIC"));
        return pagerView.get(position).get("PIC");
    }
}
