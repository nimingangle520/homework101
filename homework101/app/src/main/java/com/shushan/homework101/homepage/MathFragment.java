package com.shushan.homework101.homepage;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shushan.homework101.R;
import com.shushan.homework101.adapter.HomepageListViewAdapter;
import com.shushan.homework101.base.BaseFragment;

public class MathFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    ListView lv_homepage;
    private HomepageListViewAdapter homepageListViewAdapter;

    public MathFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_math;
    }

    @Override
    public void initViews(View view) {
        lv_homepage=(ListView) view.findViewById(R.id.lv_teacher);
        homepageListViewAdapter = new HomepageListViewAdapter(mContext,new TeacherModel(mContext).getTeacherList());
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvents() {
        lv_homepage.setAdapter(homepageListViewAdapter);
        lv_homepage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
