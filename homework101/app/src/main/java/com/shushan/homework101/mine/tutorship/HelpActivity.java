package com.shushan.homework101.mine.tutorship;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shushan.homework101.Constants;
import com.shushan.homework101.R;
import com.shushan.homework101.adapter.HomepageFragmentAdapter;

import java.util.Arrays;

public class HelpActivity extends FragmentActivity implements View.OnClickListener{
    TabLayout tl_mine_help;
    ViewPager vp_mine_help;
    private String[] help_order;
    private HomepageFragmentAdapter homepageViewpagerAdapter;
    private LinearLayout ll_mine_tutorship_homework_check;
    private LinearLayout ll_mine_tutorship_homework_tutorship;
    private View view_mine_tutorship_homework_tutorship;
    private View view_mine_tutorship_homework_check;
    private int mine_order_label=1;
    private TextView tv_mine_tutorship_homework_check;
    private TextView tv_mine_tutorship_homework_tutorship;
    private ImageView iv_mine_tutorship_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_help);
        initViews();
        initData();
        initEvents();
    }

    protected void initData() {
        SharedPreferences sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
        sharedPreferences.edit().putInt("mine_order_label",mine_order_label).commit();
        help_order = this.getResources().getStringArray(R.array.preference_help_order);
        tl_mine_help.addTab(tl_mine_help.newTab().setText(help_order[0]));
        tl_mine_help.addTab(tl_mine_help.newTab().setText(help_order[1]));
        tl_mine_help.addTab(tl_mine_help.newTab().setText(help_order[2]));
        tl_mine_help.addTab(tl_mine_help.newTab().setText(help_order[3]));
    }
    /**
     * ½çÃæÉèÖÃ×´Ì¬À¸×ÖÌåÑÕÉ«
     */
    public void changeStatusBarTextImgColor(boolean isBlack) {
        if (isBlack) {
            //ÉèÖÃ×´Ì¬À¸ºÚÉ«×ÖÌå
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            //»Ö¸´×´Ì¬À¸°×É«×ÖÌå
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }

    protected void initViews() {
        tl_mine_help=findViewById(R.id.tl_mine_help);
        vp_mine_help=findViewById(R.id.vp_mine_help);
        ll_mine_tutorship_homework_check = findViewById(R.id.ll_mine_tutorship_homework_check);
        ll_mine_tutorship_homework_tutorship = findViewById(R.id.ll_mine_tutorship_homework_tutorship);
        view_mine_tutorship_homework_check = findViewById(R.id.view_mine_tutorship_homework_check);
        view_mine_tutorship_homework_tutorship = findViewById(R.id.view_mine_tutorship_homework_tutorship);
        tv_mine_tutorship_homework_check = findViewById(R.id.tv_mine_tutorship_homework_check);
        tv_mine_tutorship_homework_tutorship = findViewById(R.id.tv_mine_tutorship_homework_tutorship);
        iv_mine_tutorship_back = findViewById(R.id.iv_mine_tutorship_back);
    }

    protected void initEvents() {
        iv_mine_tutorship_back.setOnClickListener(this);
        ll_mine_tutorship_homework_check.setOnClickListener(this);
        ll_mine_tutorship_homework_tutorship.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_mine_tutorship_back:
                finish();
                break;
            case R.id.ll_mine_tutorship_homework_check:
                mine_order_label=1;
                view_mine_tutorship_homework_check.setVisibility(View.VISIBLE);
                view_mine_tutorship_homework_tutorship.setVisibility(View.INVISIBLE);
                tv_mine_tutorship_homework_check.setTextColor(getResources().getColor(R.color.font_color_select));
                tv_mine_tutorship_homework_tutorship.setTextColor(getResources().getColor(R.color.black));
                SharedPreferences sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
                sharedPreferences.edit().putInt("mine_order_label",mine_order_label).commit();

                homepageViewpagerAdapter = new HomepageFragmentAdapter(getSupportFragmentManager(), Arrays.asList(help_order), Constants.MINE_HELP_TYPE);
                vp_mine_help.setAdapter(homepageViewpagerAdapter);
                vp_mine_help.setOffscreenPageLimit(help_order.length);
                tl_mine_help.setupWithViewPager(vp_mine_help);

                break;
            case R.id.ll_mine_tutorship_homework_tutorship:
                mine_order_label=2;
                view_mine_tutorship_homework_check.setVisibility(View.INVISIBLE);
                view_mine_tutorship_homework_tutorship.setVisibility(View.VISIBLE);
                tv_mine_tutorship_homework_check.setTextColor(getResources().getColor(R.color.black));
                tv_mine_tutorship_homework_tutorship.setTextColor(getResources().getColor(R.color.font_color_select));
                SharedPreferences spf_help=getSharedPreferences("info",MODE_PRIVATE);
                spf_help.edit().putInt("mine_order_label",mine_order_label).commit();

                homepageViewpagerAdapter = new HomepageFragmentAdapter(getSupportFragmentManager(), Arrays.asList(help_order), Constants.MINE_HELP_TYPE);
                vp_mine_help.setAdapter(homepageViewpagerAdapter);
                vp_mine_help.setOffscreenPageLimit(help_order.length);
                tl_mine_help.setupWithViewPager(vp_mine_help);

                break;

        }
    }
}
