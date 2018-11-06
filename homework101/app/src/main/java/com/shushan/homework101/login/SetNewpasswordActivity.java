package com.shushan.homework101.login;

import android.view.View;
import android.widget.TextView;

import com.shushan.homework101.R;
import com.shushan.homework101.base.BaseActivity;

import butterknife.Bind;

public class SetNewPasswordActivity extends BaseActivity {
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_set_newpassword);
    }

    @Override
    protected void initData() {
    tv_actionbar.setVisibility(View.GONE);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }
}
