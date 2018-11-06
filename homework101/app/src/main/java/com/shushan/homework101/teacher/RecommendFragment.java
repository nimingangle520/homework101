package com.shushan.homework101.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.teachers.Recommend;
import com.shushan.homework101.HttpHelper.service.entity.teachers.Teacher;
import com.shushan.homework101.HttpHelper.service.presenter.TeacherPresenter;
import com.shushan.homework101.HttpHelper.service.view.TeachersView;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.adapter.TeacherRecommendListViewAdapter;
import com.shushan.homework101.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class RecommendFragment extends BaseFragment {
    @Bind(R.id.lv_teacher_recommend)
    ListView lv_teacher_recommend;
    private TeacherRecommendListViewAdapter teacherRecommendListViewAdapter;
    private String userid;
    private String token;
    private TeacherPresenter teacherPresenter;
    private ArrayList<Recommend> recommends;
    public RecommendFragment() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initViews(View view) {
    }

    @Override
    protected void initData() {
        recommends = new ArrayList<>();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("info", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("userid", "");
        token = sharedPreferences.getString("token", "");
        teacherPresenter = new TeacherPresenter(mContext);
        teacherPresenter.onCreate(Constants.BASE_URL);
        teacherPresenter.attachView(teachersView);

        if(!TextUtils.isEmpty(userid)&&!TextUtils.isEmpty(token)){
            teacherPresenter.getTeacherMsg(userid,token);
        }
    }

    @Override
    public void initEvents() {

    }
    private TeachersView teachersView=new TeachersView() {
        @Override
        public void onSuccess(Teacher teacher) {
            if(teacher!=null){
                LogUtils.d(teacher.toString());
                if(teacher.getData().getRecommend()!=null&&teacher.getData().getRecommend().size()>0){
                    for (int i = 0; i < teacher.getData().getRecommend().size(); i++) {
                        recommends.add( teacher.getData().getRecommend().get(i));
                    }
                }

                teacherRecommendListViewAdapter = new TeacherRecommendListViewAdapter(mContext,recommends);
                lv_teacher_recommend.setAdapter(teacherRecommendListViewAdapter);

            }
        }

        @Override
        public void onError(String result) {

        }
    };
}
