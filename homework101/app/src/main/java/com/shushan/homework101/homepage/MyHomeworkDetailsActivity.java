package com.shushan.homework101.homepage;

import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shushan.homework101.HttpHelper.service.entity.homepage.Job;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.Utils.Utils;
import com.shushan.homework101.base.BaseActivity;
import com.shushan.homework101.xbanner.BannerLayout;
import com.shushan.homework101.xbanner.WebBannerAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MyHomeworkDetailsActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.iv_actionbar_left)
    ImageView iv_actionbar_left;
    @Bind(R.id.tv_right)
    TextView tv_right;
    @Bind(R.id.recycler)
    BannerLayout recyclerBanner;
    @Bind(R.id.tv_mine_homework_help)
    TextView tv_mine_homework_help;
    @Bind(R.id.iv_mine_homework_time)
    ImageView iv_mine_homework_time;
    @Bind(R.id.tv_mine_homework_time_default)
    TextView tv_mine_homework_time_default;
    @Bind(R.id.tv_mine_homework_state)
    TextView tv_mine_homework_state;
    @Bind(R.id.tv_mine_homework_help_price)
    TextView tv_mine_homework_help_price;
    @Bind(R.id.tv_mine_homework_help_price_default)
    TextView tv_mine_homework_help_price_default;
    @Bind(R.id.iv_mine_homework_state)
    ImageView iv_mine_homework_state;
    @Bind(R.id.rl_mine_homework)
    RelativeLayout rl_mine_homework;
    @Bind(R.id.tv_mine_homework_class)
    TextView tv_mine_homework_class;
    private Job job;
    private List<String> list;

    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_my_homework_details);
    }

    @Override
    protected void initViews() {
    list=new ArrayList<>();
    }

    @Override
    protected void initEvents() {
        iv_actionbar_left.setOnClickListener(this);
        tv_actionbar.setText(getResources().getString(R.string.mine_homework_details));
        tv_actionbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        TextPaint textPaint = tv_actionbar.getPaint();
        textPaint.setFakeBoldText(true);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText(getResources().getString(R.string.label_cancel));
    }

    @Override
    protected void initData() {
        job = getIntent().getParcelableExtra("job");
        LogUtils.d(job.toString());
        if(job.getLabel()==1){
            tv_mine_homework_help.setText(getResources().getString(R.string.homepage_operation_check));
            iv_mine_homework_time.setVisibility(View.GONE);
            tv_mine_homework_time_default.setVisibility(View.GONE);
            tv_mine_homework_help_price.setText(getResources().getString(R.string.mine_help_check_price));
            tv_mine_homework_help_price_default.setText(job.getPrice()+getResources().getString(R.string.homework_check_money));

        }else{
            tv_mine_homework_help.setText(getResources().getString(R.string.homepage_homework_help));
            iv_mine_homework_time.setVisibility(View.VISIBLE);
            tv_mine_homework_time_default.setVisibility(View.VISIBLE);
            tv_mine_homework_help_price_default.setText(job.getPrice()+getResources().getString(R.string.teacher_help_price_units));
            try {
                tv_mine_homework_time_default.setText(Utils.longToString(job.getStart_time()*1000L,"MM-dd HH:mm"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tv_mine_homework_help_price.setText(getResources().getString(R.string.homepage_teacher_help_price));

        }
        if(job.getIs_receive()==1){
            tv_mine_homework_state.setText(getResources().getString(R.string.homepage_release_homework_not_answer_sheet));
            tv_mine_homework_state.setTextColor(getResources().getColor(R.color.font_color_select));
            iv_mine_homework_state.setImageResource(R.drawable.order_wait_receipt);
            rl_mine_homework.setVisibility(View.GONE);
        }else{
            tv_mine_homework_state.setText(getResources().getString(R.string.homepage_release_homework_state));
            tv_mine_homework_state.setTextColor(getResources().getColor(R.color.font_color_unselected));
            iv_mine_homework_state.setImageResource(R.drawable.order_already_received);
            rl_mine_homework.setVisibility(View.VISIBLE);

        }

        tv_mine_homework_class.setText(job.getGrade()+"/"+job.getSubject());

        for (int i = 0; i < job.getImages().length; i++) {
            list.add(job.getImages()[i]);
        }
        WebBannerAdapter webBannerAdapter=new WebBannerAdapter(this, list);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MyHomeworkDetailsActivity.this, "点击了第  " + position+"  项", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerBanner.setAdapter(webBannerAdapter);

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_actionbar_left:
                finish();
                break;
        }
    }
}
