package com.shushan.homework101.homepage;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shushan.homework101.HttpHelper.service.entity.homepage.Job;
import com.shushan.homework101.R;
import com.shushan.homework101.adapter.MineHomeworkListViewAdapter;
import com.shushan.homework101.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;

public class MineHomeworkActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.lv_mine_homework)
    ListView lv_mine_homework;
    @Bind(R.id.ll_mine_homework_check)
    LinearLayout ll_mine_homework_check;
    @Bind(R.id.ll_mine_homework_tutorship)
    LinearLayout ll_mine_homework_tutorship;
    @Bind(R.id.tv_mine_homework_check)
    TextView tv_mine_homework_check;
    @Bind(R.id.view_mine_homework_check)
    View view_mine_homework_check;
    @Bind(R.id.tv_mine_homework_tutorship)
    TextView tv_mine_homework_tutorship;
    @Bind(R.id.view_mine_homework_tutorship)
    View view_mine_homework_tutorship;
    @Bind(R.id.iv_mine_homework_back)
    ImageView iv_mine_homework_back;
    private ArrayList<Job> releaseHomeworkBeans;
    private MineHomeworkListViewAdapter mineHomeworkListViewAdapter;
    boolean isCheckType=true;
    private ArrayList<Job> checkList;
    private ArrayList<Job> helpList;

    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_mine_homework);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        ll_mine_homework_check.setOnClickListener(this);
        ll_mine_homework_tutorship.setOnClickListener(this);
        iv_mine_homework_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        releaseHomeworkBeans = getIntent().getParcelableArrayListExtra("homework_bean");
        checkList = new ArrayList<>();
        helpList = new ArrayList<>();
        if(releaseHomeworkBeans!=null&&releaseHomeworkBeans.size()>0){
            for (int i = 0; i <releaseHomeworkBeans.size() ; i++) {
                if(releaseHomeworkBeans.get(i).getLabel()==1){
                    checkList.add(releaseHomeworkBeans.get(i));
                }else{
                    helpList.add(releaseHomeworkBeans.get(i));
                }
            }
        }
        mineHomeworkListViewAdapter = new MineHomeworkListViewAdapter(this,checkList,isCheckType);
        lv_mine_homework.setAdapter(mineHomeworkListViewAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_mine_homework_back:
                finish();
                break;
            case R.id.ll_mine_homework_check:
                isCheckType=true;
                view_mine_homework_check.setVisibility(View.VISIBLE);
                tv_mine_homework_check.setTextColor(getResources().getColor(R.color.font_color_select));
                tv_mine_homework_tutorship.setTextColor(getResources().getColor(R.color.black));
                view_mine_homework_tutorship.setVisibility(View.INVISIBLE);
                mineHomeworkListViewAdapter = new MineHomeworkListViewAdapter(this,checkList,isCheckType);
                lv_mine_homework.setAdapter(mineHomeworkListViewAdapter);
                break;
            case R.id.ll_mine_homework_tutorship:
                isCheckType=false;
                view_mine_homework_check.setVisibility(View.INVISIBLE);
                tv_mine_homework_check.setTextColor(getResources().getColor(R.color.black));
                tv_mine_homework_tutorship.setTextColor(getResources().getColor(R.color.font_color_select));
                view_mine_homework_tutorship.setVisibility(View.VISIBLE);
                mineHomeworkListViewAdapter = new MineHomeworkListViewAdapter(this,helpList,isCheckType);
                lv_mine_homework.setAdapter(mineHomeworkListViewAdapter);
                break;
        }
    }
}
