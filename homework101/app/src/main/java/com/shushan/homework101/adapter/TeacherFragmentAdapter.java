package com.shushan.homework101.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class TeacherFragmentAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitles ;
    private List<Fragment> mFragments ;
    public TeacherFragmentAdapter(FragmentManager fm,List<String> mTitles,List<Fragment> mFragments) {
        super(fm);
        this.mFragments=mFragments;
        this.mTitles=mTitles;
    }

    @Override
    public Fragment getItem(int i) {
        return  mFragments==null?null:mFragments.get(i);
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
