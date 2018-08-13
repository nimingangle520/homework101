package com.shushan.homework101.teacher;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.shushan.homework101.R;
import com.shushan.homework101.adapter.TeacherFragmentAdapter;
import com.shushan.homework101.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;


public class FragmentTeacher extends BaseFragment {
    @Bind(R.id.tl_teacher)
    TabLayout tl_teacher;
    @Bind(R.id.vp_teacher)
    ViewPager vp_teacher;
    private String[] teacher_classify;
    private TeacherFragmentAdapter teacherFragmentAdapter;
    private List<Fragment> fragments;
    private RecommendFragment recommendFragment;
    private FocusFragment focusFragment;

    public FragmentTeacher() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_teacher;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        recommendFragment = new RecommendFragment();
        focusFragment = new FocusFragment();
        fragments.add(recommendFragment);
        fragments.add(focusFragment);
        teacher_classify = new String[]{mContext.getResources().getString(R.string.teacher_recommed),
                mContext.getResources().getString(R.string.teacher_focus)};

        tl_teacher.addTab(tl_teacher.newTab().setText(teacher_classify[0]));
        tl_teacher.addTab(tl_teacher.newTab().setText(teacher_classify[1]));
    }

    @Override
    public void initEvents() {
       /* ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getData());*/
       tl_teacher.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
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
        teacherFragmentAdapter = new TeacherFragmentAdapter(getChildFragmentManager(), Arrays.asList(teacher_classify),fragments);
        vp_teacher.setAdapter(teacherFragmentAdapter);
        vp_teacher.setOffscreenPageLimit(teacher_classify.length);
        tl_teacher.setupWithViewPager(vp_teacher);
        vp_teacher.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    /*private List<String> getData() {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            data.add(i + "");
        }
        return data;
    }*/
}
