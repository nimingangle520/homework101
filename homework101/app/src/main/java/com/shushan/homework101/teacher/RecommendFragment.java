package com.shushan.homework101.teacher;

import android.view.View;
import android.widget.ListView;

import com.shushan.homework101.R;
import com.shushan.homework101.adapter.TeacherRecommendListViewAdapter;
import com.shushan.homework101.base.BaseFragment;

import butterknife.Bind;

public class RecommendFragment extends BaseFragment {
    @Bind(R.id.lv_teacher_recommend)
    ListView lv_teacher_recommend;
    private RecommendTeacherModel recommendTeacherModel;
    private TeacherRecommendListViewAdapter teacherRecommendListViewAdapter;

    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initViews(View view) {
        recommendTeacherModel = new RecommendTeacherModel(mContext);
        teacherRecommendListViewAdapter = new TeacherRecommendListViewAdapter(mContext,recommendTeacherModel.getTeacherList());
        lv_teacher_recommend.setAdapter(teacherRecommendListViewAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvents() {

    }
}
