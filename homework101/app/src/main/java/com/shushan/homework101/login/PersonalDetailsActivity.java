package com.shushan.homework101.login;

import android.content.SharedPreferences;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.register.Grade;
import com.shushan.homework101.HttpHelper.service.presenter.GradePresenter;
import com.shushan.homework101.HttpHelper.service.view.GradeView;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.Utils.Utils;
import com.shushan.homework101.base.BaseActivity;
import com.shushan.homework101.homework.select.CustomDate;
import com.shushan.homework101.homework.select.WheelView;

import java.util.Arrays;

import butterknife.Bind;

public class PersonalDetailsActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.iv_actionbar_left)
    ImageView iv_actionbar_left;
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.year)
    WheelView wv_year;
    @Bind(R.id.btn_per_details_ok)
    Button btn_per_details_ok;
    @Bind(R.id.et_per_details_name)
    EditText et_per_details_name;
    private String[] classArrays;
    private int width;
    private int height;
    private DisplayMetrics displayMetrics;
    private CustomDate.WheelMain wheelClassMain;
    private boolean hasSelect = true;
    private String name;
    private String grade_position;
    private GradePresenter gradePresenter;
    private CustomDate.WheelMainAdapter wheelMainAdapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_personal_details);

    }

    @Override
    protected void initViews() {

        iv_actionbar_left.setImageResource(R.drawable.back_white);
        tv_actionbar.setTextColor(getResources().getColor(R.color.white));
        tv_actionbar.setText(getResources().getString(R.string.per_details));
        tv_actionbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        TextPaint textPaint = tv_actionbar.getPaint();
        textPaint.setFakeBoldText(true);
        et_per_details_name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});


    }

    @Override
    protected void initEvents() {
        btn_per_details_ok.setOnClickListener(this);
        iv_actionbar_left.setOnClickListener(this);
    }

    private GradeView gradeView = new GradeView() {
        @Override
        public void onSuccess(Grade grade) {
            LogUtils.d(grade.toString());
            if(grade.getData()!=null&&grade.getData().size()>0){

                classArrays=new String[grade.getData().size()];

                for (int i = 0; i <grade.getData().size(); i++) {
                    classArrays[i]=grade.getData().get(i).getGrade();
                }
            }
            wv_year.setVisibility(View.VISIBLE);
            //classArrays = mContext.getResources().getStringArray(R.array.preference_primary_class);
            wheelMainAdapter = new CustomDate.WheelMainAdapter(
                    0, Arrays.asList(classArrays).size(), Arrays.asList(classArrays));
            wv_year.setAdapter(wheelMainAdapter);
            wv_year.setCyclic(true);// 可循环滚动
            wv_year.setCurrentItem(Integer.parseInt(grade_position)-1);// 初始化时显示的数据
            // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
            int textSize = 0;
            if (hasSelect)
                textSize = (height / 140) * 4;
            else
                textSize = (height / 180) * 4;
            wv_year.TEXT_SIZE = textSize;
        }

        @Override
        public void onError(String result) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        name = sharedPreferences.getString("name", "");
        et_per_details_name.setText(name);
        grade_position = sharedPreferences.getString("grade_position", "");
        if(wv_year.getVisibility()==View.VISIBLE){

            wv_year.setCurrentItem(Integer.parseInt(grade_position)-1);
        }
    }

    @Override
    protected void initData() {
        displayMetrics = Utils.getScreenWH(mContext);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        name = sharedPreferences.getString("name", "");
        et_per_details_name.setText(name);
        grade_position = sharedPreferences.getString("grade_position", "");
        if(wv_year.getVisibility()==View.VISIBLE){

            wv_year.setCurrentItem(Integer.parseInt(grade_position)-1);
        }

        gradePresenter = new GradePresenter(this);
        gradePresenter.onCreate(Constants.BASE_URL);
        gradePresenter.attachView(gradeView);
        gradePresenter.getGradeMsg();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_per_details_ok:
                name = et_per_details_name.getText().toString().trim();
                grade_position = wv_year.getCurrentItem()+1+"";
                if (!TextUtils.isEmpty(name) && isLegalName(name)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", name);
                    editor.putString("grade_position", grade_position);
                    editor.commit();

                    startActivitys(this, LoginActivity.class);
                }
                break;
            case R.id.iv_actionbar_left:
                startActivitys(this, RegisterActivity.class);
                break;
        }
    }

    /**
     * 验证输入的名字是否为“中文”或者是否包含“·”
     */
    public static boolean isLegalName(String name) {
        if (name.contains("·") || name.contains("•")) {
            if (name.matches("^[\\u4e00-\\u9fa5]+[·•][\\u4e00-\\u9fa5]+$")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (name.matches("^[\\u4e00-\\u9fa5]+$")) {
                return true;
            } else {
                return false;
            }
        }
    }
}
