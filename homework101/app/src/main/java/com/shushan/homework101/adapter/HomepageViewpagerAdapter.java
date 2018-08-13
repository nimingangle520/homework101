package com.shushan.homework101.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HomepageViewpagerAdapter extends PagerAdapter {
    private List<View> pageViews;

    public HomepageViewpagerAdapter(List<View> pageViews) {
        super();
        this.pageViews = pageViews;
    }

    @Override
    public int getCount() {
        if (null != pageViews) {
            return pageViews.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(pageViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        System.out.println("第几个pager=="+position);
        try {
            if(pageViews.get(position).getParent()==null)
                ((ViewPager) container).addView(pageViews.get(position), 0);
            else{
                ((ViewGroup)pageViews.get(position).getParent()).removeView(pageViews.get(position));
                ((ViewPager) container).addView(pageViews.get(position), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageViews.get(position);
    }
}

