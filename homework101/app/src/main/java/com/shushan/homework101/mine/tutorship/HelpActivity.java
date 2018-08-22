package com.shushan.homework101.mine.tutorship;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.TextView;

import com.shushan.homework101.Constants;
import com.shushan.homework101.R;
import com.shushan.homework101.adapter.HomepageFragmentAdapter;

import java.util.Arrays;

public class HelpActivity extends FragmentActivity {
    TabLayout tl_mine_help;
    ViewPager vp_mine_help;
    private String[] help_order;
    private HomepageFragmentAdapter homepageViewpagerAdapter;
    private TextView tv_actionbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_help);
        initViews();
        initData();
        initEvents();
    }

    protected void initData() {
        help_order = this.getResources().getStringArray(R.array.preference_help_order);
        tl_mine_help.addTab(tl_mine_help.newTab().setText(help_order[0]));
        tl_mine_help.addTab(tl_mine_help.newTab().setText(help_order[1]));
        tl_mine_help.addTab(tl_mine_help.newTab().setText(help_order[2]));
        tl_mine_help.addTab(tl_mine_help.newTab().setText(help_order[3]));
        tv_actionbar.setText(this.getResources().getString(R.string.mine_help));
    }


    protected void initViews() {
        tl_mine_help=findViewById(R.id.tl_mine_help);
        vp_mine_help=findViewById(R.id.vp_mine_help);
        tv_actionbar = findViewById(R.id.tv_actionbar);
    }

    protected void initEvents() {
        tl_mine_help.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
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
        homepageViewpagerAdapter = new HomepageFragmentAdapter(getSupportFragmentManager(), Arrays.asList(help_order), Constants.MINE_HELP_TYPE);
        vp_mine_help.setAdapter(homepageViewpagerAdapter);
        vp_mine_help.setOffscreenPageLimit(help_order.length);
        tl_mine_help.setupWithViewPager(vp_mine_help);
        vp_mine_help.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
}
