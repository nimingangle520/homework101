package com.shushan.homework101.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shushan.homework101.base.BaseFragment;
import com.shushan.homework101.homepage.FragmentFactory;

import java.util.List;


public class HomepageFragmentAdapter extends FragmentStatePagerAdapter {
    //private List<Fragment> mFragments ;
    private List<String> mTitles ;
    private int type;
    public HomepageFragmentAdapter(FragmentManager fm, List<String> titles, int type) {
        super(fm);
        //mFragments = fragments;
        this.mTitles = titles;
        this.type=type;

    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment =  FragmentFactory.createFragment(position, type);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles == null ?0:mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
