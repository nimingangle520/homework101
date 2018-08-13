package com.shushan.homework101.teacher;

import android.view.View;
import android.widget.ListView;

import com.shushan.homework101.R;
import com.shushan.homework101.adapter.TeacherFocusListViewAdapter;
import com.shushan.homework101.base.BaseFragment;

import butterknife.Bind;

public class FocusFragment extends BaseFragment {
    @Bind(R.id.lv_teacher_focus)
    ListView lv_teacher_focus;
    private FocusTeacherModle focusTeacherModle;
    private TeacherFocusListViewAdapter teacherFocusListViewAdapter;

    public FocusFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_focus;
    }

    @Override
    public void initViews(View view) {
        focusTeacherModle = new FocusTeacherModle(mContext);
        teacherFocusListViewAdapter = new TeacherFocusListViewAdapter(mContext,focusTeacherModle.getTeacherList());
        lv_teacher_focus.setAdapter(teacherFocusListViewAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvents() {

    }
}
