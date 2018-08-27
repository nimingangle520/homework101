package com.shushan.homework101.mine.wallet;

import android.widget.TextView;

import com.shushan.homework101.R;
import com.shushan.homework101.base.BaseActivity;

import butterknife.Bind;

public class RechargeActivity extends BaseActivity {
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_recharge);
    }

    @Override
    protected void initViews() {
        tv_actionbar.setText(getResources().getString(R.string.mine_wallet_recharge));
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }
}
