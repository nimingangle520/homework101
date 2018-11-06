package com.shushan.homework101.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.teachers.Attention;
import com.shushan.homework101.HttpHelper.service.entity.teachers.Teacher;
import com.shushan.homework101.HttpHelper.service.presenter.TeacherPresenter;
import com.shushan.homework101.HttpHelper.service.view.TeachersView;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.adapter.TeacherFocusListViewAdapter;
import com.shushan.homework101.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class FocusFragment extends BaseFragment {
    @Bind(R.id.lv_teacher_focus)
    ListView lv_teacher_focus;
    private TeacherFocusListViewAdapter teacherFocusListViewAdapter;
    private String userid;
    private String token;
    private TeacherPresenter teacherPresenter;
    private ArrayList<Attention> attentions;
    public FocusFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_focus;
    }

    @Override
    public void initViews(View view) {
    }

    @Override
    protected void initData() {
        attentions=new ArrayList<>();
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
                if(teacher.getData().getAttention()!=null&&teacher.getData().getAttention().size()>0){
                    for (int i = 0; i < teacher.getData().getAttention().size(); i++) {
                        attentions.add( teacher.getData().getAttention().get(i));
                    }
                }
                if(attentions!=null&&attentions.size()>0){

                    teacherFocusListViewAdapter = new TeacherFocusListViewAdapter(mContext,attentions);
                    lv_teacher_focus.setAdapter(teacherFocusListViewAdapter);
                }

            }
        }

        @Override
        public void onError(String result) {

        }
    };
}
