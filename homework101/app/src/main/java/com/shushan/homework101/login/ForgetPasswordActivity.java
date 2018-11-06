package com.shushan.homework101.login;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shushan.homework101.R;
import com.shushan.homework101.base.BaseActivity;

import butterknife.Bind;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.bt_forget_password_next)
    Button bt_forget_password_next;

    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_forget_password);
    }

    @Override
    protected void initData() {
        tv_actionbar.setVisibility(View.GONE);
    }

    @Override
    protected void initViews() {
        bt_forget_password_next.setOnClickListener(this);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_forget_password_next:
                BaseActivity.startActivitys(this, SetNewPasswordActivity.class);
                break;
        }
    }
}
