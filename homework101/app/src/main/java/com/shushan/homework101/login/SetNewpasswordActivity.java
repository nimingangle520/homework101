package com.shushan.homework101.login;

import android.widget.TextView;

import com.shushan.homework101.R;
import com.shushan.homework101.base.BaseActivity;

import butterknife.Bind;

public class SetNewpasswordActivity extends BaseActivity {
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_set_newpassword);
    }

    @Override
    protected void initData() {
    tv_actionbar.setText(R.string.set_new_password);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }
}
