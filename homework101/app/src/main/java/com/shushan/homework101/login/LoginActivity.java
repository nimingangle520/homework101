package com.shushan.homework101.login;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shushan.homework101.MainActivity;
import com.shushan.homework101.R;
import com.shushan.homework101.base.BaseActivity;

import butterknife.Bind;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.bt_login)
    Button bt_login;
    @Bind(R.id.tv_login_register)
    TextView tv_login_register;
    @Bind(R.id.tv_login_forget_password)
    TextView tv_login_forget_password;

    @Override

    protected void initContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        bt_login.setOnClickListener(this);
        tv_login_register.setOnClickListener(this);
        tv_login_forget_password.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                BaseActivity.startActivitys(this, MainActivity.class);
                break;
            case R.id.tv_login_register:
                BaseActivity.startActivitys(this,RegisterActivity.class);
                break;
            case R.id.tv_login_forget_password:
                BaseActivity.startActivitys(this,ForgetPasswordActivity.class);
                break;
        }
    }
}

