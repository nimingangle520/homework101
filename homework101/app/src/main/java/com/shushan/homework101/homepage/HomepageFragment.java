package com.shushan.homework101.homepage;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shushan.homework101.Constants;
import com.shushan.homework101.R;
import com.shushan.homework101.adapter.HomepageFragmentAdapter;
import com.shushan.homework101.base.BaseFragment;
import com.shushan.homework101.homework.TakePhoteActivity;

import java.util.Arrays;

import butterknife.Bind;

public class HomepageFragment extends BaseFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    @Bind(R.id.tb_homepage_teacher)
    TabLayout tb_homepage_teacher;
    @Bind(R.id.vp_homepage_teacher)
    ViewPager vp_homepage_teacher;
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.bt_homepage_check)
    Button bt_homepage_check;
    private String[] grade_school;
    private Context context;
    private Activity activity;
    private HomepageFragmentAdapter homepageViewpagerAdapter;

    public HomepageFragment() {
        // Required empty public constructor
    }
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void initViews(View view) {
        this.context=getContext();
        this.activity=getActivity();
    }

    @Override
    protected void initData() {
        grade_school = getActivity().getResources().getStringArray(R.array.preference_grade_school);
        tb_homepage_teacher.addTab(tb_homepage_teacher.newTab().setText(grade_school[0]));
        tb_homepage_teacher.addTab(tb_homepage_teacher.newTab().setText(grade_school[1]));
        tb_homepage_teacher.addTab(tb_homepage_teacher.newTab().setText(grade_school[2]));
        tv_actionbar.setText(getResources().getString(R.string.app_name));
    }

    @Override
    public void initEvents() {
        bt_homepage_check.setOnClickListener(this);
        tb_homepage_teacher.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
        homepageViewpagerAdapter = new HomepageFragmentAdapter(getChildFragmentManager(), Arrays.asList(grade_school), Constants.HOMEPAGR_TEACHER_TYPE);
        vp_homepage_teacher.setAdapter(homepageViewpagerAdapter);
        vp_homepage_teacher.setOffscreenPageLimit(grade_school.length);
        tb_homepage_teacher.setupWithViewPager(vp_homepage_teacher);
        vp_homepage_teacher.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bt_homepage_check:
                startActivitys(mContext, TakePhoteActivity.class);
                break;
        }
    }
}
