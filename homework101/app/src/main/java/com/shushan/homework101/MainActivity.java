package com.shushan.homework101;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.shushan.homework101.homepage.HomepageFragment;
import com.shushan.homework101.mine.FragmentMine;
import com.shushan.homework101.teacher.FragmentTeacher;

public class MainActivity extends FragmentActivity implements BottomNavigationBar.OnTabSelectedListener{

    private BottomNavigationBar mBottomNavigationBar;
    private FragmentTeacher fragmentTeacher;
    private HomepageFragment homepageFragment;
    private FragmentMine fragmentMine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getActivityStack().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
    }
    protected void initViews() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
    }

    protected void initEvents() {
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//      mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//      mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mBottomNavigationBar.setBarBackgroundColor(R.color.homepage_bottom_navigation_bg);//set background color for navigation bar
        mBottomNavigationBar.setInActiveColor(R.color.homepage_bottom_navigation_font_unselected);//unSelected icon color
        mBottomNavigationBar.setActiveColor(R.color.homepage_bottom_navigation_font);
        //.addItem(new BottomNavigationItem(R.drawable.icon_one, R.string.tab_one).setActiveColorResource(R.color.green).setBadgeItem(badgeItem))
        mBottomNavigationBar.
                addItem(new BottomNavigationItem(R.drawable.home, R.string.main_home_page).setInactiveIconResource(R.drawable.home_gray))
                .addItem(new BottomNavigationItem(R.drawable.teacher, R.string.main_teacher).setInactiveIconResource(R.drawable.teacher_gray))
                .addItem(new BottomNavigationItem(R.drawable.my, R.string.main_mine).setInactiveIconResource(R.drawable.my_gray))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    /**
     * set the default fragment
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        homepageFragment = new HomepageFragment();
        transaction.replace(R.id.ll_content,homepageFragment).commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if (homepageFragment == null) {
                    homepageFragment =new HomepageFragment();
                }
                transaction.replace(R.id.ll_content, homepageFragment);
                break;
            case 1:
                if (fragmentTeacher == null) {
                    fragmentTeacher = new FragmentTeacher();
                }
                transaction.replace(R.id.ll_content, fragmentTeacher);
                break;
            case 2:
                if (fragmentMine == null) {
                    fragmentMine = new FragmentMine();
                }
                transaction.replace(R.id.ll_content, fragmentMine);
                break;
            default:
                if (homepageFragment == null) {
                    homepageFragment= new HomepageFragment();
                }
                transaction.replace(R.id.ll_content, homepageFragment);
                break;
        }
        transaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
