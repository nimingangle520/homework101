package com.shushan.homework101.homepage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.homepage.Homepage;
import com.shushan.homework101.HttpHelper.service.entity.homepage.Job;
import com.shushan.homework101.HttpHelper.service.presenter.HomepagePresenter;
import com.shushan.homework101.HttpHelper.service.view.HomepageView;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.adapter.HomepageFragmentAdapter;
import com.shushan.homework101.adapter.HomepageReleaseHomeworkAdapter;
import com.shushan.homework101.banner.BannerView;
import com.shushan.homework101.banner.HolderCreator;
import com.shushan.homework101.banner.ImageViewRoundRect;
import com.shushan.homework101.banner.ViewHolder;
import com.shushan.homework101.base.BaseFragment;
import com.shushan.homework101.homework.clip.TakePhoteActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

import static android.content.Context.MODE_PRIVATE;

public class HomepageFragment extends BaseFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    @Bind(R.id.tb_homepage_teacher)
    TabLayout tb_homepage_teacher;
    @Bind(R.id.vp_homepage_teacher)
    ViewPager vp_homepage_teacher;
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.rl_homepage_inspect)
    RelativeLayout rl_homepage_inspect;
    @Bind(R.id.rl_homepage_tutorship)
    RelativeLayout rl_homepage_tutorship;
    @Bind(R.id.iv_actionbar_left)
    ImageView iv_actionbar_left;
    @Bind(R.id.iv_actionbar_right)
    ImageView iv_actionbar_right;
    @Bind(R.id.banner_homepage)
    BannerView banner_homepage;
    @Bind(R.id.grid_homepage_release_homework)
    GridView grid_homepage_release_homework;
    @Bind(R.id.tv_homepage_homework_sum)
    TextView tv_homepage_homework_sum;
    @Bind(R.id.rl_homepage_release_homework)
    RelativeLayout rl_homepage_release_homework;
  /*  @Bind(R.id.homepage_scroll_menu)
    ScrollMenu homepage_scroll_menu;*/
    @Bind(R.id.ll_homepage_release)
    LinearLayout ll_homepage_release;
    private String[] grade_school;
    private Context context;
    private Activity activity;
    private HomepageFragmentAdapter homepageViewpagerAdapter;
    private int[] res;
    private HomepageReleaseHomeworkAdapter homepageReleaseHomeworkAdapter;
    private ArrayList<Job> jobListTotoal;
    private HomepagePresenter homepagePresenter;
    private String userid;
    private String token;
    private String grade_position;
    private Homepage mHomepage;
    private ArrayList<Job> jobList;

    public HomepageFragment() {

    }
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_homepage;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initViews(View view) {
        this.context=getContext();
        this.activity=getActivity();
        jobListTotoal=new ArrayList<>();
        View mStatusBar = view.findViewById(R.id.fillStatusBarView);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mStatusBar.getLayoutParams();
        lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        lp.height = getStatusBarHeight(mContext);
        mStatusBar.setLayoutParams(lp);
        homepagePresenter = new HomepagePresenter(context);
        homepagePresenter.onCreate(Constants.BASE_URL);
        homepagePresenter.attachView(homepageView);

        /*homepage_scroll_menu.setOnScrollCompleteListener(new ScrollMenu.OnScrollCompleteListener() {
            @Override
            public void completeTop() {
                banner_homepage.setVisibility(View.GONE);
            }

            @Override
            public void completeBottom() {
                banner_homepage.setVisibility(View.VISIBLE);
            }
        });
        homepage_scroll_menu.setOpenHorizontalSlide(false);
        homepage_scroll_menu.setOpenVerticalSlide(true);*/

    }

    @Override
    protected void initData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("userid", "");
        token = sharedPreferences.getString("token", "");
        grade_position = sharedPreferences.getString("grade_position","");

        if(!TextUtils.isEmpty(grade_position)){

            if(Integer.parseInt(grade_position)>=0&&Integer.parseInt(grade_position)<=5){
                tb_homepage_teacher.setTabMode(TabLayout.MODE_FIXED);
                grade_school = getActivity().getResources().getStringArray(R.array.preference_primary_course);
                for (int i = 0; i <grade_school.length; i++) {
                    tb_homepage_teacher.addTab(tb_homepage_teacher.newTab().setText(grade_school[i]));
                }
            }else{
                tb_homepage_teacher.setTabMode(TabLayout.MODE_SCROLLABLE);
                grade_school = getActivity().getResources().getStringArray(R.array.preference_middle_school_course);
                for (int i = 0; i <grade_school.length; i++) {
                    tb_homepage_teacher.addTab(tb_homepage_teacher.newTab().setText(grade_school[i]));
                }
            }
        }

        tv_actionbar.setText(getResources().getString(R.string.app_name));
        tv_actionbar.setTextColor(getResources().getColor(R.color.white));
        iv_actionbar_left.setImageResource(R.drawable.home_using_help);
        iv_actionbar_right.setVisibility(View.VISIBLE);
        iv_actionbar_right.setImageResource(R.drawable.home_news);

        res = new int[]{R.drawable.image5,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image6,R.drawable.image7,R.drawable.image8};
        List<Integer> listRes = new ArrayList<>();
        for(int i=0;i<res.length;i++){
            listRes.add(res[i]);
        }
        banner_homepage.setPages(listRes, new HolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }

    private HomepageView homepageView = new HomepageView() {
        @Override
        public void onSuccess(Homepage homepage) {
            if(homepage!=null){
                LogUtils.d(homepage.toString());
                mHomepage = homepage;
                setGridView(homepage);
            }

        }

        @Override
        public void onError(String result) {
            LogUtils.e(result.toString());
        }
    };

    private void setGridView(Homepage homepage) {
        jobList = homepage.getData().getJob();
        if(jobList !=null&& jobList.size()>0){
            for (int i = 0; i < jobList.size() ; i++) {
                if(jobList.get(i).getImages()!=null&& jobList.get(i).getImages().length>0){
                    for (int j = 0; j < jobList.get(i).getImages().length; j++) {

                        Job job =new Job();
                        job.setLabel(jobList.get(i).getLabel());
                        job.setImages(new String[]{jobList.get(i).getImages()[j]});
                        job.setStart_time(jobList.get(i).getStart_time());
                        job.setIs_receive(jobList.get(i).getIs_receive());
                        job.setCreate_time(jobList.get(i).getCreate_time());
                        jobListTotoal.add(job);
                    }
                }
            }
        }
        if(jobListTotoal!=null&&jobListTotoal.size()>0) {

            tv_homepage_homework_sum.setText(jobListTotoal.size() + "");
            homepageReleaseHomeworkAdapter = new HomepageReleaseHomeworkAdapter(jobListTotoal, context);

            int size = jobListTotoal.size();
            int length = (int) getResources().getDimension(R.dimen.x150);
            int gridviewWidth = size * (length + (int) getResources().getDimension(R.dimen.x8)) + (int) getResources().getDimension(R.dimen.x7);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);

            grid_homepage_release_homework.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
            grid_homepage_release_homework.setColumnWidth((int) getResources().getDimension(R.dimen.x150)); // 设置列表项宽
            grid_homepage_release_homework.setHorizontalSpacing((int) getResources().getDimension(R.dimen.x8)); // 设置列表项水平间距
            grid_homepage_release_homework.setStretchMode(GridView.NO_STRETCH);
            grid_homepage_release_homework.setNumColumns(jobListTotoal.size()); // 设置列数量=列表集合数

            grid_homepage_release_homework.setAdapter(homepageReleaseHomeworkAdapter);
        }
    }


    public static class BannerViewHolder implements ViewHolder<Integer> {
        private ImageViewRoundRect mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageViewRoundRect) view.findViewById(R.id.banner_image);
            mImageView.setType(ImageViewRoundRect.TYPE_ROUND);
            //mImageView.setRoundRadius((int)context.getResources().getDimension(R.dimen.x4));
            mImageView.setRoundRadius(0);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }

    @Override
    public void initEvents() {
        rl_homepage_tutorship.setOnClickListener(this);
        rl_homepage_release_homework.setOnClickListener(this);
        rl_homepage_inspect.setOnClickListener(this);
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

        homepageViewpagerAdapter = new HomepageFragmentAdapter(getChildFragmentManager(), new ArrayList<String>(Arrays.asList(grade_school)), Constants.HOMEPAGR_TEACHER_TYPE);
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
        if(!TextUtils.isEmpty(userid)&&!TextUtils.isEmpty(token)){
            homepagePresenter.getHomepageMsg(userid,token);
        }
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.rl_homepage_tutorship:
                SharedPreferences sharedPreferences=context.getSharedPreferences("info",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("homework_type",Constants.TYPE_HOMEWORK_TUTORSHIP);
                editor.commit();
                startActivitys(mContext, TakePhoteActivity.class);
                break;
            case R.id.rl_homepage_inspect:
                SharedPreferences sdf_help=context.getSharedPreferences("info",MODE_PRIVATE);
                SharedPreferences.Editor editor_help=sdf_help.edit();
                editor_help.putInt("homework_type",Constants.TYPE_HOMEWORK_CHECK);
                editor_help.commit();
                startActivitys(mContext, TakePhoteActivity.class);
                break;
            case R.id.rl_homepage_release_homework:
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("homework_bean", jobList);
                startActivityWithBundle(context,MineHomeworkActivity.class,bundle);
                break;
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        banner_homepage.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        banner_homepage.start();
        changeStatusBarTextImgColor(false);

    }
}
