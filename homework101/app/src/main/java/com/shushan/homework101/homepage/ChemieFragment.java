package com.shushan.homework101.homepage;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.homepage.Homepage;
import com.shushan.homework101.HttpHelper.service.presenter.HomepagePresenter;
import com.shushan.homework101.HttpHelper.service.view.HomepageView;
import com.shushan.homework101.R;
import com.shushan.homework101.adapter.HomepageListViewAdapter;
import com.shushan.homework101.base.BaseFragment;
import com.shushan.homework101.bean.TeacherBean;

import java.util.ArrayList;

public class ChemieFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    ListView lv_homepage;
    private HomepageListViewAdapter homepageListViewAdapter;
    private String userid;
    private String token;
    private ArrayList<TeacherBean> teacherBeansChemie=new ArrayList<>();

    public ChemieFragment() {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_subject;
    }

    @Override
    public void initViews(View view) {

           lv_homepage = (ListView) view.findViewById(R.id.lv_teacher);

            SharedPreferences sharedPreferences = mContext.getSharedPreferences("info", Context.MODE_PRIVATE);
            userid = sharedPreferences.getString("userid", "");
            token = sharedPreferences.getString("token", "");
            HomepagePresenter homepagePresenter = new HomepagePresenter(getContext());
            homepagePresenter.onCreate(Constants.BASE_URL);
            homepagePresenter.attachView(homepageView);
            homepagePresenter.getHomepageMsg(userid, token);


    }

    private HomepageView homepageView = new HomepageView() {
        @Override
        public void onSuccess(Homepage homepage) {
            if(teacherBeansChemie!=null&&teacherBeansChemie.size()>0){
                teacherBeansChemie.clear();
            }
            if (homepage != null) {

                if (homepage.getData().getTeacher() != null && homepage.getData().getTeacher().size() > 0) {

                    for (int i = 0; i < homepage.getData().getTeacher().size(); i++) {
                        if (homepage.getData().getTeacher().get(i).getSubject().equals(getResources().getString(R.string.my_chemical))) {
                            TeacherBean teacher = new TeacherBean();
                            teacher.setName(homepage.getData().getTeacher().get(i).getTch_name());
                            teacher.setGrade(homepage.getData().getTeacher().get(i).getScore() + "");
                            teacher.setHelpCount(homepage.getData().getTeacher().get(i).getGuide_num() + "");
                            teacher.setHelpPrice(homepage.getData().getTeacher().get(i).getGuide_price() + "");
                            teacher.setHonor(homepage.getData().getTeacher().get(i).getLevel());

                            teacherBeansChemie.add(teacher);

                        }

                    }
                        homepageListViewAdapter = new HomepageListViewAdapter(mContext,teacherBeansChemie);
                        lv_homepage.setAdapter(homepageListViewAdapter);

                }

            }

        }

        @Override
        public void onError(String result) {

        }
    };

    @Override
    protected void initData() {

    }

    @Override
    public void initEvents() {

        lv_homepage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

}
