package com.shushan.homework101.live;

import android.widget.TextView;

import com.shushan.homework101.R;
import com.shushan.homework101.base.BaseActivity;
import com.shushan.homework101.bean.LabelBean;

import java.util.ArrayList;

import butterknife.Bind;

public class EvaluateActivity extends BaseActivity {
    @Bind(R.id.labelView_homework)
    LabelsView labelView_homework;
    private String[] labels;
    private ArrayList<LabelBean> labelBeans;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_evaluate);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        labels = getResources().getStringArray(R.array.preference_evaluate_label);
        labelBeans = new ArrayList<>();
        if(labels!=null&&labels.length>0){
            for (int i = 0; i <labels.length ; i++) {
                LabelBean labelBean=new LabelBean(labels[i],i);
                labelBeans.add(labelBean);
            }
            labelView_homework.setLabels(labelBeans, new LabelsView.LabelTextProvider<LabelBean>() {
                @Override
                public CharSequence getLabelText(TextView label, int position, LabelBean data) {
                    return data.getName();
                }
            });
        }
        labelView_homework.setSelectType(LabelsView.SelectType.MULTI);
        labelView_homework.setMaxSelect(5);
    }

}
